/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.JasonerException;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The class JsonDeserializerImpl.
 * This class implements the JsonDeserializer interface to deserialize a JSON string to an object.
 *
 * @since 1.0
 * @version 1.0
 *
 * @author Edgar Alba
 */
class JsonDeserializerImpl implements JsonDeserializer {
    /**
     * The constant ERROR_UNEXPECTED_TOKEN.
     */
    private static final String ERROR_UNEXPECTED_TOKEN = "Unexpected token '%s'";

    /**
     * Instantiates a new JsonDeserializerImpl.
     */
    JsonDeserializerImpl() {
    }

    /**
     * Deserialize an object from a JSON string.
     *
     * @param <T>   the type of the object to return
     * @param data  the JSON string to deserialize
     * @param clazz the class of the object to return
     * @return the object represented by the JSON
     */
    @Override
    public <T> T deserialize(Reader data, Class<T> clazz) {
        JsonTokenizer tokenizer = new JsonTokenizerImpl(readAll(data));
        var token = tokenizer.next();
        if (token.type() == TokenType.OBJECT_START) {
            return deserializeObject(tokenizer, clazz);
        } else if (token.type() == TokenType.ARRAY_START) {
            return deserializeArray(tokenizer, clazz, HashMap.class);
        }
        throw new JasonerException(String.format(ERROR_UNEXPECTED_TOKEN, token));
    }
    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(Reader data, Type genericSuperclass) {
        JsonTokenizer tokenizer = new JsonTokenizerImpl(readAll(data));
        var token = tokenizer.next();
        expectedToken(tokenizer.current(), TokenType.ARRAY_START);
        if (token.type() == TokenType.ARRAY_START) {
            Class<?> classType = Reflects.getClass(genericSuperclass.getTypeName());
            return (T) deserializeArray(tokenizer, ArrayList.class, classType);
        }
        return null;
    }


    private <T> T deserializeObject(JsonTokenizer tokenizer, Class<T> clazz) {
        expectedToken(tokenizer.current(), TokenType.OBJECT_START);
        return createObject(clazz, tokenizer);
    }
    private <T> T deserializeArray(JsonTokenizer tokenizer, Class<T> clazz, Class<?> clazz2) {
        expectedToken(tokenizer.current(), TokenType.ARRAY_START);
        List<Object> list = new ArrayList<>();
        moveArrayValues(list, tokenizer, clazz2);
        return clazz.cast(list);
    }

    private String readAll(Reader data) {
        StringBuilder sb = new StringBuilder();
        try {
            int c;
            while ((c = data.read()) != -1) {
                sb.append((char) c);
            }
        } catch (Exception e) {
            throw new JasonerException(e);
        }
        return sb.toString();
    }

    private <T> T createObject(Class<T> clazz, JsonTokenizer tokenizer) {
        var creator = new ObjectCreator<>(clazz);
        expectedToken(tokenizer.current(), TokenType.OBJECT_START);
        moveProperties(creator, tokenizer);
        return creator.create();
    }

    private void moveProperties(ObjectCreator<?> obj, JsonTokenizer tokenizer) {
        while (tokenizer.hasNext()) {
            String name = expectedToken(tokenizer.next(), TokenType.TEXT).stringValue();
            expectedToken(tokenizer.next(), TokenType.COLON);
            expectedTokenValue(tokenizer.next());
            moveProperty(obj, name, tokenizer);
            Token token = tokenizer.next();
            if (token.type() == TokenType.COMMA) {
                continue;
            }
            if (token.type() == TokenType.OBJECT_END) {
                break;
            }
        }
    }

    private void moveProperty(final ObjectCreator<?> obj, final String name, final JsonTokenizer tokenizer) {
        var parameterClass = obj.getParameterClass(name);
        if (parameterClass.isPresent()) {
            Object value = getValue(tokenizer, parameterClass.get());
            obj.setValue(name, value);
        } else {
            // consume the value
            Object value = getValue(tokenizer, HashMap.class);
            if (obj.isMap()) {
                obj.setValue(name, value);
            }
        }
    }

    private Object getValue(JsonTokenizer tokenizer, Class<?> ctype) {
        Object value;
        Token token = tokenizer.current();
        if (token.type() == TokenType.OBJECT_START) {
            value = createObject(ctype, tokenizer);
        } else if (token.type() == TokenType.ARRAY_START) {
            List<Object> list = new ArrayList<>();
            moveArrayValues(list, tokenizer, ctype);
            value = list;
        } else {
            value = token.typeValue(ctype);
        }
        return value;
    }

    private void moveArrayValues(List<Object> list, JsonTokenizer tokenizer, Class<?> ctype) {
        while (tokenizer.hasNext()) {
            Token token = expectedTokenValue(tokenizer.next());
            if (token.type() == TokenType.OBJECT_START) {
                list.add(createObject(ctype, tokenizer));
            } else {
                list.add(token.value());
            }
            token = tokenizer.next();
            if (token.type() == TokenType.COMMA) {
                continue;
            }
            if (token.type() == TokenType.ARRAY_END) {
                break;
            }
        }
    }

    private static Token expectedToken(Token token, TokenType expected) {
        if (token == null) {
            throw new JasonerException(String.format(ERROR_UNEXPECTED_TOKEN, "null"));
        }
        if (token.type() != expected) {
            throw new JasonerException(String.format(ERROR_UNEXPECTED_TOKEN, token));
        }
        return token;
    }

    private static Token expectedTokenValue(Token token) {
        if (token == null) {
            throw new JasonerException(String.format(ERROR_UNEXPECTED_TOKEN, "null"));
        }
        if (!(token.type() == TokenType.TEXT || token.type() == TokenType.NUMBER || token.type() == TokenType.TRUE
                || token.type() == TokenType.FALSE || token.type() == TokenType.NULL
                || token.type() == TokenType.ARRAY_START || token.type() == TokenType.OBJECT_START)) {
            throw new JasonerException(String.format(ERROR_UNEXPECTED_TOKEN, token));
        }
        return token;
    }
}
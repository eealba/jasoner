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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type Json deserializer.
 * This class is used to deserialize a JSON string to an object.
 * @author Edgar Alba
 */
class JsonDeserializerImpl implements JsonDeserializer {
	/**
	 * The constant ERROR_UNEXPECTED_TOKEN.
	 */
	private static final String ERROR_UNEXPECTED_TOKEN = "Unexpected token '%s'";
	JsonDeserializerImpl() {
	}

	/**
	 * Deserialize t.
	 *
	 * @param <T>   the type parameter
	 * @param data  the data
	 * @param clazz the clazz
	 * @return the t
	 */
	@Override
	public <T> T deserialize(String data, Class<T> clazz) {
		JsonTokenizer tokenizer = new JsonTokenizerImpl(data);
		expectedToken(tokenizer.next(), TokenType.OBJECT_START);
		return createObject(clazz, tokenizer);
	}

	/**
	 * Create object t.
	 *
	 * @param <T>       the type parameter
	 * @param clazz     the clazz
	 * @param tokenizer the tokenizer
	 * @return the t
	 */
	private <T> T createObject(Class<T> clazz, JsonTokenizer tokenizer) {
		var creator = new ObjectCreator<>(clazz);
			expectedToken(tokenizer.current(), TokenType.OBJECT_START);
			moveProperties(creator, tokenizer);
		return creator.create();

	}


	/**
	 * Move properties.
	 *
	 * @param obj       the obj
	 * @param tokenizer the tokenizer
	 */
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

	/**
	 * Move property.
	 *
	 * @param obj       the obj
	 * @param name      the name
	 * @param tokenizer the tokenizer
	 */
	private void moveProperty(final ObjectCreator<?> obj, final String name, final JsonTokenizer tokenizer){
			var parameterClass = obj.getParameterClass(name);
			if (parameterClass.isPresent()) {
				Object value = getValue(tokenizer, parameterClass.get());
				obj.setValue(name, value);
			}else{
				//consume the value
				getValue(tokenizer, HashMap.class);
			}
	}


	/**
	 * Gets value.
	 *
	 * @param tokenizer the tokenizer
	 * @param ctype     the ctype
	 * @return the value
	 */
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

	/**
	 * Move array values.
	 *
	 * @param list      the list
	 * @param tokenizer the tokenizer
	 * @param ctype     the ctype
	 */
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

	/**
	 * Expected token token.
	 *
	 * @param token    the token
	 * @param expected the expected
	 * @return the token
	 */
	private static Token expectedToken(Token token, TokenType expected) {
		if (token == null) {
			throw new JasonerException(String.format(ERROR_UNEXPECTED_TOKEN, "null"));
		}
		if (token.type() != expected) {
			throw new JasonerException(String.format(ERROR_UNEXPECTED_TOKEN, token));
		}
		return token;
	}

	/**
	 * Expected token value token.
	 *
	 * @param token the token
	 * @return the token
	 */
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

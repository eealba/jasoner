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

import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerConfig;
import io.github.eealba.jasoner.JasonerException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;

/**
 * Implementation of the Jasoner interface.
 * @author Edgar Alba
 */
class JasonerImpl implements Jasoner {
    /**
     * The configuration for the Jasoner library.
     */
    private final JasonerConfig config;
    private final JsonSerializer serializer;
    private final JsonDeserializer deserializer;
    /**
     * Instantiates a new Jasoner implementation.
     *
     * @param config the configuration for the Jasoner library
     */
    JasonerImpl(JasonerConfig config) {
        this.config = config;
        this.serializer = new JsonSerializerImpl(config);
        this.deserializer = new JsonDeserializerImpl();
    }

    /**
     * Converts an object to its JSON representation.
     *
     * @param object the object to convert
     * @return the JSON representation of the object
     */
    @Override
    public String toJson(Object object) {
        Writer writer = new StringWriter();
        toJson(object, writer);
        return writer.toString();
    }

    /**
     * Converts an object to its JSON representation and writes it to an OutputStream.
     *
     * @param object the object to convert
     * @param stream the OutputStream to write the JSON to
     */
    @Override
    public void toJson(Object object, OutputStream stream) {
        Writer writer = new OutputStreamWriter(stream, config.charset());
        toJson(object, writer);
    }

    /**
     * Converts an object to its JSON representation and writes it to a Writer.
     *
     * @param object the object to convert
     * @param writer the Writer to write the JSON to
     */
    @Override
    public void toJson(Object object, Writer writer) {
        try {
            serializer.serialize(writer, object);
        } catch (IOException e) {
            throw new JasonerException(e);
        }

    }

    /**
     * Converts a JSON InputStream to an object of the specified type.
     *
     * @param stream the InputStream containing the JSON
     * @param type   the class of the object to return
     * @return the object represented by the JSON
     */
    @Override
    public <T> T fromJson(InputStream stream, Class<T> type) {
        Reader reader = new InputStreamReader(stream, config.charset());
        return fromJson(reader, type);
    }


    /**
     * Converts a JSON string to an object of the specified type.
     *
     * @param json the JSON string
     * @param type the class of the object to return
     * @return the object represented by the JSON
     */
    @Override
    public <T> T fromJson(String json, Class<T> type) {
        Reader reader = new StringReader(json);
        return fromJson(reader, type);
    }

    /**
     * Converts a JSON string to an object of the specified type.
     *
     * @param result            the JSON string
     * @param genericSuperclass the class of the object to return
     */
    @Override
    public <T> T fromJson(String result, Type genericSuperclass) {
        Reader reader = new StringReader(result);
        return deserializer.deserialize(reader, genericSuperclass);
    }

    /**
     * Converts a JSON Reader to an object of the specified type.
     *
     * @param reader the Reader containing the JSON
     * @param type   the class of the object to return
     * @return the object represented by the JSON
     */
    @Override
    public <T> T fromJson(Reader reader, Class<T> type) {
        return deserializer.deserialize(reader, type);
    }

}

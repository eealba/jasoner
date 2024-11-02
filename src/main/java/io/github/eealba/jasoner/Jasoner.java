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

package io.github.eealba.jasoner;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;

/**
 * Interface for converting objects to JSON and vice versa.
 * @author Edgar Alba
 */
public interface Jasoner {

    /**
     * Converts an object to its JSON representation.
     *
     * @param object the object to convert
     * @return the JSON representation of the object
     */
    String toJson(Object object);

    /**
     * Converts an object to its JSON representation and writes it to an OutputStream.
     *
     * @param object the object to convert
     * @param stream the OutputStream to write the JSON to
     */
    void toJson(Object object, OutputStream stream);

    /**
     * Converts an object to its JSON representation and writes it to a Writer.
     *
     * @param object the object to convert
     * @param writer the Writer to write the JSON to
     */
    void toJson(Object object, Writer writer);

    /**
     * Converts a JSON InputStream to an object of the specified type.
     *
     * @param stream the InputStream containing the JSON
     * @param type the class of the object to return
     * @param <T> the type of the object to return
     * @return the object represented by the JSON
     */
    <T> T fromJson(InputStream stream, Class<T> type);

    /**
     * Converts a JSON Reader to an object of the specified type.
     *
     * @param reader the Reader containing the JSON
     * @param type the class of the object to return
     * @param <T> the type of the object to return
     * @return the object represented by the JSON
     */
    <T> T fromJson(Reader reader, Class<T> type);

    /**
     * Converts a JSON string to an object of the specified type.
     *
     * @param json the JSON string
     * @param type the class of the object to return
     * @param <T> the type of the object to return
     * @return the object represented by the JSON
     */
    <T> T fromJson(String json, Class<T> type);
    /**
     * Converts a JSON string to an object of the specified type.
     *
     * @param result the JSON string
     * @param genericSuperclass the class of the object to return
     */
    <T> T  fromJson(String result, Type genericSuperclass);
}
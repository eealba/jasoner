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

import java.io.Reader;
import java.io.StringReader;

/**
 * A deserializer receives a json String and converts it to a Java entity.
 * This interface is used to deserialize a JSON string to an object.
 * @author Edgar Alba
 *
 */
interface JsonDeserializer {
    /**
     * Deserialize an object from a JSON string.
     *
     * @param <T> the type of the object to return
     * @param data the JSON string to deserialize
     * @param clazz the class of the object to return
     * @return the object represented by the JSON
     */
    <T> T deserialize(Reader data, Class<T> clazz);
    /**
     * Deserialize an object from a JSON string.
     *
     * @param <T> the type of the object to return
     * @param data the JSON string to deserialize
     * @param clazz the class of the object to return
     * @return the object represented by the JSON
     */
    default <T> T deserialize(String data, Class<T> clazz){
        return deserialize(new StringReader(data), clazz);
    }
}

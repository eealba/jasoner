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

/**
 * A deserializer receives a json String and converts it to a Java entity.
 * This interface is used to deserialize a JSON string to an object.
 * @author Edgar Alba
 *
 */
interface JsonDeserializer {
    /**
     * Returns an entity T from String
     *
     * @param data A String data
     * @return Returns an entity T
     */
    <T> T deserialize(String data, Class<T> clazz);

}

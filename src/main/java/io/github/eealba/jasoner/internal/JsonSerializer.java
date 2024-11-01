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

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * The interface Json serializer.
 * This interface is used to serialize an object to a JSON string.
 * @author Edgar Alba
 */
interface JsonSerializer {

    /**
     * Serialize an object to a JSON string.
     *
     * @param data the writer to write the JSON string to
     * @param obj the object to serialize
     */
    void serialize(Writer data, Object obj) throws IOException;

    /**
     * Serialize an object to a JSON string.
     *
     * @param obj the object to serialize
     * @return the JSON string representation of the object
     */
    default String serialize(Object obj) {
        Writer writer = new StringWriter();
        try {
            serialize(writer, obj);
        } catch (IOException e) {
            throw new JasonerException(e);
        }
        return writer.toString();
    }
}

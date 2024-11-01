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

import java.nio.charset.StandardCharsets;

/**
 * The interface Json serializer.
 * This interface is used to serialize an object to a JSON string.
 * @author Edgar Alba
 */
interface JsonSerializer {

    /**
     * Serialize string.
     *
     * @param obj the obj
     * @return the string
     */
    String serialize(Object obj);

    /**
     * Serialize to byte array byte [ ].
     *
     * @param obj the obj
     * @return the byte [ ]
     */
    default byte[] serializeToByteArray(Object obj) {
		return serialize(obj).getBytes(StandardCharsets.UTF_8);
	}

}

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

import java.time.Instant;

/**
 * The class ConvertFactory.
 * This class is used to create instances of the Convert interface based on the type of the input object.
 *
 * @since 1.0
 * @version 1.0
 *
 * @author Edgar Alba
 */
class ConvertFactory {

    /**
     * Gets the converter for the specified type.
     *
     * @param type the class type of the input object
     * @return the converter for the specified type
     */
    public static Convert<Object, ?> getConverter(Class<?> type) {
        if (type == Instant.class) {
            return new ConvertInstant();
        }
        if (type.isRecord() && type.getDeclaredConstructors()[0].getParameters().length == 1) {
            return new ConvertSimpleRecord<>(type);
        }
        return (Convert<Object, Object>) obj -> obj;
    }
}
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
 * The interface Convert.
 * This interface is used to convert an object of type Object to an object of type T.
 *
 * @param <Object> the type of the input object
 * @param <T> the type of the output object
 *
 * @since 1.0
 * @version 1.0
 *
 * @author Edgar Alba
 */
interface Convert<Object, T> {

    /**
     * Convert an object of type Object to an object of type T.
     *
     * @param obj the object to convert
     * @return the converted object
     */
    T convert(Object obj);
}

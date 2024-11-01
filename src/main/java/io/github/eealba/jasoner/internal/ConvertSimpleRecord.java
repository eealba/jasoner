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

import java.lang.reflect.InvocationTargetException;

/**
 * The class ConvertSimpleRecord.
 * This class implements the Convert interface to convert an object to a simple record type.
 *
 * @param <T> the type of the output object
 *
 * @since 1.0
 * @version 1.0
 *
 * @author Edgar Alba
 */
class ConvertSimpleRecord<T> implements Convert<Object, T> {
    private final Class<T> type;

    /**
     * Instantiates a new ConvertSimpleRecord.
     *
     * @param type the class type of the output object
     * @throws JasonerException if the record class does not have exactly one parameter in the constructor
     */
    public ConvertSimpleRecord(Class<T> type) {
        if (type.getDeclaredConstructors()[0].getParameters().length != 1) {
            throw new JasonerException("Record class must have only one parameter in the constructor");
        }
        this.type = type;
    }

    /**
     * Converts an object to a simple record type.
     *
     * @param obj the object to convert
     * @return the converted object
     * @throws JasonerException if the conversion fails
     */
    @Override
    public T convert(Object obj) {
        try {
            return type.cast(type.getDeclaredConstructors()[0].newInstance(obj));
        } catch (IllegalArgumentException | InstantiationException | IllegalAccessException
                 | InvocationTargetException e) {
            throw new JasonerException(e);
        }
    }
}
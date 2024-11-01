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

import io.github.eealba.jasoner.ModifierStrategy;
import io.github.eealba.jasoner.NamingStrategy;
import io.github.eealba.jasoner.PrefixAccessorStrategy;
import io.github.eealba.jasoner.SerializationStrategy;

/**
 * The interface Json serializer builder.
 * This interface is used to build a json serializer.
 * @author Edgar Alba
 */
interface JsonSerializerBuilder {

    /**
     * Build json serializer.
     *
     * @return the json serializer
     */
    JsonSerializer build();

    /**
     * Naming strategy json serializer builder.
     *
     * @param ns the ns
     * @return the json serializer builder
     */
    JsonSerializerBuilder namingStrategy(NamingStrategy ns);

    /**
     * Serialization strategy json serializer builder.
     *
     * @param ss the ss
     * @return the json serializer builder
     */
    JsonSerializerBuilder serializationStrategy(SerializationStrategy ss);

    /**
     * Modifier strategy json serializer builder.
     *
     * @param as the as
     * @return the json serializer builder
     */
    JsonSerializerBuilder modifierStrategy(ModifierStrategy as);

    /**
     * Prefix accessor strategy json serializer builder.
     *
     * @param as the as
     * @return the json serializer builder
     */
    JsonSerializerBuilder prefixAccessorStrategy(PrefixAccessorStrategy as);

    JsonSerializerBuilder pretty(boolean boo);
}

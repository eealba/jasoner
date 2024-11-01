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


import java.nio.charset.Charset;
import java.util.Objects;

/**
 * The type Json provider.
 * @author Edgar Alba
 */
public abstract class JsonProvider {
    /**
     * A constant representing the name of the default {@code JsonProvider}
     * implementation class.
     */
    private static final String DEFAULT_PROVIDER = "io.github.eealba.jasoner.internal.JsonProviderImpl";

    /**
     * Creates a JSON provider object. If there are no available service providers,
     * this method returns the default service provider.
     *
     * @return a JSON provider
     */
    public static JsonProvider provider() {
        return Providers.getProvider(JsonProvider.class, DEFAULT_PROVIDER);
    }

    /**
     * Create serializer builder json serializer builder.
     *
     * @return the json serializer builder
     */
    public abstract JsonSerializerBuilder createSerializerBuilder();

    /**
     * Create deserializer builder json deserializer builder.
     *
     * @return the json deserializer builder
     */
    public abstract JsonDeserializerBuilder createDeserializerBuilder();

    /**
     * Create tokenizer json tokenizer.
     *
     * @param data the data
     * @return the json tokenizer
     */
    public abstract JsonTokenizer createTokenizer(String data);

    /**
     * Create tokenizer json tokenizer.
     *
     * @param data the data
     * @param cs   the cs
     * @return the json tokenizer
     */
    public JsonTokenizer createTokenizer(byte[] data, Charset cs) {
        return createTokenizer(new String(Objects.requireNonNull(data), Objects.requireNonNull(cs)));
    }

    /**
     * Create jasoner jasoner.
     *
     * @param config the config
     * @return the jasoner
     */
    public abstract Jasoner createJasoner(JasonerConfig config);
}

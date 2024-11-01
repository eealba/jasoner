package io.github.eealba.jasoner;

import java.nio.charset.Charset;
import java.util.Objects;

/**
 * The type Json provider.
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

}

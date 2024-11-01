package io.github.eealba.jasoner;

import java.nio.charset.Charset;

/**
 * The type Json.
 */
public class Json {
	private static final JsonProvider provider = JsonProvider.provider();

    /**
     * Create serializer builder json serializer builder.
     *
     * @return the json serializer builder
     */
    public static JsonSerializerBuilder createSerializerBuilder() {

		return provider.createSerializerBuilder();
	}

    /**
     * Create deserializer builder json deserializer builder.
     *
     * @return the json deserializer builder
     */
    public static JsonDeserializerBuilder createDeserializerBuilder() {
		return provider.createDeserializerBuilder();
	}

    /**
     * Create tokenizer json tokenizer.
     *
     * @param data the data
     * @return the json tokenizer
     */
    public static JsonTokenizer createTokenizer(String data) {
		return provider.createTokenizer(data);
	}

    /**
     * Create tokenizer json tokenizer.
     *
     * @param data the data
     * @param cs   the cs
     * @return the json tokenizer
     */
    public static JsonTokenizer createTokenizer(byte[] data, Charset cs) {
		return provider.createTokenizer(data, cs);

	}

}

package io.github.eealba.jasoner;

/**
 * The interface Json serializer builder.
 */
public interface JsonSerializerBuilder {

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

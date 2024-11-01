package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.JsonDeserializer;
import io.github.eealba.jasoner.JsonDeserializerBuilder;

public class JsonDeserializerBuilderImpl implements JsonDeserializerBuilder {

    @Override
    public JsonDeserializer build() {
        return new JsonDeserializerImpl();
    }
}

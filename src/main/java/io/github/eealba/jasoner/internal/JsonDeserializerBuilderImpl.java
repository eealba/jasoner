package io.github.eealba.jasoner.internal;

class JsonDeserializerBuilderImpl implements JsonDeserializerBuilder {

    @Override
    public JsonDeserializer build() {
        return new JsonDeserializerImpl();
    }
}

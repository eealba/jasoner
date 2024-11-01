package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.JsonSerializer;
import io.github.eealba.jasoner.JsonSerializerBuilder;
import io.github.eealba.jasoner.ModifierStrategy;
import io.github.eealba.jasoner.NamingStrategy;
import io.github.eealba.jasoner.PrefixAccessorStrategy;
import io.github.eealba.jasoner.SerializationStrategy;

class JsonSerializerBuilderImpl implements JsonSerializerBuilder {
    NamingStrategy ns = NamingStrategy.CAMEL_CASE;
    SerializationStrategy ss = SerializationStrategy.METHOD;
    ModifierStrategy ms = ModifierStrategy.PUBLIC;
    PrefixAccessorStrategy pas = PrefixAccessorStrategy.REMOVE;
    boolean pretty;


    @Override
    public JsonSerializerBuilder namingStrategy(NamingStrategy ns) {
        this.ns = ns;
        return this;
    }

    @Override
    public JsonSerializerBuilder serializationStrategy(SerializationStrategy ss) {
        this.ss = ss;
        return this;
    }

    @Override
    public JsonSerializerBuilder modifierStrategy(ModifierStrategy ms) {
        this.ms = ms;
        return this;
    }

    @Override
    public JsonSerializerBuilder prefixAccessorStrategy(PrefixAccessorStrategy pas) {
        this.pas = pas;
        return this;
    }

    @Override
    public JsonSerializerBuilder pretty(boolean boo) {
        this.pretty = boo;
        return this;
    }

    @Override
    public JsonSerializer build() {
        return new JsonSerializerImpl(this);
    }


}

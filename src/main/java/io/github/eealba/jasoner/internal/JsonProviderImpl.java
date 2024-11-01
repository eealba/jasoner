package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.JsonDeserializerBuilder;
import io.github.eealba.jasoner.JsonProvider;
import io.github.eealba.jasoner.JsonSerializerBuilder;
import io.github.eealba.jasoner.JsonTokenizer;

/**
 * The type Json provider.
 */
class JsonProviderImpl extends JsonProvider {

	@Override
	public JsonSerializerBuilder createSerializerBuilder() {
		return new JsonSerializerBuilderImpl();
	}

	@Override
	public JsonTokenizer createTokenizer(String data) {
		return new JsonTokenizerImpl(data);
	}

	@Override
	public JsonDeserializerBuilder createDeserializerBuilder() {
		return new JsonDeserializerBuilderImpl();
	}

}

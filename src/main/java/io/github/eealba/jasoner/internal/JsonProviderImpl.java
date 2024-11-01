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

import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerConfig;
import io.github.eealba.jasoner.JsonDeserializerBuilder;
import io.github.eealba.jasoner.JsonProvider;
import io.github.eealba.jasoner.JsonSerializerBuilder;
import io.github.eealba.jasoner.JsonTokenizer;

/**
 * The type Json provider.
 * @author Edgar Alba
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

	/**
	 * Create jasoner jasoner.
	 *
	 * @param config the config
	 * @return the jasoner
	 */
	@Override
	public Jasoner createJasoner(JasonerConfig config) {
		return new JasonerImpl(config);
	}

	@Override
	public JsonDeserializerBuilder createDeserializerBuilder() {
		return new JsonDeserializerBuilderImpl();
	}

}

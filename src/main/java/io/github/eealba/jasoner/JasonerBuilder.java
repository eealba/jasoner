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

/**
 * The type Jasoner builder.
 * This class is used to create instances of the Jasoner interface.
 * It provides methods to create a Jasoner with default or custom configurations.
 *
 * @see Jasoner
 * @see JasonerConfig
 * @see JasonerProvider
 *
 * @author Edgar Alba
 */
public class JasonerBuilder {
    /**
     * Private constructor for JasonerBuilder.
     */
    private JasonerBuilder() {
    }

    /**
     * Create a Jasoner instance with default configuration.
     *
     * @return the Jasoner instance
     */
    public static Jasoner create() {
        return create(JasonerConfig.DEFAULT);
    }

    /**
     * Create a Jasoner instance with custom configuration.
     *
     * @param config the custom configuration
     * @return the Jasoner instance
     */
    public static Jasoner create(JasonerConfig config) {
        return JasonerProvider.provider().createJasoner(config);
    }
}
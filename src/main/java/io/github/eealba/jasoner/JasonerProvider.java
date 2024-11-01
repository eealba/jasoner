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
 * The type Json provider.
 * @author Edgar Alba
 */
public abstract class JasonerProvider {
    /**
     * A constant representing the name of the default {@code JsonProvider}
     * implementation class.
     */
    private static final String DEFAULT_PROVIDER = "io.github.eealba.jasoner.internal.JasonerProviderImpl";

    /**
     * Creates a JSON provider object. If there are no available service providers,
     * this method returns the default service provider.
     *
     * @return a JSON provider
     */
    public static JasonerProvider provider() {
        return Providers.getProvider(JasonerProvider.class, DEFAULT_PROVIDER);
    }


    /**
     * Create jasoner jasoner.
     *
     * @param config the config
     * @return the jasoner
     */
    public abstract Jasoner createJasoner(JasonerConfig config);
}

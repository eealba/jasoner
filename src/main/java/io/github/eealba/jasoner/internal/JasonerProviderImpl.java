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
import io.github.eealba.jasoner.JasonerProvider;

/**
 * The class JasonerProviderImpl.
 * This class extends the JasonerProvider to provide an implementation for creating Jasoner instances.
 *
 * @since 1.0
 * @version 1.0
 *
 * @author Edgar Alba
 */
class JasonerProviderImpl extends JasonerProvider {

    /**
     * Creates a new Jasoner instance with the specified configuration.
     *
     * @param config the configuration for the Jasoner instance
     * @return the created Jasoner instance
     */
    @Override
    public Jasoner createJasoner(JasonerConfig config) {
        return new JasonerImpl(config);
    }
}
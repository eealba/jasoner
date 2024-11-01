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

import java.io.Serial;

/**
 * The type Provider not found exception.
 * This class is used to handle exceptions in the Jasoner library.
 * @author Edgar Alba
 */
public class ProviderNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Provider not found exception.
     *
     * @param providerName the provider name
     */
    public ProviderNotFoundException(String providerName) {
        super("Provider for " + providerName + " not found");
    }

    /**
     * Instantiates a new Provider not found exception.
     *
     * @param providerName the provider name
     * @param x            the x
     */
    public ProviderNotFoundException(String providerName, Exception x) {
        super("Provider for " + providerName + " not found", x);
    }

}

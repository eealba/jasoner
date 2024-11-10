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


import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * The type Json provider.
 *
 * @author Edgar Alba
 */
public abstract class JasonerProvider {
    /**
     * The providerImpl.
     */
    private static JasonerProvider providerImpl;
    /**
     * A constant representing the name of the default {@code JsonProvider}
     * implementation class.
     */
    private static final String DEFAULT_PROVIDER = "io.github.eealba.jasoner.internal.JasonerProviderImpl";

    /**
     * Instantiates a new Json provider.
     */
    public JasonerProvider() {
    }

    /**
     * Creates a JSON provider object. If there are no available service providers,
     * this method returns the default service provider.
     *
     * @return a JSON provider
     */
    public static JasonerProvider provider() {
        if (providerImpl != null) {
            return providerImpl;
        }
        try {
            Class<?> clazz = Class.forName(DEFAULT_PROVIDER);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> c : constructors) {
                if (c.getParameterCount() == 0) {
                    c.setAccessible(true);
                    providerImpl = (JasonerProvider) c.newInstance();
                    return providerImpl;
                }
            }
        } catch (Exception x) {
            throw new JasonerException("Provider for JasonerProvider not found", x);
        }
        throw new JasonerException("Provider for JasonerProvider not found");
    }


    /**
     * Create jasoner jasoner.
     *
     * @param config the config
     * @return the jasoner
     */
    public abstract Jasoner createJasoner(JasonerConfig config);

}

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
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Providers.
 *
 * @author Edgar Alba
 */
public class Providers {
    private static final Map<String, Object> providers = new ConcurrentHashMap<>();

    private Providers() {
    }

    /**
     * Gets provider.
     *
     * @param <T>             the type parameter
     * @param provider        the provider
     * @param defaultProvider the default provider
     * @return the provider
     */
    public static <T> T getProvider(Class<T> provider, String defaultProvider) {
        T providerImpl = provider.cast(providers.get(provider.getName()));

        if (providerImpl != null) {
            return providerImpl;
        }

        ServiceLoader<T> loader = ServiceLoader.load(provider);
        Iterator<T> it = loader.iterator();
        if (it.hasNext()) {
            providerImpl = it.next();
            providers.put(provider.getName(), providerImpl);
            return providerImpl;
        }

        try {
            Class<?> clazz = Class.forName(defaultProvider);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> c : constructors) {
                if (c.getParameterCount() == 0) {
                    c.setAccessible(true);
                    providerImpl = provider.cast(c.newInstance());
                    providers.put(provider.getName(), providerImpl);
                    return providerImpl;
                }
            }
        } catch (Exception x) {
            throw new ProviderNotFoundException(defaultProvider, x);
        }

        throw new ProviderNotFoundException(provider.getName());
    }

    /**
     * Register default provider.
     *
     * @param <T>          the type parameter
     * @param provider     the provider
     * @param providerImpl the provider
     */
    public static <T> void registerDefaultProvider(Class<T> provider, Object providerImpl) {
        providers.put(Objects.requireNonNull(provider).getName(), Objects.requireNonNull(providerImpl));
    }

    /**
     * Clear this loader's provider cache so that all providers will be reloaded.
     *
     * <p>
     * This method is intended for use in situations in which new providers can be
     * installed into a running Java virtual machine.
     */
    public static void clear() {
        providers.clear();
    }

}

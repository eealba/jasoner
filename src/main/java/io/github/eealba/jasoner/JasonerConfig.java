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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Configuration class for Jasoner.
 * This class holds various configuration options for the Jasoner library.
 * It includes settings for naming strategy, modifier strategy, charset, and more.
 *
 * @since 1.0
 * @version 1.0
 *
 * @see NamingStrategy
 * @see ModifierStrategy
 * @see SerializationStrategy
 *
 * @author Edgar Alba
 */
public class JasonerConfig {
    /**
     * Default configuration for Jasoner.
     */
    public final static JasonerConfig DEFAULT = new Builder().build();

    private final NamingStrategy namingStrategy;
    private final ModifierStrategy modifierStrategy;
    private final boolean removePrefixAccessors;
    private final Charset charset;
    private final SerializationStrategy serializationStrategy;
    private final boolean pretty;

    /**
     * Private constructor for JasonerConfig.
     *
     * @param builder the builder to create the configuration
     */
    private JasonerConfig(Builder builder) {
        this.namingStrategy = builder.namingStrategy;
        this.modifierStrategy = builder.modifierStrategy;
        this.removePrefixAccessors = builder.removePrefixAccessors;
        this.charset = builder.charset;
        this.serializationStrategy = builder.serializationStrategy;
        this.pretty = builder.pretty;
    }

    /**
     * Gets the naming strategy.
     *
     * @return the naming strategy
     */
    public NamingStrategy namingStrategy() {
        return namingStrategy;
    }

    /**
     * Gets the modifier strategy.
     *
     * @return the modifier strategy
     */
    public ModifierStrategy modifierStrategy() {
        return modifierStrategy;
    }

    /**
     * Checks if prefix accessors should be removed.
     *
     * @return true if prefix accessors should be removed, false otherwise
     */
    public boolean removePrefixAccessors() {
        return removePrefixAccessors;
    }

    /**
     * Gets the charset.
     *
     * @return the charset
     */
    public Charset charset() {
        return charset;
    }

    /**
     * Gets the serialization strategy.
     *
     * @return the serialization strategy
     */
    public SerializationStrategy serializationStrategy() {
        return serializationStrategy;
    }

    /**
     * Checks if JSON should be pretty printed.
     *
     * @return true if JSON should be pretty printed, false otherwise
     */
    public boolean pretty() {
        return pretty;
    }


    /**
     * Creates a new Builder instance for JasonerConfig.
     *
     * @return the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for JasonerConfig.
     * This class is used to build instances of JasonerConfig with custom settings.
     */
    public static class Builder {
        private boolean pretty;
        private NamingStrategy namingStrategy = NamingStrategy.CAMEL_CASE;
        private ModifierStrategy modifierStrategy = ModifierStrategy.PUBLIC;
        private boolean removePrefixAccessors = true;
        private Charset charset = StandardCharsets.UTF_8;
        private SerializationStrategy serializationStrategy = SerializationStrategy.BOTH;
        /**
         * Default constructor for Builder.
         */
        public Builder() {

        }
        /**
         * Sets the naming strategy.
         *
         * @param namingStrategy the naming strategy
         * @return the builder
         */
        public Builder namingStrategy(NamingStrategy namingStrategy) {
            this.namingStrategy = namingStrategy;
            return this;
        }

        /**
         * Sets the modifier strategy.
         *
         * @param modifierStrategy the modifier strategy
         * @return the builder
         */
        public Builder modifierStrategy(ModifierStrategy modifierStrategy) {
            this.modifierStrategy = modifierStrategy;
            return this;
        }

        /**
         * Sets whether to remove prefix accessors.
         *
         * @param removePrefixAccessors true to remove prefix accessors, false otherwise
         * @return the builder
         */
        public Builder removePrefixAccessors(boolean removePrefixAccessors) {
            this.removePrefixAccessors = removePrefixAccessors;
            return this;
        }

        /**
         * Sets the charset.
         *
         * @param charset the charset
         * @return the builder
         */
        public Builder charset(Charset charset) {
            this.charset = charset;
            return this;
        }

        /**
         * Sets the serialization strategy.
         *
         * @param serializationStrategy the serialization strategy
         * @return the builder
         */
        public Builder serializationStrategy(SerializationStrategy serializationStrategy) {
            this.serializationStrategy = serializationStrategy;
            return this;
        }

        /**
         * Sets whether to pretty print the JSON.
         *
         * @param pretty true to pretty print the JSON, false otherwise
         * @return the builder
         */
        public Builder pretty(boolean pretty) {
            this.pretty = pretty;
            return this;
        }


        /**
         * Builds the JasonerConfig.
         *
         * @return the JasonerConfig
         */
        public JasonerConfig build() {
            return new JasonerConfig(this);
        }
    }
}
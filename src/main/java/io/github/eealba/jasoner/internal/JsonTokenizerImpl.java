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

import java.util.List;

/**
 * The class JsonTokenizerImpl.
 * This class implements the JsonTokenizer interface to tokenize a JSON string.
 *
 * @since 1.0
 * @version 1.0
 *
 * @author Edgar Alba
 */
class JsonTokenizerImpl implements JsonTokenizer {
    /**
     * The Tokens.
     */
    private final List<Token> tokens;
    /**
     * The Pos.
     */
    private int pos;

    /**
     * Instantiates a new JsonTokenizerImpl.
     *
     * @param data the JSON string to tokenize
     */
    JsonTokenizerImpl(String data) {
        tokens = new Tokenizer(data).tokens();
    }

    /**
     * Gets the next token.
     *
     * @return the next token
     */
    @Override
    public Token next() {
        Token token = null;
        if (pos < tokens.size()) {
            token = tokens.get(pos++);
        }
        return token;
    }

    /**
     * Gets the current token.
     *
     * @return the current token
     */
    @Override
    public Token current() {
        Token token = null;
        if (pos != 0) {
            token = tokens.get(pos - 1);
        }
        return token;
    }

    /**
     * Checks if there are more tokens.
     *
     * @return true if there are more tokens, false otherwise
     */
    @Override
    public boolean hasNext() {
        return pos < tokens.size();
    }

    /**
     * Gets the list of tokens.
     *
     * @return the list of tokens
     */
    @Override
    public List<Token> tokens() {
        return tokens;
    }
}
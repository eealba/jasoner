package io.github.eealba.jasoner.internal;

import java.util.List;

/**
 * The type Json tokenizer.
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
     * Instantiates a new Json tokenizer.
     *
     * @param data the data
     */
    JsonTokenizerImpl(String data) {
        tokens = new Tokenizer(data).tokens();
    }

    /**
     * Next token.
     *
     * @return the token
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
     * Current token.
     *
     * @return the token
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
     * Has next boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean hasNext() {
        return pos < tokens.size();
    }

    /**
     * Tokens list.
     *
     * @return the list
     */
    @Override
    public List<Token> tokens() {
        return tokens;
    }

}

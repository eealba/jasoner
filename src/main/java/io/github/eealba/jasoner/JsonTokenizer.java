package io.github.eealba.jasoner;

import java.util.List;

/**
 * The interface Json tokenizer.
 */
public interface JsonTokenizer {
    Token next();
    Token current();
    boolean hasNext();
    List<Token> tokens();
}

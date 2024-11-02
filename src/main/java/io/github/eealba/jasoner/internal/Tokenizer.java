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

import io.github.eealba.jasoner.JasonerException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * The class Tokenizer.
 * This class is responsible for tokenizing a JSON string into a list of tokens.
 *
 * @since 1.0
 * @version 1.0
 *
 * @author Edgar Alba
 */
class Tokenizer {
    private static final String ERROR_UNEXPECTED_CHARACTER = "Unexpected token '%c' at position %d";
    private static final int EOF = -1;
    private static final int LIMIT_CONTROL = '\u001f';
    private static final String ERROR_UNEXPECTED_TOKEN = "Unexpected token '%s'";
    private static final String ERROR_EXPECTED_TOKEN = "Expected token '%s'";
    private final String data;
    private final int len;
    private int pos = -1;
    private int ch = EOF;
    private int countObjectToken;
    private int countArrayToken;
    private final List<Token> res = new ArrayList<>();

    /**
     * Instantiates a new Tokenizer.
     *
     * @param data the JSON string to tokenize
     */
    Tokenizer(String data) {
        this.data = Objects.requireNonNull(data);
        len = this.data.length();
    }

    /**
     * Tokenizes the JSON string into a list of tokens.
     *
     * @return the list of tokens
     */
    List<Token> tokens() {
        clear();
        Token token;
        try {
            while ((token = nextToken()) != null) {
                if (!res.isEmpty()) {
                    validateTokenWithPrevious(token, res.get(res.size() - 1));
                } else {
                    if (token.type() != TokenType.ARRAY_START && token.type() != TokenType.OBJECT_START) {
                        throw new JasonerException(String.format(ERROR_UNEXPECTED_TOKEN, token));
                    }
                }
                res.add(token);
            }
        } catch (IOException e) {
            throw new JasonerException(e);
        }
        if (countObjectToken > 0) {
            throw new JasonerException(String.format(ERROR_EXPECTED_TOKEN, "}"));
        } else if (countObjectToken < 0) {
            throw new JasonerException(String.format(ERROR_UNEXPECTED_TOKEN, "}"));
        }
        return Collections.unmodifiableList(res);
    }

    private void clear() {
        res.clear();
        countArrayToken = 0;
        countObjectToken = 0;
        pos = -1;
        ch = EOF;
    }

    private void validateTokenWithPrevious(Token token, Token previous) {
        switch (token.type()) {
            case ARRAY_START:
                if (previous.type() != TokenType.COLON && previous.type() != TokenType.COMMA) {
                    throw new JasonerException(String.format(ERROR_UNEXPECTED_TOKEN, token));
                }
                break;
            case OBJECT_START:
                if (previous.type() != TokenType.COLON && previous.type() != TokenType.COMMA
                        && previous.type() != TokenType.ARRAY_START) {
                    throw new JasonerException(String.format(ERROR_UNEXPECTED_TOKEN, token));
                }
                break;
            case COMMA:
                if (previous.type() == TokenType.OBJECT_START || token.type() == TokenType.ARRAY_START) {
                    throw new JasonerException(String.format(ERROR_UNEXPECTED_TOKEN, token));
                }
                break;
            case COLON:
                if (previous.type() != TokenType.TEXT) {
                    throw new JasonerException(String.format(ERROR_UNEXPECTED_TOKEN, token));
                }
                break;
            default:
                break;
        }
    }

    private Token nextToken() throws IOException {
        whitespaces();
        if (ch == EOF)
            return null;
        unRead();
        char _ch = readChar();
        if (res.isEmpty() && _ch != '{' && _ch != '[') {
            throw new JasonerException(String.format(ERROR_UNEXPECTED_TOKEN, _ch));
        }
        switch (_ch) {
            case '{':
                countObjectToken++;
                return TokenImpl.OBJECT_START;
            case '}':
                countObjectToken--;
                if (countObjectToken < 0) {
                    throw new JasonerException(String.format(ERROR_UNEXPECTED_CHARACTER, _ch, pos));
                }
                return TokenImpl.OBJECT_END;
            case '[':
                countArrayToken++;
                return TokenImpl.ARRAY_START;
            case ']':
                countArrayToken--;
                if (countArrayToken < 0) {
                    throw new JasonerException(String.format(ERROR_UNEXPECTED_CHARACTER, _ch, pos));
                }
                return TokenImpl.ARRAY_END;
            case ',':
                return TokenImpl.COMMA;
            case ':':
                return TokenImpl.COLON;
            case 't':
                unRead();
                return readExpected("true", TokenImpl.TRUE);
            case 'f':
                unRead();
                return readExpected("false", TokenImpl.FALSE);
            case 'n':
                unRead();
                return readExpected("null", TokenImpl.NULL);
            case '-':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                unRead();
                return numberToken();
            case '"':
                return stringToken();
        }
        throw new JasonerException(String.format(ERROR_UNEXPECTED_CHARACTER, _ch, pos));
    }

    private void whitespaces() {
        while (true) {
            read();
            switch (ch) {
                case ' ':
                case '\n':
                case '\r':
                case '\t':
                    continue;
                default:
                    return;
            }
        }
    }

    private void read() {
        pos++;
        ch = pos < len ? data.charAt(pos) : EOF;
    }

    private char readChar() {
        read();
        if (ch == EOF)
            throw new JasonerException("Unexpected end of file");
        return (char) ch;
    }

    private void unRead() {
        pos--;
    }

    private Token readExpected(final String val, Token token) {
        StringBuilder buff = new StringBuilder();
        final int vlen = val.length();
        for (int i = 0; i < vlen; i++) {
            buff.append(readChar());
        }
        if (val.contentEquals(buff)) {
            return token;
        }
        throw new JasonerException("Error, Expected token " + val + " but got " + val);
    }

    private Token numberToken() {
        StringBuilder buff = new StringBuilder();
        boolean exp = false;
        boolean execute = true;
        while (execute) {
            char ch = readChar();
            switch (ch) {
                case '+':
                    if (!buff.isEmpty()) {
                        throw new JasonerException(String.format(ERROR_UNEXPECTED_CHARACTER, ch, pos));
                    }
                    break;
                case '-':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    if (!buff.isEmpty() && ch == '-') {
                        throw new JasonerException(String.format(ERROR_UNEXPECTED_CHARACTER, ch, pos));
                    }
                    buff.append(ch);
                    break;
                case '.':
                    if (exp || buff.indexOf(".") != -1) {
                        throw new JasonerException(String.format(ERROR_UNEXPECTED_CHARACTER, ch, pos));
                    }
                    buff.append(ch);
                    break;
                case 'e':
                case 'E':
                    buff.append(ch);
                    exp = true;
                    break;
                default:
                    execute = false;
                    unRead();
                    break;
            }
        }
        return TokenImpl.createNumberToken(buff.toString());
    }

    private Token stringToken() {
        StringBuilder buff = new StringBuilder();
        boolean esc = false;
        while (true) {
            char ch = readChar();
            if (esc) {
                if (ch <= LIMIT_CONTROL) {
                    buff.append(ch);
                    esc = false;
                }
                switch (ch) {
                    case '"':
                    case '\\':
                    case '\b':
                    case '\f':
                    case '\n':
                    case '\r':
                    case '\t':
                        buff.append(ch);
                        esc = false;
                        break;
                    case 'u':
                        ch = readUnicodeSequence();
                        buff.append(ch);
                        esc = false;
                        break;
                }
            } else if (ch == '\\') {
                esc = true;
            } else if (ch == '"') {
                break;
            } else {
                buff.append(ch);
            }
        }
        return TokenImpl.createTextToken(buff.toString());
    }

    private char readUnicodeSequence() {
        StringBuilder buff = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            buff.append(readChar());
        }
        return (char) Integer.parseInt(buff.toString(), 16);
    }
}
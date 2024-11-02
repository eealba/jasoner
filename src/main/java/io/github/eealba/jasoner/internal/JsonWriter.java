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

import java.io.IOException;
import java.io.Writer;

/**
 * The class JsonWriter.
 * This class is responsible for writing JSON tokens to a writer with optional pretty printing.
 *
 * @since 1.0
 * @version 1.0
 *
 * @author Edgar Alba
 */
class JsonWriter {
    private final Writer writer;
    private final boolean pretty;
    private int indent = 0;

    /**
     * Instantiates a new JsonWriter.
     *
     * @param writer the writer to write JSON tokens to
     * @param pretty whether to pretty print the JSON
     */
    JsonWriter(Writer writer, boolean pretty) {
        this.writer = writer;
        this.pretty = pretty;
    }

    /**
     * Appends a JSON token to the writer.
     *
     * @param c the JSON token
     * @return the JsonWriter instance
     * @throws IOException if an I/O error occurs
     */
    JsonWriter append(Token c) throws IOException {
        putLeading(c);
        write(c);
        putTrailing(c);
        return this;
    }

    private void putLeading(Token c) throws IOException {
        if (pretty) {
            if (c.type() == TokenType.OBJECT_END || c.type() == TokenType.ARRAY_END) {
                indent--;
                appendNewLine();
                indent();
            }
        }
    }

    private void write(Token c) throws IOException {
        if (c.type() == TokenType.TEXT) {
            writer.write('"');
        }

        writer.write(c.stringValue());

        if (c.type() == TokenType.TEXT) {
            writer.write('"');
        }
    }

    private void putTrailing(Token c) throws IOException {
        if (pretty) {
            if (c.type() == TokenType.OBJECT_START || c.type() == TokenType.ARRAY_START) {
                indent++;
                appendNewLine();
                indent();
            }
            if (c.type() == TokenType.COMMA) {
                appendNewLine();
                indent();
            }
            if (c.type() == TokenType.COLON) {
                writer.write(' ');
            }
        }
    }

    private void appendNewLine() throws IOException {
        if (pretty) {
            writer.append('\n');
        }
    }

    private void indent() throws IOException {
        int localIndent = indent;
        if (pretty) {
            while (localIndent > 0) {
                writer.append("  ");
                --localIndent;
            }
        }
    }

    /**
     * Gets the string representation of the writer.
     *
     * @return the string representation of the writer
     */
    String writertoString() {
        return writer.toString();
    }
}
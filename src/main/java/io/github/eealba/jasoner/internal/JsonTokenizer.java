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
 * The interface Json tokenizer.
 * This interface is used to tokenize a JSON string.
 * @author Edgar Alba
 */
interface JsonTokenizer {
    Token next();
    Token current();
    boolean hasNext();
    List<Token> tokens();
}

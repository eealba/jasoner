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
 * The type JasonerException.
 * This class is used to handle exceptions in the Jasoner library.
 *
 * @since 1.0
 * @version 1.0
 *
 * @see RuntimeException
 * @see Throwable
 * @see Serial
 *
 * @author Edgar Alba
 */
public class JasonerException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 5997360855269832676L;

    /**
     * Instantiates a new JasonerException with a message and a cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public JasonerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new JasonerException with a message.
     *
     * @param message the detail message
     */
    public JasonerException(String message) {
        super(message);
    }

    /**
     * Instantiates a new JasonerException with a cause.
     *
     * @param cause the cause of the exception
     */
    public JasonerException(Throwable cause) {
        super(cause);
    }
}
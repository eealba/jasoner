package io.github.eealba.jasoner;

/**
 * The type Json exception.
 */
public class JsonException extends RuntimeException {

	private static final long serialVersionUID = 5997360855269832676L;

    /**
     * Instantiates a new Json exception.
     *
     * @param s the s
     * @param x the x
     */
    public JsonException(String s, Throwable x) {
		super(s, x);
	}

    /**
     * Instantiates a new Json exception.
     *
     * @param s the s
     */
    public JsonException(String s) {
		super(s);
	}

    /**
     * Instantiates a new Json exception.
     *
     * @param x the x
     */
    public JsonException(Throwable x) {
		super(x);
	}

}

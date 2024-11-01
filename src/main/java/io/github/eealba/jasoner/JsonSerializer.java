package io.github.eealba.jasoner;

import java.nio.charset.StandardCharsets;

/**
 * The interface Json serializer.
 */
public interface JsonSerializer {

    /**
     * Serialize string.
     *
     * @param obj the obj
     * @return the string
     */
    String serialize(Object obj);

    /**
     * Serialize to byte array byte [ ].
     *
     * @param obj the obj
     * @return the byte [ ]
     */
    default byte[] serializeToByteArray(Object obj) {
		return serialize(obj).getBytes(StandardCharsets.UTF_8);
	}

}

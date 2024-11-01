package io.github.eealba.jasoner;

/**
 * A deserializer receives a json String and converts it to a Java entity.
 *
 */
public interface JsonDeserializer {
    /**
     * Returns an entity T from String
     *
     * @param data A String data
     * @return Returns an entity T
     */
    <T> T deserialize(String data, Class<T> clazz);

}

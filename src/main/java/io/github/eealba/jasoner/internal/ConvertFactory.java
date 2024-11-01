package io.github.eealba.jasoner.internal;

import java.time.Instant;

class ConvertFactory {

        public static Convert<Object, ?> getConverter(Class<?> type) {
            if (type == Instant.class) {
                return new ConvertInstant();
            }
            if (type.isRecord() && type.getDeclaredConstructors()[0].getParameters().length == 1) {
                return new ConvertSimpleRecord<>(type);
            }
            return (Convert<Object, Object>) obj -> obj;
        }

}

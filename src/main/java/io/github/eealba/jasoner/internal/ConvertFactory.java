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

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * The class ConvertFactory.
 * This class is used to create instances of the Convert interface based on the type of the input object.
 *
 * @author Edgar Alba
 * @version 1.0
 * @since 1.0
 */
class ConvertFactory {

    /**
     * Gets the converter for the specified type.
     *
     * @param type the class type of the input object
     * @return the converter for the specified type
     */
    public static Convert<Object, ?> getConverter(Class<?> type) {
        //Basic converters

        String name = type.getSimpleName();
        Convert<Object, ?> convert = switch (name) {
            case "byte", "Byte" -> (Object data) -> Byte.parseByte(data.toString());
            case "short", "Short" -> (Object data) -> Short.parseShort(data.toString());
            case "char", "Character" -> (Object data) -> data.toString().charAt(0);
            case "int", "Integer" -> (Object data) -> Integer.parseInt(data.toString());
            case "long", "Long" -> (Object data) -> Long.parseLong(data.toString());
            case "float", "Float" -> (Object data) -> Float.parseFloat(data.toString());
            case "double", "Double" -> (Object data) -> Double.parseDouble(data.toString());
            case "BigDecimal" -> (Object data) -> numericValue(data.toString());
            case "boolean", "Boolean" -> (Object data) -> Boolean.parseBoolean(data.toString());
            case "LocalDate" -> (Object data) -> LocalDate.parse(data.toString());
            case "LocalDateTime" -> (Object data) -> LocalDateTime.parse(data.toString());
            case "LocalTime" -> (Object data) -> LocalTime.parse(data.toString());
            case "Date" -> (Object data) -> Date.from(Instant.parse(data.toString()));
            case "OffsetDateTime" -> (Object data) -> OffsetDateTime.parse(data.toString());
            case "UUID" -> (Object data) -> UUID.fromString(data.toString());
            case "ZoneOffset" -> (Object data) -> ZoneOffset.of(data.toString());
            case "URI" -> (Object data) -> URI.create(data.toString());
            default -> null;
        };
        if (convert != null) {
            return convert;
        }
        if (type == Instant.class) {
            return new ConvertInstant();
        }
        if (type.isRecord() && type.getDeclaredConstructors()[0].getParameters().length == 1) {
            return new ConvertSimpleRecord<>(type);
        }
        return (Convert<Object, Object>) obj -> obj;
    }

    private static BigDecimal numericValue(String data) {
        return getPowerDelimiter(data).map((Integer pos) ->
                        new BigDecimal(data.substring(0, pos)).pow(Integer.parseInt(data.substring(pos + 1))))
                .orElseGet(() -> new BigDecimal(data));
    }

    private static Optional<Integer> getPowerDelimiter(String data) {
        int pos = data.toLowerCase().indexOf("e");
        if (pos == -1) {
            return Optional.empty();
        }
        return Optional.of(pos);
    }

}
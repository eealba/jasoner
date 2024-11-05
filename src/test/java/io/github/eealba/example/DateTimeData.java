package io.github.eealba.example;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Data
public class DateTimeData {
    LocalDate localDate;
    LocalDateTime localDateTime;
    LocalTime localTime;
    Date date;
    OffsetDateTime offsetDateTime;
    Instant instant;

    public static DateTimeData minValues() {
        DateTimeData data = new DateTimeData();
        data.setLocalDate(LocalDate.MIN);
        data.setLocalDateTime(LocalDateTime.MIN);
        data.setLocalTime(LocalTime.MIN);
        data.setDate(new Date(Long.MIN_VALUE));
        data.setOffsetDateTime(OffsetDateTime.MIN);
        data.setInstant(Instant.MIN);
        return data;
    }
    public static DateTimeData firstJanuary() {
        DateTimeData data = new DateTimeData();
        data.setLocalDate(LocalDate.of(2021, 1, 1));
        data.setLocalDateTime(LocalDateTime.of(2021, 1, 1, 0, 0));
        data.setLocalTime(LocalTime.of(0, 0));
        data.setDate(new Date(1609459200000L));
        data.setOffsetDateTime(OffsetDateTime.of(2021, 1, 1, 0, 0, 0, 0, OffsetDateTime.now().getOffset()));
        data.setInstant(Instant.ofEpochMilli(1609459200000L));
        return data;
    }
}

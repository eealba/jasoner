package io.github.eealba.example;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
@Builder
@Getter
public class ImmutablePerson {
    private final String name;
    private final int age;
    private final boolean developer;
    private final List<String> hobbies;
    private final Map<String, String> socialMedia;
}

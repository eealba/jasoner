package io.github.eealba.jasoner.demo.model1;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Person {
    private String name;
    private int age;
    private boolean developer;
    private List<String> hobbies;
    private Map<String, String> socialMedia;
}

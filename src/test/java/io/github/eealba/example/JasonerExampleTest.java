package io.github.eealba.example;

import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;
import io.github.eealba.jasoner.JsonbCompatibilityTest;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JasonerExampleTest {
    @Test
    void should_serialize_and_deserialize_person() throws JSONException {
        Jasoner jasoner = JasonerBuilder.create();

        String expected = "{\"name\":\"John\",\"age\":30,\"developer\":true,\"hobbies\":[\"Soccer\",\"Guitar\"],\"socialMedia\":{\"twitter\":\"@john\",\"linkedin\":\"john\"}}";
        Person person = person();

        // Serialize
        String result = jasoner.toJson(person);
        // Verify the result
        JSONAssert.assertEquals(expected, result, true);
        // Deserialize back
        person = jasoner.fromJson(expected, Person.class);

        verifyPerson(person);
    }

    @Test
    void should_deserialize_person_to_map() throws JSONException {
        String expected = "{\"name\":\"John\",\"age\":30,\"developer\":true,\"hobbies\":[\"Soccer\",\"Guitar\"],\"socialMedia\":{\"twitter\":\"@john\",\"linkedin\":\"john\"}}";
        Jasoner jasoner = JasonerBuilder.create();
        // Deserialize back to a map
        var person = jasoner.fromJson(expected, Map.class);

        assertEquals("John", person.get("name"));
        assertEquals(BigDecimal.valueOf(30), person.get("age"));
        assertTrue((boolean) person.get("developer"));
        assertEquals(List.of("Soccer", "Guitar"), person.get("hobbies"));
        assertEquals(Map.of("twitter", "@john", "linkedin", "john"), person.get("socialMedia"));
    }

    @Test
    void should_mapping_a_collection() throws JSONException {

        Jasoner jasoner = JasonerBuilder.create();
        List<Person> persons = List.of(person(), person());
        String expected = "[{\"name\":\"John\",\"age\":30,\"developer\":true,\"hobbies\":[\"Soccer\",\"Guitar\"],\"socialMedia\":{\"twitter\":\"@john\",\"linkedin\":\"john\"}},{\"name\":\"John\",\"age\":30,\"developer\":true,\"hobbies\":[\"Soccer\",\"Guitar\"],\"socialMedia\":{\"twitter\":\"@john\",\"linkedin\":\"john\"}}]";
        // Serialize
        String result = jasoner.toJson(persons);
        // Verify the result
        JSONAssert.assertEquals(expected, result, true);
        // Deserialize back
        persons = jasoner.fromJson(expected, new ArrayList<Person>(){}.getClass().getGenericSuperclass());
        verifyPerson(persons.get(0));
        verifyPerson(persons.get(1));
    }
    @Test
    void should_mapping_a_collection2() throws JSONException {
        // Create a new Jasoner instance
        Jasoner jasoner = JasonerBuilder.create();

        // Create a list of Person objects
        List<Person> persons = List.of(
                new Person("John", 30, true, List.of("Soccer", "Guitar"), Map.of("twitter", "@john", "linkedin", "john")),
                new Person("Jane", 25, false, List.of("Reading", "Painting"), Map.of("twitter", "@jane", "linkedin", "jane"))
        );

        // Serialize the list of Person objects to a JSON string
        String json = jasoner.toJson(persons);
        System.out.println(json);
        // Deserialize the JSON string to a list of Person objects
        List<Person> newPersons = jasoner.fromJson(json, new ArrayList<Person>(){}.getClass().getGenericSuperclass());

        assertEquals(2, newPersons.size());
        assertEquals("John", newPersons.get(0).getName());
        assertEquals(30, newPersons.get(0).getAge());
    }

    private static Person person() {
        Person person = new Person();
        person.setName("John");
        person.setAge(30);
        person.setDeveloper(true);
        person.setHobbies(List.of("Soccer", "Guitar"));
        person.setSocialMedia(Map.of("twitter", "@john", "linkedin", "john"));
        return person;
    }

    private static void verifyPerson(Person person) {
        assertEquals("John", person.getName());
        assertEquals(30, person.getAge());
        assertTrue(person.isDeveloper());
        assertEquals(List.of("Soccer", "Guitar"), person.getHobbies());
        assertEquals(Map.of("twitter", "@john", "linkedin", "john"), person.getSocialMedia());
    }

}

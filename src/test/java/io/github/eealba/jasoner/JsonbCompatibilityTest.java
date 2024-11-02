package io.github.eealba.jasoner;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class JsonbCompatibilityTest {
    private static final Jasoner jasoner = JasonerBuilder.create();

    @Test
    void should_serialize_and_deserialize() {
        var expected = "{\"name\":\"Falco\",\"age\":4,\"bites\":false}";
        Dog dog = falco();
        //serialize
        String result = jasoner.toJson(dog);
        // Verify the result
        assertEquals(expected, result);
        // Deserialize back
        dog = jasoner.fromJson(expected, Dog.class);
        assertEquals("Falco", dog.name);
        assertEquals(4, dog.age);
        assertFalse(dog.bites);
    }

    @Test
    void should_serialize_and_deserialize_raw_list() {
        String result = serializeDogsList();

        // We can also deserialize back into a raw collection, but since there is no way to infer a type here,
        // the result will be a list of java.util.Map instances with string keys.
        var arrayList = jasoner.fromJson(result, ArrayList.class);

        assertEquals(2, arrayList.size());
        assertInstanceOf(Map.class, arrayList.get(0));
        assertEquals("Falco", ((Map) arrayList.get(0)).get("name"));
    }
    @Test
    void should_serialize_and_deserialize_dog_list() {
        String result = serializeDogsList();

        // We can also deserialize back into a raw collection, but since there is no way to infer a type here,
        // the result will be a list of java.util.Map instances with string keys.
        List<Dog> arrayList = jasoner.fromJson(result, new ArrayList<Dog>(){}.getClass().getGenericSuperclass());

        assertEquals(2, arrayList.size());
        assertInstanceOf(Dog.class, arrayList.get(0));
        assertEquals("Falco", arrayList.get(0).name);
    }


    @SuppressWarnings("unchecked")
    private static String serializeDogsList() {
        // List of dogs
        @SuppressWarnings("rawtypes")
        List dogs = new ArrayList();
        dogs.add(falco());
        dogs.add(cassidy());

        // Serialize
        return jasoner.toJson(dogs);
    }

    private static Dog falco() {
        Dog dog = new Dog();
        dog.name = "Falco";
        dog.age = 4;
        dog.bites = false;
        return dog;
    }
    private static Dog cassidy() {
        Dog dog = new Dog();
        dog.name = "Cassidy";
        dog.age = 2;
        dog.bites = false;
        return dog;
    }

    public static class Dog {
        public String name;
        public int age;
        public boolean bites;
    }
}

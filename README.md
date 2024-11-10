# JaSONer - A Spartan JSON Library
[![Coverage](.github/badges/jacoco.svg)](https://github.com/eealba/jasoner/actions/workflows/github_action.yaml)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.eealba/jasoner.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.eealba/jasoner)
[![Javadoc](https://javadoc.io/badge2/io.github.eealba/jasoner/javadoc.io.svg)](https://javadoc.io/doc/io.github.eealba/jasoner)
[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](https://opensource.org/licenses/Apache-2.0)
[![Java Version](https://img.shields.io/badge/Java-17%2B-brightgreen)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Build Status](https://github.com/eealba/jasoner/actions/workflows/github_action.yaml/badge.svg)](https://github.com/eealba/jasoner/actions)


JaSONer is a lightweight and fast JSON library designed for simplicity and 
performance. It offers a user-friendly API compatible with Json-B, making it 
easy to integrate into your Java projects. Built with Java 17+, JaSONer is 
thread-safe and ideal for handling immutable objects, DTOs, and POJOs.

### Maven

```xml
<dependency>
    <groupId>io.github.eealba</groupId>
    <artifactId>jasoner</artifactId>
    <version>0.4.0</version>
</dependency>

```
### Usage
Here's a simple example of how to use Jasoner:

```java
import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;
//...
// Create a new Jasoner instance
Jasoner jasoner = JasonerBuilder.create();
```
#### Mapping an object
```java
import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;

public class Person {
    private String name;
    private int age;
    private boolean developer;
    private List<String> hobbies;
    private Map<String, String> socialMedia;

    // Getters and Setters
    //...
}

public class Main {
    public static void main(String[] args) {
        // Create a new Jasoner instance
        Jasoner jasoner = JasonerBuilder.create();

        // Create a new Person object
        Person person = new Person();
        person.setName("John");
        person.setAge(30);
        person.setDeveloper(true);
        person.setHobbies(List.of("Soccer", "Guitar"));
        person.setSocialMedia(Map.of("twitter", "@john", "linkedin", "john"));

        // Serialize the Person object to a JSON string
        String json = jasoner.toJson(person);
        System.out.println(json);
        // Deserialize the JSON string to a Person object
        Person newPerson = jasoner.fromJson(json, Person.class);
    }
}
```
#### Mapping a Collection
```java
import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;

public class Main {
    public static void main(String[] args) {
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
    }
}
```
#### Mapping a Map
```java
    String expected = "{\"name\":\"John\",\"age\":30,\"developer\":true,\"hobbies\":[\"Soccer\",\"Guitar\"],\"socialMedia\":{\"twitter\":\"@john\",\"linkedin\":\"john\"}}";
    Jasoner jasoner = JasonerBuilder.create();
    // Deserialize back to a map
    Map person = jasoner.fromJson(expected, Map.class);
```

#### Mapping an immutable object with Joshua Bloch Builder Pattern (example with Lombok)
```java
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


public class Main {
    public static void main(String[] args) {
        String expected = "{\"name\":\"John\",\"age\":30,\"developer\":true,\"hobbies\":[\"Soccer\",\"Guitar\"],\"socialMedia\":{\"twitter\":\"@john\",\"linkedin\":\"john\"}}";
        // Create a new Jasoner instance
        Jasoner jasoner = JasonerBuilder.create();
        // Deserialize back to a ImmutablePerson
        ImmutablePerson person = jasoner.fromJson(expected, ImmutablePerson.class);
    }
}
```





#### Changing property names with annotations in fields
```java
public class Address {
    @JasonerProperty("calle")
    public String street;

    @JasonerProperty("ciudad")
    public String city;

    public String state;
    public String zip;
}
```
#### Changing property names with annotations in methods
```java
public class Address {
    private String street;
    private String city;
    private String state;
    private String zip;

    @JasonerProperty("calle")
    public String getStreet() {
        return street;
    }

    @JasonerProperty("ciudad")
    public String getCity() {
        return city;
    }
}
```
#### Naming Strategy
Jasoner provides various naming strategies to customize the JSON property names during serialization and deserialization. You can choose from the following naming strategies:  
 
 - **CAMEL_CASE**: Converts property names to camel case (e.g., FirstName becomes firstName).
 - **SNAKE_CASE**: Converts property names to snake case (e.g., firstName becomes first\_name).
 - **LOWER_CASE**: Converts property names to lower case.
 - **UPPER_CASE**: Converts property names to upper case.
 - **UPPER_SNAKE_CASE**: Converts property names to upper snake case (e.g., firstName becomes FIRST\_NAME).
 - **KEBAB_CASE**: Converts property names to kebab case (e.g., firstName becomes first-name).
 - **UPPER_KEBAB_CASE**: Converts property names to upper kebab case (e.g., firstName becomes FIRST-NAME).
- **NONE**: No naming strategy applied.

To use a naming strategy, configure it when creating the Jasoner instance:
```java
Jasoner jasoner = JasonerBuilder.create(new JasonerConfig.Builder().namingStrategy(NamingStrategy.SNAKE_CASE).build());
```

### Default Serialization and Deserialization Process in Jasoner

By default, Jasoner serializes and deserializes objects by accessing their public fields and methods. This means that only the fields and methods with public access modifiers are considered during the JSON conversion process.

### Changing the Behavior with `ModifierStrategy`

To change the default behavior, you can use the `ModifierStrategy` enum to specify which fields and methods should be included during serialization and deserialization. The available strategies are:

- `PUBLIC`: Only public fields and methods are included.
- `PROTECTED`: Protected and public fields and methods are included.
- `PACKAGE`: Package-private, protected, and public fields and methods are included.
- `PRIVATE`: All fields and methods, including private ones, are included.

### Example

Here is an example of how to configure Jasoner to use a different `ModifierStrategy`:

```java
import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;
import io.github.eealba.jasoner.ModifierStrategy;

public class Main {
    public static void main(String[] args) {
        // Create a new Jasoner instance with a specific modifier strategy
        Jasoner jasoner = JasonerBuilder.create(new JasonerConfig.Builder()
                .modifierStrategy(ModifierStrategy.PRIVATE)
                .build());


        // Example usage
        Person person = new Person("John", 30);
        String json = jasoner.toJson(person);
        System.out.println(json);

        Person deserializedPerson = jasoner.fromJson(json, Person.class);
        System.out.println(deserializedPerson.getName());
    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

In this example, the `ModifierStrategy.PRIVATE` strategy is used, which means that all fields and methods, including private ones, will be included during serialization and deserialization.

### `JasonerTransient` Annotation

The `JasonerTransient` annotation is used to mark fields or methods that should be ignored during JSON serialization and deserialization. When a field or method is annotated with `JasonerTransient`, it will not be included in the JSON output and will not be populated from the JSON input.

### Example

Here is an example demonstrating how to use the `JasonerTransient` annotation:

```java
import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;
import io.github.eealba.jasoner.JasonerTransient;

public class Main {
    public static void main(String[] args) {
        // Create a new Jasoner instance
        Jasoner jasoner = JasonerBuilder.create();

        // Create a new User object
        User user = new User("John", "password123", "john@example.com");

        // Serialize the User object to a JSON string
        String json = jasoner.toJson(user);
        System.out.println("Serialized JSON: " + json);

        // Deserialize the JSON string back to a User object
        User deserializedUser = jasoner.fromJson(json, User.class);
        System.out.println("Deserialized User: " + deserializedUser.getName());
    }
}

class User {
    public String name;

    @JasonerTransient
    public String password;

    public String email;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
```

In this example, the `password` field is annotated with `JasonerTransient`, so it will be ignored during serialization and deserialization. The resulting JSON string will not include the `password` field, and the `password` field will not be populated when deserializing the JSON string back to a `User` object.


### Features
- **Lightweight**: Zero dependencies and small footprint.
- **Fast**: Optimized for performance.
- **Easy to Use**: Simple API for common JSON operations, Api syntax compatible with Json-B.
- **Compliant**: Adheres to JSON standards.
- **Java 17+**: Built with Java 17+.
- **Thread-safe**: Jasoner is thread-safe.
- **Ideal for**: Immutable objects, DTOs, and POJOs.


### Support Mapping
- **POJOs**: Plain Old Java Objects.
- **DTOs**: Data Transfer Objects.
- **Collections**: Lists and Maps.
- **Arrays**: Primitive and Object arrays.
- **Enums**: Enumerations.
- **Records**: Java Records.
- **Joshua Bloch's Builder Pattern**: Perfect for immutable objects.
- **Value Objects**: Objects with value semantics.


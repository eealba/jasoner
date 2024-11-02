# JaSONer - A Spartan JSON Library

## Features
- **Lightweight**: Minimal dependencies and small footprint.
- **Fast**: Optimized for performance.
- **Flexible**: Supports custom serialization and deserialization.
- **Easy to Use**: Simple API for common JSON operations.
- **Compliant**: Adheres to JSON standards.
- **Java 17**: Built with Java 17.
- **Open Source**: Apache License 2.0.
- **thread-safe**: Jasoner is thread-safe.
- **Ideal for**: Immutable objects, DTOs, and POJOs.

 
## Support Mapping
- **POJOs**: Plain Old Java Objects.
- **DTOs**: Data Transfer Objects.
- **Collections**: Lists and Maps.
- **Arrays**: Primitive and Object arrays.
- **Enums**: Enumerations.
- **Records**: Java Records.
- **Joshua Bloch**: Builder Pattern.

## Installation
Add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>io.github.eealba</groupId>
    <artifactId>jasoner</artifactId>
    <version>0.1.0</version>
</dependency>

```
## Usage
Here's a simple example of how to use Jasoner:

```java
import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;
//...
// Create a new Jasoner instance
Jasoner jasoner = JasonerBuilder.create();
```
Mapping an object
```java
import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;

public class Person {
    private String name;
    private int age;
    private boolean isDeveloper;
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
        person.setName("John Doe");
        person.setAge(30);
        person.setDeveloper(true);
        person.setHobbies(List.of("Reading", "Gaming", "Traveling"));
        person.setSocialMedia(Map.of("Twitter", "@johndoe", "GitHub", "johndoe"));

        // Serialize the Person object to a JSON string
        String json = jasoner.toJson(person);
        System.out.println(json);
    }
}
```

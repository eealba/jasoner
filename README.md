# JaSONer - A Spartan JSON Library

## Features
- **Lightweight**: Zero dependencies and small footprint.
- **Fast**: Optimized for performance.
- **Easy to Use**: Simple API for common JSON operations, Api syntax compatible with Json-B.
- **Compliant**: Adheres to JSON standards.
- **Java 17+**: Built with Java 17+.
- **thread-safe**: Jasoner is thread-safe.
- **Ideal for**: Immutable objects, DTOs, and POJOs.

 
## Support Mapping
- **POJOs**: Plain Old Java Objects.
- **DTOs**: Data Transfer Objects.
- **Collections**: Lists and Maps.
- **Arrays**: Primitive and Object arrays.
- **Enums**: Enumerations.
- **Records**: Java Records.
- **Joshua Bloch Builder Pattern**: Perfect for immutable objects.
- **Value Objects**: Objects with value semantics.

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
Mapping a collection
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
Changing property names with annotations in fields
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
Changing property names with annotations in methods
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
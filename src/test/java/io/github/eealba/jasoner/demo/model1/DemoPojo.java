package io.github.eealba.jasoner.demo.model1;

/**
 * The type Demo pojo.
 * For testing java modifier strategy enum (public, protected, package, private)
 */
public class DemoPojo {
    private String name;
    protected String lastName;
    int age;
    public String address;

    public static DemoPojo joeDoe() {
        return new DemoPojo()
                .setName("John")
                .setLastName("Doe")
                .setAge(30)
                .setAddress("New York");
    }

    private String name() {
        return name;
    }

    DemoPojo setName(String name) {
        this.name = name;
        return this;
    }

    protected String lastName() {
        return lastName;
    }

    protected DemoPojo setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    int age() {
        return age;
    }

    DemoPojo setAge(int age) {
        this.age = age;
        return this;
    }

    public String address() {
        return address;
    }

    public DemoPojo setAddress(String address) {
        this.address = address;
        return this;
    }
}

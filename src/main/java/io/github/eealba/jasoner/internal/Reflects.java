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

import io.github.eealba.jasoner.JasonerException;
import io.github.eealba.jasoner.JasonerProperty;
import io.github.eealba.jasoner.JasonerTransient;
import io.github.eealba.jasoner.JsonObject;
import io.github.eealba.jasoner.ModifierStrategy;
import io.github.eealba.jasoner.NamingStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * The class Reflects.
 * This class provides utility methods for reflection operations such as getting methods, fields, and creating
 * instances.
 *
 * @author Edgar Alba
 * @version 1.0
 * @since 1.0
 */
class Reflects {
    private static final Predicate<Method> staticMethod = (Method method) -> Modifier.isStatic(method.getModifiers());
    private static final Predicate<Method> instanceMethod =
            (Method method) -> !Modifier.isStatic(method.getModifiers());

    private static final Predicate<Method> publicMethod = (Method method) -> Modifier.isPublic(method.getModifiers());
    private static final Predicate<Method> protectedMethod =
            (Method method) -> Modifier.isProtected(method.getModifiers());
    private static final Predicate<Method> privateMethod = (Method method) -> Modifier.isPrivate(method.getModifiers());

    private static final Predicate<Field> publicField = (Field field) -> Modifier.isPublic(field.getModifiers());
    private static final Predicate<Field> protectedField = (Field field) -> Modifier.isProtected(field.getModifiers());
    private static final Predicate<Field> privateField = (Field field) -> Modifier.isPrivate(field.getModifiers());

    private static final Predicate<Method> onceParameterMethod = (Method method) -> method.getParameterCount() == 1;
    private static final Predicate<Method> noParameterMethod = (Method method) -> method.getParameterCount() == 0;
    private static final Predicate<Method> returnValueMethod = (Method method) -> method.getReturnType() != Void.TYPE;

    private static final Predicate<Method> noOverridedObjectMethod = (Method method) -> !(method.getName().equals(
            "hashCode")
            || method.getName().equals("toString"));

    private static final Predicate<Method> setterMethod = instanceMethod.and(onceParameterMethod);
    private static final Predicate<Method> getterMethod = instanceMethod
            .and(noParameterMethod)
            .and(returnValueMethod)
            .and(noOverridedObjectMethod);

    private static final BiPredicate<Parameter, String> hasParameterName = (Parameter p, String name) ->
            p.getName().equals(name)
                    || p.getName().equals(NamingFactory.get(NamingStrategy.CAMEL_CASE).apply(name))
                    || p.getName().equals(NamingFactory.get(NamingStrategy.SNAKE_CASE).apply(name));

    /**
     * Gets methods.
     *
     * @param entity the entity
     * @return the methods
     */
    static List<Method> getMethods(Object entity) {
        return getMethods(entity, m -> true);
    }

    /**
     * Gets methods.
     *
     * @param entity    the entity
     * @param predicate the predicate
     * @return the methods
     */
    static List<Method> getMethods(Object entity, Predicate<Method> predicate) {
        Method[] methods = entity.getClass().getDeclaredMethods();
        List<Method> list = new ArrayList<>();
        for (Method m : methods) {
            if (predicate.test(m)) {
                list.add(m);
            }
        }
        return list;
    }

    /**
     * Gets getter methods.
     *
     * @param entity           the entity
     * @param modifierStrategy the modifier strategy
     * @return the getter methods
     */
    static List<Method> getGetterMethods(Object entity, ModifierStrategy modifierStrategy) {
        var predicate = getterMethod.and(ignoreTransientMethod());
        if (modifierStrategy == ModifierStrategy.PUBLIC) {
            predicate = predicate.and(publicMethod);
        }
        if (modifierStrategy == ModifierStrategy.PROTECTED) {
            predicate = predicate.and(publicMethod.or(protectedMethod));
        }
        if (modifierStrategy == ModifierStrategy.PACKAGE) {
            predicate = predicate.and(privateMethod.negate());
        }
        return getMethods(entity, predicate);
    }

    /**
     * Gets the parameter class of a setter method.
     *
     * @param entity the entity
     * @param name   the name of the property
     * @return an optional containing the parameter class, or empty if not found
     */
    static Optional<Class<?>> getSetterMethodParameterClass(Object entity, String name) {
        return getSetterMethod(entity, name, null).map(m -> m.getParameters()[0]).map(Reflects::getClass);
    }

    /**
     * Gets the setter method.
     *
     * @param entity the entity
     * @param name   the name of the property
     * @param value  the value to set
     * @return an optional containing the setter method, or empty if not found
     */
    static Optional<Method> getSetterMethod(Object entity, String name, Object value) {
        var methods = getMethods(entity, setterMethod).stream()
                                                      .filter(m -> value == null || m.getParameterTypes()[0].isInstance(value))
                                                      .filter(filterMethodName(name).or(filterMethodWithJasonerProperty(name)))
                                                      .filter(ignoreTransientMethod())
                                                      .toList();
        return methods.stream().findFirst();
    }

    private static Predicate<Method> filterMethodName(String name) {
        return m -> m.getName().equals(name)
                || m.getName().equals(NamingFactory.get(NamingStrategy.CAMEL_CASE).apply(name))
                || m.getName().equals(NamingFactory.get(NamingStrategy.SNAKE_CASE).apply(name))
                || m.getName().equals("set" + capitalizeFirstLetter(
                NamingFactory.get(NamingStrategy.CAMEL_CASE).apply(name)))
                || m.getName().equals("set" + capitalizeFirstLetter(
                NamingFactory.get(NamingStrategy.SNAKE_CASE).apply(name)));
    }

    private static Predicate<Method> ignoreTransientMethod() {
        return m -> m.getDeclaredAnnotation(JasonerTransient.class) == null;
    }

    private static Predicate<Method> filterMethodWithJasonerProperty(String name) {
        return m -> {
            var annotations = m.getDeclaredAnnotation(JasonerProperty.class);
            var mName = annotations != null ? annotations.value() : m.getName();

            return mName.equals(name)
                    || mName.equals(NamingFactory.get(NamingStrategy.CAMEL_CASE).apply(name))
                    || mName.equals(NamingFactory.get(NamingStrategy.SNAKE_CASE).apply(name))
                    || mName.equals("set" + capitalizeFirstLetter(NamingFactory.get(NamingStrategy.CAMEL_CASE).apply(name)))
                    || mName.equals("set" + capitalizeFirstLetter(NamingFactory.get(NamingStrategy.SNAKE_CASE).apply(name)));
        };
    }

    /**
     * Capitalizes the first letter of a string.
     *
     * @param name the string to capitalize
     * @return the capitalized string
     */
    static String capitalizeFirstLetter(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    /**
     * Gets fields.
     *
     * @param entity the entity
     * @return the fields
     */
    static List<Field> getFields(Object entity) {
        return getAllFields(new ArrayList<>(), entity.getClass());
    }

    /**
     * Gets all fields of a class, including inherited fields.
     *
     * @param fields the list of fields
     * @param type   the class type
     * @return the list of fields
     */
    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

    /**
     * Gets fields with a specific modifier strategy.
     *
     * @param entity           the entity
     * @param modifierStrategy the modifier strategy
     * @return the fields
     */
    static List<Field> getFields(Object entity, ModifierStrategy modifierStrategy) {
        var fields = getFields(entity);
        Predicate<Field> predicate = ignoreTransientField();
        if (modifierStrategy == ModifierStrategy.PUBLIC) {
            predicate = predicate.and(publicField);
        }
        if (modifierStrategy == ModifierStrategy.PROTECTED) {
            predicate = predicate.and(publicField.or(protectedField));
        }
        if (modifierStrategy == ModifierStrategy.PACKAGE) {
            predicate = predicate.and(privateField.negate());
        }

        return fields.stream().filter(predicate).toList();
    }

    /**
     * Gets a field by name.
     *
     * @param entity the entity
     * @param name   the name of the field
     * @return an optional containing the field, or empty if not found
     */
    static Optional<Field> getField(Object entity, String name) {
        return getFields(entity).stream()
                                .filter(fieldFieldName(name).or(fieldFieldWithJasonerProperty(name)))
                                .filter(ignoreTransientField())
                                .findFirst();
    }

    private static Predicate<Field> fieldFieldName(String name) {
        return f -> f.getName().equals(name)
                || f.getName().equals(NamingFactory.get(NamingStrategy.CAMEL_CASE).apply(name))
                || f.getName().equals(NamingFactory.get(NamingStrategy.SNAKE_CASE).apply(name));
    }

    private static Predicate<Field> fieldFieldWithJasonerProperty(String name) {
        return f -> {
            var annotation = f.getDeclaredAnnotation(JasonerProperty.class);
            var fieldName = annotation != null ? annotation.value() : f.getName();

            return fieldName.equals(name)
                    || fieldName.equals(NamingFactory.get(NamingStrategy.CAMEL_CASE).apply(name))
                    || fieldName.equals(NamingFactory.get(NamingStrategy.SNAKE_CASE).apply(name));
        };
    }

    private static Predicate<Field> ignoreTransientField() {
        return f -> f.getDeclaredAnnotation(JasonerTransient.class) == null;
    }


    /**
     * Gets the parameter class of a field.
     *
     * @param entity the entity
     * @param name   the name of the field
     * @return an optional containing the parameter class, or empty if not found
     */
    static Optional<Class<?>> getFieldParameterClass(Object entity, String name) {
        return getField(entity, name).map(Field::getType);
    }

    /**
     * Sets the value of a field.
     *
     * @param field  the field
     * @param entity the entity
     * @param value  the value to set
     */
    static void setFieldValue(Field field, Object entity, Object value) {
        if (field.trySetAccessible()) {
            try {
                field.set(entity, value);
            } catch (IllegalAccessException e) {
                throw new JasonerException(e);
            }
        }
    }

    /**
     * Creates an instance of a class.
     *
     * @param <T>   the type of the class
     * @param clazz the class
     * @return an optional containing the instance, or empty if not created
     */
    static <T> Optional<T> createJsonObject(Class<T> clazz) {
        return Optional.ofNullable(clazz.cast(createObjectInt(clazz)));
    }

    /**
     * Creates an instance of a class with the specified arguments.
     *
     * @param clazz the class
     * @return an optional containing the instance, or empty if not created
     */
    static Optional<JsonObject> createJsonObject(Class<?> clazz, Object... args) {
        if (!JsonObject.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(String.format("The class: '%s' is not a JsonObject", clazz.getName()));
        }
        for (Constructor<?> c : clazz.getDeclaredConstructors()) {
            if (c.getParameterCount() == args.length && c.trySetAccessible()) {
                try {
                    return Optional.of((JsonObject) (c.newInstance(args)));
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new JasonerException(e);
                }
            }
        }
        return createJsonObject(JsonObjectImpl.class, args);

    }

    private static Object createObjectInt(Class<?> clazz) {
        for (Constructor<?> c : clazz.getDeclaredConstructors()) {
            if (c.getParameterCount() == 0 && c.trySetAccessible()) {
                try {
                    return clazz.cast(c.newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new JasonerException(e);
                }
            }
        }
        return null;
    }

    /**
     * Invokes a method.
     *
     * @param method the method
     * @param obj    the object
     * @param args   the arguments
     * @return the result of the method invocation
     */
    static Object invokeMethod(Method method, Object obj, Object[] args) {
        try {
            if (method.trySetAccessible()) {
                return method.invoke(obj, args);
            } else {
                throw new JasonerException(String.format("The method '%s' is not accessible", method.getName()));
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new JasonerException(e);
        }
    }

    /**
     * Invokes a method without arguments.
     *
     * @param method the method
     * @param obj    the object
     * @return the result of the method invocation
     */
    static Object invokeMethod(Method method, Object obj) {
        return invokeMethod(method, obj, null);
    }

    private static final BiPredicate<Class<?>, Class<?>> hasInstanceMethodWithoutParameterReturnInstanceOfClass =
            (Class<?> c, Class<?> clazz) -> Stream.of(c.getDeclaredMethods())
                                                  .anyMatch(instanceMethod.and(publicMethod)
                                                                          .and(m -> m.getReturnType() == clazz && m.getParameterCount() == 0));

    /**
     * Creates a builder instance for a class.
     *
     * @param clazz the class
     * @return an optional containing the builder instance, or empty if not created
     */
    static Optional<Object> createBuilder(Class<?> clazz) {
        //Step 1 - Look for a Builder class
        var builderClass = Stream.of(clazz.getDeclaredClasses())
                                 .filter((Class<?> c) -> hasInstanceMethodWithoutParameterReturnInstanceOfClass.test(c, clazz))
                                 .findFirst();
        if (builderClass.isEmpty()) {
            return Optional.empty();
        }
        //Step 2 - Try to create an instance of the builder class
        var instance = builderClass.map(Reflects::createObjectInt);
        if (instance.isPresent()) {
            return instance;
        }
        //Step 3 - Look for a static method that returns an instance of the builder class
        var method = Stream.of(clazz.getDeclaredMethods())
                           .filter(staticMethod
                                           .and(publicMethod)
                                           .and(m -> m.getReturnType() == builderClass.get() && m.getParameterCount() == 0))
                           .findFirst();
        return method.filter(Method::trySetAccessible).map((Method m) -> invokeMethod(m, null, null));
    }

    /**
     * Creates an instance of a class from a builder instance.
     *
     * @param <T>     the type of the class
     * @param builder the builder instance
     * @param clazz   the class
     * @return an optional containing the instance, or empty if not created
     */
    static <T> Optional<T> createObjectFromBuilderInstance(Object builder, Class<T> clazz) {
        return getMethods(builder, m -> m.getReturnType() == clazz && m.getParameterCount() == 0)
                .stream()
                .findFirst().map(m -> invokeMethod(m, builder, null)).map(clazz::cast);
    }

    /**
     * Gets the class of a parameter.
     *
     * @param parameter the parameter
     * @return the class
     */
    private static Class<?> getClass(Parameter parameter) {
        Class<?> clazz = parameter.getType();
        if (clazz == List.class) {
            clazz = getClass(parameter.toString());
        }
        return clazz;
    }

    static Class<?> getClass(String str) {
        int p0 = str.indexOf("<");
        int p1 = str.lastIndexOf(">");
        if (p0 != -1 && p1 != -1) {
            try {
                return Class.forName(str.substring(p0 + 1, p1));
            } catch (ClassNotFoundException e) {
                throw new JasonerException(e);
            }
        }
        return null;
    }

    /**
     * Gets the parameter class of a record.
     *
     * @param clazz the class
     * @param name  the name of the parameter
     * @return an optional containing the parameter class, or empty if not found
     */
    static Optional<Class<?>> getRecordParameterClass(Class<?> clazz, String name) {
        if (!clazz.isRecord()) {
            throw new IllegalArgumentException(String.format("The class: '%s' is not a record", clazz.getName()));
        }
        var constructor = clazz.getDeclaredConstructors()[0];

        for (var parameter : constructor.getParameters()) {
            if (hasParameterName.test(parameter, name)) {
                return Optional.of(getClass(parameter));
            }
        }
        return Optional.empty();
    }

    static Optional<Class<?>> getSingleRecordParameterClass(Class<?> clazz) {
        if (!clazz.isRecord()) {
            throw new IllegalArgumentException(String.format("The class: '%s' is not a record", clazz.getName()));
        }
        var constructor = clazz.getDeclaredConstructors()[0];
        var parameters = constructor.getParameters();
        if (parameters.length == 1) {
            return Optional.of(getClass(parameters[0]));
        }
        return Optional.empty();
    }

    /**
     * Creates an instance of a record.
     *
     * @param <T>   the type of the record
     * @param clazz the class of the record
     * @param map   the map of parameter values
     * @return an optional containing the record instance, or empty if not created
     */
    static <T> Optional<T> createRecord(Class<T> clazz, Map<String, Object> map) {
        if (!clazz.isRecord()) {
            throw new IllegalArgumentException(String.format("The class: '%s' is not a record", clazz.getName()));
        }
        var constructor = clazz.getDeclaredConstructors()[0];
        if (constructor.trySetAccessible()) {
            List<Object> values = getConstructorArgs(map, constructor);
            try {
                return Optional.of(clazz.cast(constructor.newInstance(values.toArray())));
            } catch (Exception e) {
                throw new JasonerException(e);
            }
        }
        throw new IllegalArgumentException(String.format("The constructor of the record class: '%s' is not accessible",
                                                         clazz.getName()));
    }

    static <T> Optional<T> createSingleRecord(Class<T> clazz, Object value) {
        if (!clazz.isRecord()) {
            throw new IllegalArgumentException(String.format("The class: '%s' is not a record", clazz.getName()));
        }
        var constructor = clazz.getDeclaredConstructors()[0];
        if (constructor.trySetAccessible()) {
            try {
                return Optional.of(clazz.cast(constructor.newInstance(value)));
            } catch (Exception e) {
                var msg = String.format("Error to create new instance of class: %s with value: %s, cause: %s",
                                        clazz.getName(), value, e.getMessage());
                throw new JasonerException(msg);
            }
        }
        throw new IllegalArgumentException(String.format("The constructor of the record class: '%s' is not accessible",
                                                         clazz.getName()));
    }

    private static List<Object> getConstructorArgs(Map<String, Object> map, Constructor<?> constructor) {
        List<String> nameParameter = new ArrayList<>();
        for (var parameter : constructor.getParameters()) {
            nameParameter.add(parameter.getName());
        }
        List<Object> values = new ArrayList<>();
        for (var name : nameParameter) {
            values.add(getParameter(map, name));
        }
        return values;
    }

    private static Object getParameter(Map<String, Object> map, String name) {
        Object value = map.get(name);
        if (value == null) {
            value = map.get(NamingFactory.get(NamingStrategy.CAMEL_CASE).apply(name));
        }
        if (value == null) {
            value = map.get(NamingFactory.get(NamingStrategy.SNAKE_CASE).apply(name));
        }
        return value;
    }

    /**
     * Gets the value of a field.
     *
     * @param field  the field
     * @param source the source object
     * @return the value of the field
     */
    public static Object getFieldValue(Field field, Object source) {
        if (field.trySetAccessible()) {
            try {
                return field.get(source);
            } catch (IllegalAccessException e) {
                throw new JasonerException(e);
            }
        }
        return null;
    }

    static Optional<JasonerProperty> getJasonerProperty(Class<?> ctype, Enum<?> e) {
        try {
            var field = ctype.getField(e.name());
            return Optional.ofNullable(field.getAnnotation(JasonerProperty.class));
        } catch (NoSuchFieldException ex) {
            return Optional.empty();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] createArray(Class<?> clazz, List<?> list) {
        if (clazz.isArray()) {
            T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz.getComponentType(), list.size());
            for (int i = 0; i < list.size(); i++) {
                array[i] = (T) list.get(i);
            }
            return array;
        }
        throw new JasonerException("The class is not an array: " + clazz.getName());
    }
}
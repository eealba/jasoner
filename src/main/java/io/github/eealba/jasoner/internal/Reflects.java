package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.JsonException;
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
 * The type Reflects.
 */
class Reflects {
    private static final Predicate<Method> staticMethod = (Method method) -> Modifier.isStatic(method.getModifiers());
    private static final Predicate<Method> instanceMethod = (Method method) -> !Modifier.isStatic(method.getModifiers());

    private static final Predicate<Method> publicMethod = (Method method) -> Modifier.isPublic(method.getModifiers());
    private static final Predicate<Method> protectedMethod = (Method method) -> Modifier.isProtected(method.getModifiers());
    private static final Predicate<Method> privateMethod = (Method method) -> Modifier.isPrivate(method.getModifiers());
    private static final Predicate<Method> packageMethod = publicMethod.and(protectedMethod).and(privateMethod).negate();

    private static final Predicate<Method> onceParameterMethod = (Method method) -> method.getParameterCount() == 1;
    private static final Predicate<Method> noParameterMethod = (Method method) -> method.getParameterCount() == 0;
    private static final Predicate<Method> returnValueMethod = (Method method) -> method.getReturnType() != Void.TYPE;

    private static final Predicate<Method> setterMethod = instanceMethod.and(onceParameterMethod);
    private static final Predicate<Method> getterMethod = instanceMethod.and(noParameterMethod).and(returnValueMethod);

    private static final BiPredicate<Parameter, String > hasParameterName = (Parameter p, String name) ->
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
    static List<Method> getGetterMethods(Object entity, ModifierStrategy modifierStrategy) {
        var predicate = getterMethod;
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
    static Optional<Class<?>> getSetterMethodParameterClass(Object entity, String name) {
        return getSetterMethod(entity, name, null).map(m -> m.getParameters()[0]).map(Reflects::getClass);
    }

    static Optional<Method> getSetterMethod(Object entity, String name, Object value) {
        var methods = getMethods(entity, setterMethod).stream()
                .filter(m -> value == null || m.getParameterTypes()[0].isInstance(value))
                .filter(m -> m.getName().equals(name)
                        || m.getName().equals(NamingFactory.get(NamingStrategy.CAMEL_CASE).apply(name))
                        || m.getName().equals(NamingFactory.get(NamingStrategy.SNAKE_CASE).apply(name))
                        || m.getName().equals("set" + capitalizeFirstLetter(
                                NamingFactory.get(NamingStrategy.CAMEL_CASE).apply(name)))
                        || m.getName().equals("set" + capitalizeFirstLetter(
                        NamingFactory.get(NamingStrategy.SNAKE_CASE).apply(name)))
                )
                .toList();
        return methods.stream().findFirst();
    }
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
        Field[] methods = entity.getClass().getDeclaredFields();
        return Arrays.asList(methods);
    }
    static Optional<Field> getField(Object entity, String name) {

        return getFields(entity).stream()
                .filter(f -> f.getName().equals(name)
                        || f.getName().equals(NamingFactory.get(NamingStrategy.CAMEL_CASE).apply(name))
                        || f.getName().equals(NamingFactory.get(NamingStrategy.SNAKE_CASE).apply(name)))
                .findFirst();
    }

    static Optional<Class<?>> getFieldParameterClass(Object entity, String name) {
        return getField(entity, name).map(Field::getType);
    }

    static void setFieldValue(Field field, Object entity, Object value) {
        if (field.trySetAccessible()){
            try {
                field.set(entity, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Create object t.
     *
     * @param <T>   the type parameter
     * @param clazz the clase
     * @return the t
     */
    static <T> Optional<T> createObject(Class<T> clazz) {
        return Optional.ofNullable(clazz.cast(createObjectInt(clazz)));
    }
    private static Object createObjectInt(Class<?> clazz) {
        for (Constructor<?> c : clazz.getDeclaredConstructors()) {
            if (c.getParameterCount() == 0 &&  c.trySetAccessible()){
                try {
                    return clazz.cast(c.newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        return null;
    }
    static Object invokeMethod(Method method, Object obj, Object[] args) {
        try {
            if (method.trySetAccessible()){
                return method.invoke(obj, args);
            }else{
                throw new JsonException(String.format("The method '%s' is not accessible", method.getName()));
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
           return  new JsonException(e);
        }
    }
    static Object invokeMethod(Method method, Object obj) {
        return invokeMethod(method, obj, null);
    }

    private static final BiPredicate<Class<?>, Class<?>> hasInstanceMethodWithoutParameterReturnInstanceOfClass =
            (Class<?> c, Class<?> clazz) -> Stream.of(c.getDeclaredMethods())
            .anyMatch(instanceMethod.and(publicMethod)
                    .and(m -> m.getReturnType() == clazz && m.getParameterCount() == 0));

    static Optional<Object> createBuilder(Class<?> clazz) {
        //Step 1 - Look for a Builder class
        var builderClass = Stream.of(clazz.getDeclaredClasses())
                .filter((Class<?> c) -> hasInstanceMethodWithoutParameterReturnInstanceOfClass.test(c, clazz))
                .findFirst();
        if (builderClass.isEmpty()){
            return Optional.empty();
        }
        //Step 2 - Try to create an instance of the builder class
        var instance = builderClass.map(Reflects::createObjectInt);
        if (instance.isPresent()){
            return instance;
        }
        //Step 3 - Look for a static method that returns an instance of the builder class
        var method = Stream.of(clazz.getDeclaredMethods())
                .filter(staticMethod
                        .and(publicMethod)
                        .and(m -> m.getReturnType() == builderClass.get() && m.getParameterCount() == 0))
                .findFirst();
        return method.filter(Method::trySetAccessible).map((Method m) -> invokeMethod(m, null,null));
    }

    static <T> Optional<T> createObjectFromBuilderInstance(Object builder, Class<T> clazz) {
        return  getMethods(builder, m -> m.getReturnType() == clazz && m.getParameterCount() == 0)
                .stream()
                .findFirst().map(m -> invokeMethod(m, builder, null)).map(clazz::cast);
    }
    /**
     * Gets class.
     *
     * @param parameter the parameter
     * @return the class
     */
    private static Class<?> getClass(Parameter parameter) {
        Class<?> clazz = parameter.getType();
        if (clazz == List.class){
            String str = parameter.toString();
            int p0 = str.indexOf("<");
            int p1 = str.lastIndexOf(">");
            if (p0 != -1 && p1 != -1){
                try {
                    clazz = Class.forName(str.substring(p0 + 1, p1));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

        }

        return clazz;
    }
    static Optional<Class<?>> getRecordParameterClass(Class<?> clazz, String name){
        if (!clazz.isRecord()) {
            throw new IllegalArgumentException(String.format("The class: '%s' is not a record", clazz.getName()));
        }
        var constructor = clazz.getDeclaredConstructors()[0];

            for (var parameter : constructor.getParameters()) {
                if (hasParameterName.test(parameter, name)){
                    return Optional.of(getClass(parameter));
                }
            }

        return Optional.empty();
    }


        static <T> Optional<T> createRecord(Class<T> clazz, Map<String, Object> map){
        if (!clazz.isRecord()) {
            throw new IllegalArgumentException(String.format("The class: '%s' is not a record", clazz.getName()));
        }
        var constructor = clazz.getDeclaredConstructors()[0];
        if (constructor.trySetAccessible()){
            List<Object> values = getConstructorArgs(map, constructor);
            try {
                return Optional.of(clazz.cast(constructor.newInstance(values.toArray())));
            } catch (Exception e) {
                throw new RuntimeException(e);
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
        if (value == null){
            value = map.get(NamingFactory.get(NamingStrategy.CAMEL_CASE).apply(name));
        }
        if (value == null){
            value = map.get(NamingFactory.get(NamingStrategy.SNAKE_CASE).apply(name));
        }
        return value;
    }


}

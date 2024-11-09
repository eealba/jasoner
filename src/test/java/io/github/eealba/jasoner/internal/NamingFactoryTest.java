package io.github.eealba.jasoner.internal;

import io.github.eealba.jasoner.NamingStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NamingFactoryTest {

    @Test
    void kebakCase() {
        Naming naming = NamingFactory.get(NamingStrategy.KEBAB_CASE);
        
        Assertions.assertEquals("hola-mundo", naming.apply("holaMundo"));
        Assertions.assertEquals("hola-mundo", naming.apply("HolaMundo"));
        Assertions.assertEquals("hola-mundo", naming.apply("Hola_mundo"));
        Assertions.assertEquals("hola-mundo", naming.apply("Hola_MUNDO"));
    }
    @Test
    void upperKebakCase() {
        Naming naming = NamingFactory.get(NamingStrategy.UPPER_KEBAB_CASE);
        
        Assertions.assertEquals("HOLA-MUNDO-GRANDE", naming.apply("holaMundoGrande"));
        Assertions.assertEquals("HOLA-MUNDO", naming.apply("HolaMundo"));
        Assertions.assertEquals("HOLA-MUNDO", naming.apply("Hola_mundo"));
        Assertions.assertEquals("HOLA-MUNDO", naming.apply("Hola_MUNDO"));
    }
    @Test
    void snakeCase() {
        Naming naming = NamingFactory.get(NamingStrategy.SNAKE_CASE);

        Assertions.assertEquals("hola_mundo", naming.apply("holaMundo"));
        Assertions.assertEquals("hola_mundo", naming.apply("HolaMundo"));
        Assertions.assertEquals("hola_mundo", naming.apply("Hola-Mundo"));
    }
    @Test
    void upperSnakeCase() {
        Naming naming = NamingFactory.get(NamingStrategy.UPPER_SNAKE_CASE);
        
        Assertions.assertEquals("HOLA_MUNDO", naming.apply("holaMundo"));
        Assertions.assertEquals("HOLA_MUNDO", naming.apply("HolaMundo"));
        Assertions.assertEquals("HOLA_MUNDO", naming.apply("Hola-Mundo"));
    }

    @Test
    void none() {
        Naming naming = NamingFactory.get(NamingStrategy.NONE);
        
        Assertions.assertEquals("holaMundo", naming.apply("holaMundo"));
        Assertions.assertEquals("HolaMundo", naming.apply("HolaMundo"));
        Assertions.assertEquals("Hola Mundo", naming.apply("Hola Mundo"));
        Assertions.assertEquals("Hola-Mundo", naming.apply("Hola-Mundo"));
        Assertions.assertEquals("Hola_Mundo", naming.apply("Hola_Mundo"));
    }
    @Test
    void lowerCase() {
        Naming naming = NamingFactory.get(NamingStrategy.LOWER_CASE);
        
        Assertions.assertEquals("holamundo", naming.apply("holaMundo"));
        Assertions.assertEquals("holamundo", naming.apply("HolaMundo"));
        Assertions.assertEquals("hola mundo", naming.apply("Hola Mundo"));
        Assertions.assertEquals("hola-mundo", naming.apply("Hola-Mundo"));
        Assertions.assertEquals("hola_mundo", naming.apply("Hola_Mundo"));
    }

    @Test
    void upperCase() {
        Naming naming = NamingFactory.get(NamingStrategy.UPPER_CASE);
        
        Assertions.assertEquals("HOLAMUNDO", naming.apply("holaMundo"));
        Assertions.assertEquals("HOLAMUNDO", naming.apply("HolaMundo"));
        Assertions.assertEquals("HOLA MUNDO", naming.apply("Hola Mundo"));
        Assertions.assertEquals("HOLA-MUNDO", naming.apply("Hola-Mundo"));
        Assertions.assertEquals("HOLA_MUNDO", naming.apply("Hola_Mundo"));
    }

    @Test
    void camelCase() {
        Naming naming = NamingFactory.get(NamingStrategy.CAMEL_CASE);

        Assertions.assertEquals("addressLine1", naming.apply( "address_line_1"));
        Assertions.assertEquals("addressLine1", naming.apply( "address-line-1"));
        Assertions.assertEquals("addressLine1", naming.apply( "addressLine1"));
        Assertions.assertEquals("addressLine1", naming.apply( "AddressLine1"));
        Assertions.assertEquals("addressLine1", naming.apply( "ADDRESS_LINE_1"));

        Assertions.assertEquals("holaMundo", naming.apply("holaMundo"));
        Assertions.assertEquals("holaMundo", naming.apply("HolaMundo"));
        Assertions.assertEquals("holaMundo", naming.apply("Hola-Mundo"));
        Assertions.assertEquals("holaMundo", naming.apply("Hola_Mundo"));
        Assertions.assertEquals("holaMundo", naming.apply("Hola_mundo"));
        Assertions.assertEquals("holaMundo", naming.apply("Hola-mundo"));
        Assertions.assertEquals("holaMundo", naming.apply("HOLA_MUNDO"));

        Assertions.assertEquals("productId", naming.apply( "productId"));
        Assertions.assertEquals("productId", naming.apply( "product_Id"));
        Assertions.assertEquals("productId", naming.apply( "product_id"));
        Assertions.assertEquals("productId", naming.apply( "ProductId"));
        Assertions.assertEquals("productId", naming.apply( "PRODUCT_ID"));


    }

}
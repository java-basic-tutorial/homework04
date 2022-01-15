package com.softserveinc.task01;

import com.softserveinc.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.softserveinc.TestUtil.*;

public class CoffeeMachineV1Test {

    public static final String CLASS_NAME = "com.softserveinc.task01.CoffeeMachineV1";

    public static final Class<?> CLAZZ;

    static {
        Class<?> cls;
        try {
            cls = Class.forName(CLASS_NAME);
        } catch (ClassNotFoundException ex) {
            cls = null;
        }
        CLAZZ = cls;
    }

    @Test
    @Order(1)
    @DisplayName("Check existing class CoffeeMachineV1")
    void classExists() {
        isPublicNoStaticNoFinalNoAbstractClass(CLAZZ, CLASS_NAME);
    }

    @Order(2)
    @ParameterizedTest(name = "{0}")
    @DisplayName("Check private fields")
    @ValueSource(strings = {"coffee", "water", "waste"})
    void fieldExists(String fieldName) throws NoSuchFieldException {
        hasPrivateNoStaticField(CLAZZ.getSuperclass(), fieldName);
    }

    @Order(2)
    @ParameterizedTest(name = "{0}")
    @DisplayName("Check existing getters")
    @ValueSource(strings = {"getCoffee", "getWater", "getWaste"})
    void getterExists(String getterName) throws NoSuchMethodException {
        hasPublicNoStaticMethod(CLAZZ.getSuperclass(), getterName);
    }

    @Order(3)
    @ParameterizedTest(name = "{0}")
    @DisplayName("Check existing methods makeEspresso and makeAmericano")
    @ValueSource(strings = {"makeEspresso", "makeAmericano"})
    void methodsExists(String getterName) throws NoSuchMethodException {
        hasPublicNoStaticMethod(CLAZZ, getterName);
    }

    @Order(5)
    @Test
    @DisplayName("Check existing constructors")
    void constructors() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cls = Class.forName(CLASS_NAME);
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        Assertions.assertEquals(1, declaredConstructors.length, "Should be only one constructor");

        Constructor<?> ctor = cls.getDeclaredConstructor(int.class, int.class, int.class);

        Object human = ctor.newInstance(200, 200, 200);
    }
}

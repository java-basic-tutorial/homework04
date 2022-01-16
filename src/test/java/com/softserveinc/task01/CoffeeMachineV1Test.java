package com.softserveinc.task01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    @DisplayName("Check existing class CoffeeMachineV1")
    void classExists() {
        isPublicNoStaticNoFinalNoAbstractClass(CLAZZ, CLASS_NAME);
    }


    @ParameterizedTest(name = "{0}")
    @DisplayName("Check private fields")
    @ValueSource(strings = {"coffee", "water", "waste"})
    void fieldExists(String fieldName) throws NoSuchFieldException {
        hasPrivateNoStaticField(CLAZZ.getSuperclass(), fieldName);
    }


    @ParameterizedTest(name = "{0}")
    @DisplayName("Check existing getters")
    @ValueSource(strings = {"getCoffee", "getWater", "getWaste"})
    void getterExists(String getterName) throws NoSuchMethodException {
        hasPublicNoStaticMethod(CLAZZ.getSuperclass(), getterName);
    }


    @ParameterizedTest(name = "{0}")
    @DisplayName("Check existing methods makeEspresso and makeAmericano")
    @ValueSource(strings = {"makeEspresso", "makeAmericano"})
    void methodsExists(String getterName) throws NoSuchMethodException {
        hasPublicNoStaticMethod(CLAZZ, getterName);
    }

    @Test
    @DisplayName("Should make Espresso")
    void shouldMakeEspresso() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cls = Class.forName(CLASS_NAME);
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        Assertions.assertEquals(1, declaredConstructors.length, "Should be only one constructor");

        Constructor<?> ctor = cls.getDeclaredConstructor(int.class, int.class, int.class);

        Object coffeeMachine = ctor.newInstance(200, 200, 200);
        Method on = CLAZZ.getSuperclass().getDeclaredMethod("on");
        Method addWater = CLAZZ.getSuperclass().getDeclaredMethod("addWater", int.class);
        Method addCoffeeBeans = CLAZZ.getSuperclass().getDeclaredMethod("addCoffeeBeans", int.class);
        Method makeEspresso = CLAZZ.getDeclaredMethod("makeEspresso");

        on.invoke(coffeeMachine);
        addWater.invoke(coffeeMachine, 30);
        addCoffeeBeans.invoke(coffeeMachine, 22);
        boolean made = (boolean) makeEspresso.invoke(coffeeMachine);
        Assertions.assertTrue(made);
    }

    @Test
    @DisplayName("Should make Americano")
    void shouldMakeAmericano() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cls = Class.forName(CLASS_NAME);
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        Assertions.assertEquals(1, declaredConstructors.length, "Should be only one constructor");

        Constructor<?> ctor = cls.getDeclaredConstructor(int.class, int.class, int.class);

        Object coffeeMachine = ctor.newInstance(200, 200, 200);
        Method on = CLAZZ.getSuperclass().getDeclaredMethod("on");
        Method addWater = CLAZZ.getSuperclass().getDeclaredMethod("addWater", int.class);
        Method addCoffeeBeans = CLAZZ.getSuperclass().getDeclaredMethod("addCoffeeBeans", int.class);
        Method makeAmericano = CLAZZ.getDeclaredMethod("makeAmericano");

        on.invoke(coffeeMachine);
        addWater.invoke(coffeeMachine, 100);
        addCoffeeBeans.invoke(coffeeMachine, 22);
        boolean made = (boolean) makeAmericano.invoke(coffeeMachine);
        Assertions.assertTrue(made);
    }

    @Test
    @DisplayName("Should not make Espresso")
    void shouldNotMakeEspresso() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cls = Class.forName(CLASS_NAME);
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        Assertions.assertEquals(1, declaredConstructors.length, "Should be only one constructor");

        Constructor<?> ctor = cls.getDeclaredConstructor(int.class, int.class, int.class);

        Object coffeeMachine = ctor.newInstance(200, 200, 200);
        Method on = CLAZZ.getSuperclass().getDeclaredMethod("on");
        Method addWater = CLAZZ.getSuperclass().getDeclaredMethod("addWater", int.class);
        Method addCoffeeBeans = CLAZZ.getSuperclass().getDeclaredMethod("addCoffeeBeans", int.class);
        Method makeEspresso = CLAZZ.getDeclaredMethod("makeEspresso");

        addWater.invoke(coffeeMachine, 29);
        addCoffeeBeans.invoke(coffeeMachine, 21);

        PrintStream systemError = System.err;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));

        boolean made = (boolean) makeEspresso.invoke(coffeeMachine);
        Assertions.assertFalse(made, "Should not make Espresso because machine is off");
        String errorMessage = outputStream.toString();
        Assertions.assertFalse(errorMessage.isEmpty());
        Assertions.assertEquals("Coffee machine is off", errorMessage.trim());

        outputStream.reset();

        on.invoke(coffeeMachine);

        made = (boolean) makeEspresso.invoke(coffeeMachine);
        Assertions.assertFalse(made, "Should not make Espresso because coffee is not enough");
        errorMessage = outputStream.toString();
        Assertions.assertFalse(errorMessage.isEmpty());
        Assertions.assertEquals("Not enough coffee", errorMessage.trim());

        outputStream.reset();

        addCoffeeBeans.invoke(coffeeMachine, 1);
        made = (boolean) makeEspresso.invoke(coffeeMachine);
        Assertions.assertFalse(made, "Should not make Espresso because water is not enough");
        errorMessage = outputStream.toString();
        Assertions.assertFalse(errorMessage.isEmpty());
        Assertions.assertEquals("Not enough water", errorMessage.trim());

        System.setErr(systemError);
    }

    @Test
    @DisplayName("Should not make Americano")
    void shouldNotMakeAmericano() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cls = Class.forName(CLASS_NAME);
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        Assertions.assertEquals(1, declaredConstructors.length, "Should be only one constructor");

        Constructor<?> ctor = cls.getDeclaredConstructor(int.class, int.class, int.class);

        Object coffeeMachine = ctor.newInstance(200, 200, 200);
        Method on = CLAZZ.getSuperclass().getDeclaredMethod("on");
        Method addWater = CLAZZ.getSuperclass().getDeclaredMethod("addWater", int.class);
        Method addCoffeeBeans = CLAZZ.getSuperclass().getDeclaredMethod("addCoffeeBeans", int.class);
        Method makeAmericano = CLAZZ.getDeclaredMethod("makeAmericano");

        addWater.invoke(coffeeMachine, 99);
        addCoffeeBeans.invoke(coffeeMachine, 21);

        PrintStream systemError = System.err;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));

        boolean made = (boolean) makeAmericano.invoke(coffeeMachine);
        Assertions.assertFalse(made, "Should not make Americano because machine is off");
        String errorMessage = outputStream.toString();
        Assertions.assertFalse(errorMessage.isEmpty());
        Assertions.assertEquals("Coffee machine is off", errorMessage.trim());

        outputStream.reset();

        on.invoke(coffeeMachine);

        made = (boolean) makeAmericano.invoke(coffeeMachine);
        Assertions.assertFalse(made, "Should not make Americano because coffee is not enough");
        errorMessage = outputStream.toString();
        Assertions.assertFalse(errorMessage.isEmpty());
        Assertions.assertEquals("Not enough coffee", errorMessage.trim());

        outputStream.reset();

        addCoffeeBeans.invoke(coffeeMachine, 1);
        made = (boolean) makeAmericano.invoke(coffeeMachine);
        Assertions.assertFalse(made, "Should not make Americano because water is not enough");
        errorMessage = outputStream.toString();
        Assertions.assertFalse(errorMessage.isEmpty());
        Assertions.assertEquals("Not enough water", errorMessage.trim());

        System.setErr(systemError);
    }


    @Test
    @DisplayName("Check existing constructors")
    void constructors() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cls = Class.forName(CLASS_NAME);
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        Assertions.assertEquals(1, declaredConstructors.length, "Should be only one constructor");
    }
}

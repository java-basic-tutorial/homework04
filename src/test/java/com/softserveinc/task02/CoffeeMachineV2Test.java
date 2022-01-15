package com.softserveinc.task02;

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

public class CoffeeMachineV2Test {

    public static final String CLASS_NAME = "com.softserveinc.task02.CoffeeMachineV2";

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


    @Test
    void milkFieldExists() throws NoSuchFieldException {
        hasPrivateNoStaticField(CLAZZ, "milk", int.class);
    }

    @Test
    void milkReservoirCapacityFieldExists() throws NoSuchFieldException {
        hasFinalProtectedNoStaticField(CLAZZ, "milkReservoirCapacity", int.class);
    }

    @Test
    void getterExists() throws NoSuchMethodException {
        hasPublicNoStaticMethod(CLAZZ, "getMilk");
    }


    @ParameterizedTest(name = "{0}")
    @DisplayName("Check existing methods makeEspresso and makeAmericano")
    @ValueSource(strings = {"makeLatte", "makeCappuccino"})
    void methodsExists(String getterName) throws NoSuchMethodException {
        hasPublicNoStaticMethod(CLAZZ, getterName);
    }

    @Test
    @DisplayName("Should make Cappuccino")
    void shouldMakeCappuccino() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cls = Class.forName(CLASS_NAME);
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        Assertions.assertEquals(1, declaredConstructors.length, "Should be only one constructor");

        Constructor<?> ctor = cls.getDeclaredConstructor(int.class, int.class, int.class, int.class);

        Object coffeeMachine = ctor.newInstance(200, 200, 200, 200);
        Method on = CLAZZ.getSuperclass().getSuperclass().getDeclaredMethod("on");
        Method addWater = CLAZZ.getSuperclass().getSuperclass().getDeclaredMethod("addWater", int.class);
        Method addCoffeeBeans = CLAZZ.getSuperclass().getSuperclass().getDeclaredMethod("addCoffeeBeans", int.class);
        Method addMilk = CLAZZ.getDeclaredMethod("addMilk", int.class);
        Method makeEspresso = CLAZZ.getDeclaredMethod("makeCappuccino");

        on.invoke(coffeeMachine);
        addWater.invoke(coffeeMachine, 30);
        addCoffeeBeans.invoke(coffeeMachine, 22);
        addMilk.invoke(coffeeMachine, 85);

        boolean made = (boolean) makeEspresso.invoke(coffeeMachine);
        Assertions.assertTrue(made);
    }

    @Test
    @DisplayName("Should make Latte")
    void shouldMakeLatte() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cls = Class.forName(CLASS_NAME);
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        Assertions.assertEquals(1, declaredConstructors.length, "Should be only one constructor");

        Constructor<?> ctor = cls.getDeclaredConstructor(int.class, int.class, int.class, int.class);

        Object coffeeMachine = ctor.newInstance(200, 200, 200, 200);
        Method on = CLAZZ.getSuperclass().getSuperclass().getDeclaredMethod("on");
        Method addWater = CLAZZ.getSuperclass().getSuperclass().getDeclaredMethod("addWater", int.class);
        Method addCoffeeBeans = CLAZZ.getSuperclass().getSuperclass().getDeclaredMethod("addCoffeeBeans", int.class);
        Method addMilk = CLAZZ.getDeclaredMethod("addMilk", int.class);
        Method makeLatte = CLAZZ.getDeclaredMethod("makeLatte");

        on.invoke(coffeeMachine);
        addWater.invoke(coffeeMachine, 100);
        addCoffeeBeans.invoke(coffeeMachine, 22);
        addMilk.invoke(coffeeMachine, 150);
        boolean made = (boolean) makeLatte.invoke(coffeeMachine);
        Assertions.assertTrue(made);
    }

    @Test
    @DisplayName("Should not make Cappuccino")
    void shouldNotMakeCappuccino() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cls = Class.forName(CLASS_NAME);
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        Assertions.assertEquals(1, declaredConstructors.length, "Should be only one constructor");

        Constructor<?> ctor = cls.getDeclaredConstructor(int.class, int.class, int.class, int.class);

        Object coffeeMachine = ctor.newInstance(200, 200, 200, 200);
        Method on = CLAZZ.getSuperclass().getSuperclass().getDeclaredMethod("on");
        Method addWater = CLAZZ.getSuperclass().getSuperclass().getDeclaredMethod("addWater", int.class);
        Method addCoffeeBeans = CLAZZ.getSuperclass().getSuperclass().getDeclaredMethod("addCoffeeBeans", int.class);
        Method addMilk = CLAZZ.getDeclaredMethod("addMilk", int.class);
        Method makeCappuccino = CLAZZ.getDeclaredMethod("makeCappuccino");

        addWater.invoke(coffeeMachine, 29);
        addCoffeeBeans.invoke(coffeeMachine, 21);
        addMilk.invoke(coffeeMachine, 84);

        PrintStream systemError = System.err;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));

        boolean made = (boolean) makeCappuccino.invoke(coffeeMachine);
        Assertions.assertFalse(made, "Should not make Cappuccino because machine is off");
        String errorMessage = outputStream.toString();
        Assertions.assertFalse(errorMessage.isEmpty());
        Assertions.assertEquals("Coffee machine is off", errorMessage.trim());

        outputStream.reset();

        on.invoke(coffeeMachine);

        made = (boolean) makeCappuccino.invoke(coffeeMachine);
        Assertions.assertFalse(made, "Should not make Cappuccino because coffee is not enough");
        errorMessage = outputStream.toString();
        Assertions.assertFalse(errorMessage.isEmpty());
        Assertions.assertEquals("Not enough coffee", errorMessage.trim());

        outputStream.reset();

        addCoffeeBeans.invoke(coffeeMachine, 1);
        made = (boolean) makeCappuccino.invoke(coffeeMachine);
        Assertions.assertFalse(made, "Should not make Cappuccino because water is not enough");
        errorMessage = outputStream.toString();
        Assertions.assertFalse(errorMessage.isEmpty());
        Assertions.assertEquals("Not enough water", errorMessage.trim());

        outputStream.reset();

        addWater.invoke(coffeeMachine, 1);
        made = (boolean) makeCappuccino.invoke(coffeeMachine);
        Assertions.assertFalse(made, "Should not make Cappuccino because milk is not enough");
        errorMessage = outputStream.toString();
        Assertions.assertFalse(errorMessage.isEmpty());
        Assertions.assertEquals("Not enough milk", errorMessage.trim());

        System.setErr(systemError);
    }

    @Test
    @DisplayName("Should not make Latte")
    void shouldNotMakeLatte() throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> cls = Class.forName(CLASS_NAME);
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        Assertions.assertEquals(1, declaredConstructors.length, "Should be only one constructor");

        Constructor<?> ctor = cls.getDeclaredConstructor(int.class, int.class, int.class, int.class);

        Object coffeeMachine = ctor.newInstance(200, 200, 200, 200);
        Method on = CLAZZ.getSuperclass().getSuperclass().getDeclaredMethod("on");
        Method addWater = CLAZZ.getSuperclass().getSuperclass().getDeclaredMethod("addWater", int.class);
        Method addCoffeeBeans = CLAZZ.getSuperclass().getSuperclass().getDeclaredMethod("addCoffeeBeans", int.class);
        Method addMilk = CLAZZ.getDeclaredMethod("addMilk", int.class);
        Method makeLatte = CLAZZ.getDeclaredMethod("makeLatte");

        addWater.invoke(coffeeMachine, 29);
        addCoffeeBeans.invoke(coffeeMachine, 21);
        addMilk.invoke(coffeeMachine, 149);

        PrintStream systemError = System.err;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));

        boolean made = (boolean) makeLatte.invoke(coffeeMachine);
        Assertions.assertFalse(made, "Should not make Latte because machine is off");
        String errorMessage = outputStream.toString();
        Assertions.assertFalse(errorMessage.isEmpty());
        Assertions.assertEquals("Coffee machine is off", errorMessage.trim());

        outputStream.reset();

        on.invoke(coffeeMachine);

        made = (boolean) makeLatte.invoke(coffeeMachine);
        Assertions.assertFalse(made, "Should not make Latte because coffee is not enough");
        errorMessage = outputStream.toString();
        Assertions.assertFalse(errorMessage.isEmpty());
        Assertions.assertEquals("Not enough coffee", errorMessage.trim());

        outputStream.reset();

        addCoffeeBeans.invoke(coffeeMachine, 1);
        made = (boolean) makeLatte.invoke(coffeeMachine);
        Assertions.assertFalse(made, "Should not make Latte because water is not enough");
        errorMessage = outputStream.toString();
        Assertions.assertFalse(errorMessage.isEmpty());
        Assertions.assertEquals("Not enough water", errorMessage.trim());

        outputStream.reset();

        addWater.invoke(coffeeMachine, 1);
        made = (boolean) makeLatte.invoke(coffeeMachine);
        Assertions.assertFalse(made, "Should not make Latte because water is not enough");
        errorMessage = outputStream.toString();
        Assertions.assertFalse(errorMessage.isEmpty());
        Assertions.assertEquals("Not enough milk", errorMessage.trim());

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

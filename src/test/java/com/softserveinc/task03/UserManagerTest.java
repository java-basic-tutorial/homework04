package com.softserveinc.task03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import static com.softserveinc.TestUtil.*;

public class UserManagerTest {

    public static final String CLASS_NAME = "com.softserveinc.task03.UserManager";

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
    @DisplayName("Check existing class UserManager")
    void classExists() {
        isPublicNoStaticNoFinalNoAbstractClass(CLAZZ, CLASS_NAME);
    }


    @Test
    void usersFieldExists() throws NoSuchFieldException {
        hasFinalPrivateNoStaticField(CLAZZ, "users", Set.class);
    }


    @Test
    void registerMethodExists() throws NoSuchMethodException {
        hasPublicNoStaticMethod(CLAZZ, "register", String.class, String.class);
    }

    @Test
    void registerMethodTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor<?> constructor = CLAZZ.getDeclaredConstructor();
        Object userManager = constructor.newInstance();
        String login = "login";
        String password = "password";
        Method register = CLAZZ.getDeclaredMethod("register", String.class, String.class);
        register.invoke(userManager, login, password);
        Field usersField = CLAZZ.getDeclaredField("users");
        usersField.setAccessible(true);
        Set users = (Set)usersField.get(userManager);
        Assertions.assertEquals(1, users.size());
        Assertions.assertTrue(users.contains(new User(login, password)));
    }

    @Test
    void deleteMethodExists() throws NoSuchMethodException {
        hasPublicNoStaticMethod(CLAZZ, "delete", String.class);
    }

    @Test
    void deleteMethodTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor<?> constructor = CLAZZ.getDeclaredConstructor();
        Object userManager = constructor.newInstance();
        String login = "login";
        String password = "password";
        Field usersField = CLAZZ.getDeclaredField("users");
        usersField.setAccessible(true);
        Set users = (Set)usersField.get(userManager);
        users.add(new User(login, password));
        Assertions.assertEquals(1, users.size());
        Assertions.assertTrue(users.contains(new User(login, password)));
        Method delete = CLAZZ.getDeclaredMethod("delete", String.class);
        delete.invoke(userManager, login);
        Assertions.assertEquals(0, users.size());
    }

    @Test
    void existsMethodExists() throws NoSuchMethodException {
        hasPublicNoStaticMethod(CLAZZ, "exists", String.class, String.class);
    }

    @Test
    void existsMethodTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor<?> constructor = CLAZZ.getDeclaredConstructor();
        Object userManager = constructor.newInstance();
        String login = "login";
        String password = "password";
        Field usersField = CLAZZ.getDeclaredField("users");
        usersField.setAccessible(true);
        Set users = (Set)usersField.get(userManager);
        users.add(new User(login, password));
        Assertions.assertEquals(1, users.size());
        Assertions.assertTrue(users.contains(new User(login, password)));
        Method exists = CLAZZ.getDeclaredMethod("exists", String.class, String.class);
        Assertions.assertTrue((boolean) exists.invoke(userManager, login, password));
        Assertions.assertFalse((boolean) exists.invoke(userManager, login, login));
    }

    @Test
    void changePasswordMethodExists() throws NoSuchMethodException {
        hasPublicNoStaticMethod(CLAZZ, "changePassword", String.class, String.class, String.class);
    }

    @Test
    void changePasswordMethodTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor<?> constructor = CLAZZ.getDeclaredConstructor();
        Object userManager = constructor.newInstance();
        String login = "login";
        String oldPassword = "password";
        String newPassword = "qwerty";
        Field usersField = CLAZZ.getDeclaredField("users");
        usersField.setAccessible(true);
        Set users = (Set)usersField.get(userManager);
        users.add(new User(login, oldPassword));
        Assertions.assertEquals(1, users.size());
        Assertions.assertTrue(users.contains(new User(login, oldPassword)));
        Method changePassword = CLAZZ.getDeclaredMethod("changePassword", String.class, String.class, String.class);
        changePassword.invoke(userManager, login, oldPassword, newPassword);
        Assertions.assertEquals(1, users.size());
        Assertions.assertTrue(users.contains(new User(login, newPassword)));
    }
}

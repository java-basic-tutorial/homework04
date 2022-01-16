# Homework 04

## Inheritance. Generics. Collections

As you remember [the first homework](https://github.com/java-basic-tutorial/homework01) was dedicated 
writing a program that simulate a coffee machine.

The first and the second task of this homework also about Coffee Machine, but instead of using the procedure 
approach you will use the [Object Oriented Programming](https://docs.oracle.com/javase/tutorial/java/concepts/).

The third task give you the opportunity to practice of using [Java Collection Framework]().

## Task 01

In this task you will create the first version of Coffee Machine which should make `Espresso` and `Americano`.

First of all, lets take a look at the class [AbstractCoffeeMachine](src/main/java/com/softserveinc/task01/AbstractCoffeeMachine.java).

This class is abstract and has a few fields and methods. 
Pay attention at the method `makeCoffee()`. You can use it to make a `Espresso` and `Americano`.

Create a public class `CoffeeMachineV1` derived from [AbstractCoffeeMachine](src/main/java/com/softserveinc/task01/AbstractCoffeeMachine.java)
in the package [com.softserveinc.task01](src/main/java/com/softserveinc/task01).

After, you need to implement the abstract methods `makeEspresso()` and `makeAmericano()`. 
Both methods should invoke the method `makeCoffee()` defined in parent class [AbstractCoffeeMachine](src/main/java/com/softserveinc/task01/AbstractCoffeeMachine.java).
The methods should return `true` if it is enough resources (water and coffee beans) to make a coffee, otherwise `false`.

The method `makeEspresso()` intended to simulate making the `Espresso`.
One cup of `Espresso` made on this coffee machine contains `30 ml` of water, and `22 g` of coffee beans.

Method `makeAmericano()` intended to simulate making the `Americano`.
One cup of `Americano` made on this coffee machine contains `100 ml` of water, and `22 g` of coffee beans.

Also, you need to create a constructor with three arguments (see for example how is implemented the constructor in the [parent class](src/main/java/com/softserveinc/task01/AbstractCoffeeMachine.java)). 
**Important: you don't need to add a default constructor!**

Run the [unit test](src/test/java/com/softserveinc/task01/CoffeeMachineV1Test.java) to check you solution. 

## Task 02

The second version of Coffee Machine should make `Cappuccino` and `Latte`.
You need extend the first version of Coffee Machine to reuse of existing code.

In the package [com.softserveinc.task02](src/main/java/com/softserveinc/task02) create the class `CoffeeMachineV2` derived from
 [CoffeeMachineV1](src/main/java/com/softserveinc/task01/CoffeeMachineV1.java).
 
Add the field `milkReservoirCapacity` (**private final integer**) to represent how much milk can be poured in the Coffee Machine.

Add the public constructor with four parameters (three like in the super class and fourth's for `milkReservoirCapacity`).
**Don't forget to invoke the constructor of super class**. 

Add the field `milk` (**private integer**) to represent how much milk now is present in the Coffee Machine.

Add the method `addMilk()` to pour milk in Coffee Machine (see for example how is implemented the method `addWater()` 
and `addCoffeeBeans()` in the class [AbstractCoffeeMachine](src/main/java/com/softserveinc/task01/AbstractCoffeeMachine.java)).

Add the getter to the field `milk`.

Add the method `makeCappuccino()` intended to simulate making the `Cappuccino`.
To make `Cappuccino` it is need to make `Espresso` and add `85 ml` of milk.

Add the method `makeLatte()` intended to simulate making the `Latte`.
To make `Latte` it is need to make `Espresso` and add `150 ml` of milk.

Both methods `makeCappuccino()` and `makeLatte()` are returned boolean value that represent if it possible to make 
a coffee.

If it is not enough milk to make a coffee the methods should return false and print message `Not enough milk` 
by using `System.err.println()`

At the end run the [unit test](src/test/java/com/softserveinc/task02/CoffeeMachineV2Test.java) to check you solution. 

## Task 03

Develop an app that allows for saving logins and passwords. 
The pair login-password corresponds to a certain user.

Main functionality of the program:
1. Add a new user
2. Delete a user
3. Check if the user exists
4. Change the password of a user.

You will use the existing class [User](src/main/java/com/softserveinc/task03/User.java).
 
To do it you should create the public class `UserManager` in package [com.softserveinc.task03](src/main/java/com/softserveinc/task03).

Add the private final field called `users`. It should have the type of `Set<User>`.

Initialize this field in the default constructor (use `HashSet<>` class).

Add the method `register()` with two parameters: login and password of `String` type.
Method should create the [User](src/main/java/com/softserveinc/task03/User.java) and save it in the set `users`.
Method returns nothing (`void`).

Add the method `delete()` with parameter login of `String` type.
Method should delete the [User](src/main/java/com/softserveinc/task03/User.java) in the set `users`.
Method returns nothing (`void`).

Add the method `exists()` with two parameters: login and password of `String` type.
The method should check if the [User](src/main/java/com/softserveinc/task03/User.java) exists in the set `users`. 
The method returns the boolean value.
If exists than returns `true`, otherwise - `false`.

Add the method `changePassword()` with three parameters: `login`, `old password` and `new password`.
The method should find the [User](src/main/java/com/softserveinc/task03/User.java) with given `login` and `old password` in the set `users` and change the password to
 the `new password`.
The method returns the boolean value.
If the [User](src/main/java/com/softserveinc/task03/User.java) exists and password is changed (successfully) than `true`, otherwise - `false`.

At the end run the [unit test](src/test/java/com/softserveinc/task03/UserManagerTest.java) to check you solution. 

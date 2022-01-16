# homework04

## Inheritance. Generics. Collections

The first homework was dedicated 


## Task 01

In package [com.softserveinc.task01](src/main/java/com/softserveinc/task01) has the 
class [AbstractCoffeeMachine](src/main/java/com/softserveinc/task01/AbstractCoffeeMachine.java).

This class is abstract and has a few fields and methods. 

You need create a public class `CoffeeMachineV1` derived from [AbstractCoffeeMachine](src/main/java/com/softserveinc/task01/AbstractCoffeeMachine.java).

Implement the methods `makeEspresso()` and `makeAmericano()`. Both methods should invoke method `makeCoffee()` defined
in parent class `AbstractCoffeeMachine`.

Method `makeEspresso()` intended to create `Espresso` it is needed 22 g of coffee beans and 30 ml of water.

Method `makeAmericano()` intended to create `Americano` it is needed 22 g of coffee beans and 100 ml of water.

Also, you need to create a constructor with three arguments (exactly like in parent class). 
**You don't need a default constructor!**

## Task 02

## Task 03

Develop an app that allows for saving usernames and passwords. The pair username-password corresponds to a certain
user. When the app runs, this menu is displayed:
1. Add a new user;
2. Delete a user;
3. Check if the user exists;
4. Change the username of a user;
5. Change the password of a user.
Execute an action based on the user’s choice and display
the menu again. Use an appropriate class from the Java Collections Framework to solve this task.

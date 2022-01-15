# homework04

Inheritance. Generics. Collections

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

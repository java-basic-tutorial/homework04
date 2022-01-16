package com.softserveinc.task01;

public class CoffeeMachineV1 extends AbstractCoffeeMachine {
    public CoffeeMachineV1(int coffeeBeanStorageCapacity, int waterReservoirCapacity, int wasteCoffeeBeanCapacity) {
        super(coffeeBeanStorageCapacity, waterReservoirCapacity, wasteCoffeeBeanCapacity);
    }

    @Override
    public boolean makeEspresso() {
        return makeCoffee(22, 30);
    }

    @Override
    public boolean makeAmericano() {
        return makeCoffee(22, 100);
    }
}

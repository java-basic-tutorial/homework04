package com.softserveinc.task01;

public abstract class AbstractCoffeeMachine {
    private final int coffeeBeanStorageCapacity;
    private final int waterReservoirCapacity;
    private final int wasteCoffeeBeanCapacity;

    private int coffee;
    private int water;
    private int waste;

    private boolean isOn;

    public AbstractCoffeeMachine(int coffeeBeanStorageCapacity, int waterReservoirCapacity, int wasteCoffeeBeanCapacity) {
        this.coffeeBeanStorageCapacity = coffeeBeanStorageCapacity;
        this.waterReservoirCapacity = waterReservoirCapacity;
        this.wasteCoffeeBeanCapacity = wasteCoffeeBeanCapacity;
    }

    public void addWater(int water) {
        this.water = Math.min(water, waterReservoirCapacity);
    }

    public void addCoffeeBeans(int coffee) {
        this.coffee = Math.min(coffee, coffeeBeanStorageCapacity);
    }

    public void throwAwayWaste() {
        waste = 0;
    }

    public int getCoffee() {
        return coffee;
    }

    public int getWater() {
        return water;
    }

    public int getWaste() {
        return waste;
    }

    public abstract boolean makeEspresso();

    public abstract boolean makeAmericano();

    protected boolean makeCoffee(int coffee, int water) {
        if (!isOn) {
            return false;
        }
        if (coffee > this.coffee || water > this.water || waste >= wasteCoffeeBeanCapacity) {
            return false;
        }
        this.coffee -= coffee;
        this.water -= water;
        this.waste += coffee;
        return true;
    }

    public void on() {
        isOn = true;
    }

    public void off() {
        isOn = false;
    }

}

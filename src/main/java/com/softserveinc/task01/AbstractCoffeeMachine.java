package com.softserveinc.task01;

public abstract class AbstractCoffeeMachine {
    protected final int coffeeBeanStorageCapacity;
    protected final int waterReservoirCapacity;
    protected final int wasteCoffeeBeanCapacity;

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
        this.water = Math.min(water + this.water, waterReservoirCapacity);
    }

    public void addCoffeeBeans(int coffee) {
        this.coffee = Math.min(coffee + this.coffee, coffeeBeanStorageCapacity);
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
            System.err.println("Coffee machine is off");
            return false;
        }
        if (coffee > this.coffee) {
            System.err.println("Not enough coffee");
            return false;
        }
        if (water > this.water) {
            System.err.println("Not enough water");
            return false;
        }
        if (waste > wasteCoffeeBeanCapacity) {
            System.err.println("The storage of waste is full");
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

    @Override
    public String toString() {
        return "AbstractCoffeeMachine{" +
                "coffeeBeanStorageCapacity=" + coffeeBeanStorageCapacity +
                ", waterReservoirCapacity=" + waterReservoirCapacity +
                ", wasteCoffeeBeanCapacity=" + wasteCoffeeBeanCapacity +
                ", coffee=" + coffee +
                ", water=" + water +
                ", waste=" + waste +
                ", isOn=" + isOn +
                '}';
    }
}

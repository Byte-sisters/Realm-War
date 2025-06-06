package org.example.models.structures;

public abstract class Structures {
    int healthPoints;
    int level;
    int maintenanceCost;
    int price;
    int canPlaceUnit;

    public Structures(){
        this.healthPoints = 30;
        this.level = 1;
        this.maintenanceCost = 5;
        this.price = 10;
        this.canPlaceUnit = 1;
    }

    public void levelUp(){
        level++;
    }

    public int getCanPlaceUnit() {return canPlaceUnit;}

    public void loseHealthPoints(int damage){
        healthPoints -= damage;
    }

    public int getHealthPoints(){ return healthPoints; }

    public int getMaintenanceCost(){ return maintenanceCost; }

    public int getPrice(){ return price; }
}

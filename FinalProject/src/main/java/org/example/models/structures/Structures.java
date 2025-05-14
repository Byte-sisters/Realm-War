package org.example.models.structures;

public abstract class Structures {
    int healthPoints;
    int level;
    int maintenanceCost;

    public Structures(){
        this.healthPoints = 50;
        this.level = 1;
        this.maintenanceCost = 5;
    }

    public void levelUp(){
        level++;
    }

    public void loseHealthPoints(){
        healthPoints -= 10;
    }
}

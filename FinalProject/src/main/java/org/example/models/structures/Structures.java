package org.example.models.structures;

import org.example.models.player.Player;

import javax.swing.*;

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

    public boolean levelUp(Player player){
        if(player.getGold() >= 5 && level<4){
            System.out.println(level);
            player.setGold(player.getGold() - 5);
            healthPoints +=10;
            level++;
            return true;
        }
        return false;
    }

    public int getCanPlaceUnit() {return canPlaceUnit;}

    public void loseHealthPoints(int damage){
        healthPoints -= damage;
    }

    public int getHealthPoints(){ return healthPoints; }

    public int getMaintenanceCost(){ return maintenanceCost; }

    public int getPrice(){ return price; }
    public int getLevel(){ return level;

    }
}

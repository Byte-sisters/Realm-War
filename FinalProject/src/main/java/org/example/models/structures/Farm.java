package org.example.models.structures;

import org.example.models.player.Player;

public class Farm extends Structures{
    private int addFood;
    public Farm() {
        super();
        addFood = 5;
    }
    public int getaddFood() { return addFood;}
    @Override
    public  boolean levelUp(Player player) {
        if(player.getGold() >= 5 && level<4){
            player.setGold(player.getGold() - 5);
            healthPoints +=10;
            addFood += 5;
            level++;
            return true;
        }
        return false;

    }
}
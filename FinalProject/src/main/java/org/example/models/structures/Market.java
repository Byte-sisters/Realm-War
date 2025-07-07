package org.example.models.structures;

import org.example.models.player.Player;

public class Market extends Structures {
    private int addGold;

    public Market() {
        super();
        addGold = 5;
    }

    public int getaddGold() {
        return addGold;
    }

    @Override
    public  boolean levelUp(Player player) {
        if(player.getGold() >= 5 && level<4){
            player.setGold(player.getGold() - 5);
            healthPoints +=10;
            addGold += 5;
            level++;
            return true;
        }
        return false;

    }
}



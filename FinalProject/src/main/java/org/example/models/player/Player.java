package org.example.models.player;

import org.example.models.structures.*;
import org.example.models.units.*;

import java.util.ArrayList;

public class Player {
    private String name;
    private TownHall townHall;
    private int gold;
    private int foodSupply;
    private ArrayList<Barrack> barracks;
    private ArrayList<Farm> farms;
    private ArrayList<Market> markets;
    private ArrayList<Tower> towers;

    private ArrayList<Peasant> peasants;
    private ArrayList<Knight> knights;
    private ArrayList<Spearman> spearmen;
    private ArrayList<Swordman> swordmen;

    private boolean[][] placeUnit;

    public Player(String name) {
        peasants = new ArrayList<>();
        knights = new ArrayList<>();
        spearmen = new ArrayList<>();
        swordmen = new ArrayList<>();
        towers = new ArrayList<>();
        barracks = new ArrayList<>();
        farms = new ArrayList<>();
        markets = new ArrayList<>();
        placeUnit = new boolean[12][12];
        this.name = name;
        this.townHall = new TownHall();
        this.gold = 10;
        this.foodSupply = 10;
    }

    public void changePairTurn(){
        if(!markets.isEmpty()) for(Market market : markets){ gold += market.getaddGold(); }
        if(!farms.isEmpty()) for(Farm farm : farms){ foodSupply += farm.getaddFood(); }
        if(!spearmen.isEmpty()) for(Spearman spearman : spearmen){ gold -= spearman.getPayment(); }
        if(!peasants.isEmpty()) for(Peasant peasant : peasants){ gold -= peasant.getPayment(); }
        if(!knights.isEmpty()) for(Knight knight : knights){ gold -= knight.getPayment(); }
        if(!swordmen.isEmpty()) for(Swordman swordman : swordmen){gold -= swordman.getPayment(); }
    }

    public boolean canMakeBarracks() {
        return barracks.size()<=5;
    }
    public boolean canMakeFarms() {
        return farms.size()<=5;
    }
    public boolean canMakeMarkets() {
        return markets.size()<=5;
    }
    public boolean canMakeTowers() {
        return towers.size()<=5;
    }

    public boolean canMakePeasants() {
        return peasants.size()<=10;
    }
    public boolean canMakeKnights() {
        return knights.size()<=10;
    }
    public boolean canMakeSpearman() {
        return spearmen.size()<=10;
    }
    public boolean canMakeSwordman() {
        return swordmen.size()<=10;
    }



    public String getName() {
        return name;
    }

    public boolean hasLost(){
        return townHall.getHealthPoints()==0;
    }

    public int getGold() {
        return gold;
    }

    public int getFoodSupply() {return foodSupply;}
    public void setGold(int gold) {
        this.gold = gold;
    }

    public ArrayList<Farm> getFarms() {
        return farms;
    }
    public ArrayList<Market> getMarkets() {
        return markets;
    }
    public ArrayList<Tower> getTowers() {
        return towers;
    }
    public ArrayList<Barrack> getBarracks() {
        return barracks;
    }

    public ArrayList<Knight> getKnights() { return knights; }
    public ArrayList<Spearman> getSpearmen() { return spearmen; }
    public ArrayList<Swordman> getSwordmen() { return swordmen; }
    public ArrayList<Peasant> getPeasants() { return peasants; }

    public boolean HaveMoneyToPayForBarrack(Barrack barrack) {
        return getGold() >= barrack.getPrice() && canMakeBarracks();
    }

    public boolean HaveMoneyToPayForFarm(Farm farm) {
        return getGold() >= farm.getPrice() && canMakeFarms();
    }
    public boolean HaveMoneyToPayForMarket(Market market) {
        return getGold() >= market.getPrice() && canMakeMarkets();
    }

    public boolean HaveMoneyToPayForTower(Tower tower) {
        return getGold() >= tower.getPrice() && canMakeTowers();
    }

     public boolean HaveMoneyToPayForPeasant(Peasant peasant) {
         return getGold()-peasant.getPrice()>0 && canMakePeasants() ;
     }

     public boolean HaveMoneyToPayForKnight(Knight knight) {
        return getGold()-knight.getPrice()>0 && canMakeKnights();
     }
     public boolean HaveMoneyToPayForSpearman(Spearman spearman) {
         return getGold()-spearman.getPrice()>0 && canMakeSpearman();
     }
     public boolean HaveMoneyToPayForSwordMan(Swordman swordman) {
        return getGold()-swordman.getPrice()>0 && canMakeSwordman();
     }

     public void setPlaceUnit(Units unit, int i, int j) {
        int radius = unit.getMovementRange();

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                int newRow = i + dx;
                int newCol = j + dy;

                if (newRow >= 0 && newRow < placeUnit.length && newCol >= 0 && newCol < placeUnit[0].length) {
                    placeUnit[newRow][newCol] = true;
                }
            }
        }

     }

    public void setPlaceUnit(Structures structure, int i, int j) {
        int radius = structure.getCanPlaceUnit();

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                int newRow = i + dx;
                int newCol = j + dy;

                if (newRow >= 0 && newRow < placeUnit.length && newCol >= 0 && newCol < placeUnit[0].length) {
                    placeUnit[newRow][newCol] = true;
                }
            }
        }

    }

     public boolean[][] getPlaceUnit() {
        return placeUnit;
     }

    public TownHall getTownHall() {
        return townHall;
    }

}

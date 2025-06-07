package org.example.models.units;

public abstract class Units {
    int hitPoint;
    int movementRange;
    int attackPower;
    int attackRange;
    int payment;
    int ration;
    int unitSpace;
    int price;

    public Units() {

        this.hitPoint = 20;
        this.movementRange = 2;
        this.attackPower=0;
        this.attackRange=5;
        this.payment = 1;
        this.ration = 1;
        this.unitSpace = 0;
        this.price = 10;
    }
    public int getRation(){return ration;}
    public int getPrice(){
        return this.price;
    }
    public int getPayment(){ return this.payment; }
    public int getMovementRange(){ return this.movementRange; }

    public int getAttackPower() {
        return attackPower;
    }
    public void takeDamage(int damage) {
        hitPoint = hitPoint - damage;
    }
    public int getHitPoint() {return hitPoint;}
}

package org.example.models.units;

public abstract class Units {
    int hitPoint;
    int movementRange;
    int attackPower;
    int attackRange;
    int payment;
    int ration;
    int unitSpace;

    public Units() {

        this.hitPoint = 0;
        this.movementRange = 0;
        this.attackPower=0;
        this.attackRange=0;
        this.payment = 0;
        this.ration = 0;
        this.unitSpace = 0;

    }
}

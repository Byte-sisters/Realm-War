package org.example.models.blocks;

public abstract class Blocks {
    int column;
    int row;
    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
}

package org.example.models;

import org.example.models.blocks.*;

import java.util.ArrayList;

public class BoardState {
    public ArrayList<EmptyBlock> blocks;
    public ArrayList<ForestBlock> trees;

    public BoardState(ArrayList<EmptyBlock> blocks, ArrayList<ForestBlock> trees) {
        this.blocks = blocks;
        this.trees = trees;
    }
}


package org.example.controller;

import org.example.models.player.Player;
import org.example.swing.NameInput;
import java.util.ArrayList;

public class Game {
    ArrayList<Player> players;
    int numbersOfPlayer;
    public Game(int numbersOfPlayer) {
        this.numbersOfPlayer = numbersOfPlayer;
        players = new ArrayList<>(numbersOfPlayer);
        for (int i = 0; i < numbersOfPlayer; i++) {
            NameInput input = new NameInput();
            Player player = new Player(input.getPlayerName());
            players.add(player);
        }
    }
}

package org.example.controller;

import org.example.models.player.Player;
import org.example.swing.GUI;

import javax.swing.*;
import java.util.ArrayList;

public class Game {
    private GUI gui;
    private ArrayList<Player> players;
    private int currentPlayerIndex;
    private ArrayList<Player> winners;

    public Game(GUI gui, ArrayList<Player> players) {
        this.gui = gui;
        this.players = players;
        this.currentPlayerIndex = 0;
        this.winners = new ArrayList<>();
        nextTurn();
    }

    public void nextTurn() {
        if (players.size() == 1) {
            winners.add(players.get(0));
            gui.ShowEndGameWindow(winners);
            return;
        }

        Player currentPlayer = players.get(currentPlayerIndex);

        if (currentPlayer.hasLost()) {
            winners.add(currentPlayer);
            players.remove(currentPlayerIndex);

            if (players.size() == 1) {
                winners.add(players.get(0));
                gui.ShowEndGameWindow(winners);
                return;
            }

            currentPlayerIndex = currentPlayerIndex % players.size();

            JOptionPane.showMessageDialog(gui, currentPlayer.getName() + " Has Lost!", "Player Eliminated", JOptionPane.INFORMATION_MESSAGE);
            nextTurn();
        } else {
            currentPlayer.changeFoodPairTurn();
            currentPlayer.changeGoldPairTurn();
            gui.updateResourceLabels(currentPlayer);
            gui.ShowGameBoardWindow(currentPlayer);
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }
}

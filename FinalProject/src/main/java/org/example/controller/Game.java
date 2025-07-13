package org.example.controller;

import org.example.DataBase.DB;
import org.example.models.player.Player;
import org.example.swing.GUI;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {
    private GUI gui;
    private ArrayList<Player> players;
    private int currentPlayerIndex;
    private ArrayList<Player> winners;
    private ScheduledExecutorService turnScheduler = Executors.newSingleThreadScheduledExecutor();
    private DB db;


    public Game(GUI gui, ArrayList<Player> players, DB db) {
        this.gui = gui;
        this.db = db;
        this.players = players;
        this.currentPlayerIndex = 0;
        this.winners = new ArrayList<>();
        nextTurn();
    }

    public void nextTurn() {
        if (players.size() == 1) {
            Player lastPlayer = players.get(0);
            if (!winners.contains(lastPlayer)) {
                winners.add(lastPlayer);
            }
            db.saveGameWinners(winners);
            gui.ShowEndGameWindow(winners);
            turnScheduler.shutdown();
            return;
        }

        Player currentPlayer = players.get(currentPlayerIndex);

        if (currentPlayer.hasLost()) {
            if (!winners.contains(currentPlayer)) {
                winners.add(currentPlayer);
            }
            players.remove(currentPlayerIndex);

            JOptionPane.showMessageDialog(gui, currentPlayer.getName() + " Has Lost!", "Player Eliminated", JOptionPane.INFORMATION_MESSAGE);

            if (players.size() > 0) {
                currentPlayerIndex = currentPlayerIndex % players.size();
            }

            return;
        }

        currentPlayer.changeFoodPairTurn();
        currentPlayer.changeGoldPairTurn();
        gui.updateResourceLabels(currentPlayer);
        gui.ShowGameBoardWindow(currentPlayer);
        gui.startCountdown(20);

        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public void startTurnLoop() {
        turnScheduler.scheduleAtFixedRate(() -> {
            SwingUtilities.invokeLater(() -> {
                try {
                    nextTurn();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }, 0, 20, TimeUnit.SECONDS);
    }

}
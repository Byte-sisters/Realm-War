package org.example.controller;

import org.example.models.Board;
import org.example.models.player.Player;
import org.example.swing.GUI;
import java.util.concurrent.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//public class Game {
//    private GUI gui;
//    private ArrayList<Player> players;
//    private int currentPlayerIndex;
//    private ArrayList<Player> winners;
//    private ScheduledExecutorService turnScheduler = Executors.newSingleThreadScheduledExecutor();
//    public Board board;
//
//    public Game(GUI gui, ArrayList<Player> players, Board board) {
//        this.gui = gui;
//        this.players = players;
//        this.currentPlayerIndex = 0;
//        this.winners = new ArrayList<>();
//        this.board = board;
//        nextTurn();
//    }
//
//    public void nextTurn() {
//        if (players.size() == 1) {
//            winners.add(players.get(0));
//            gui.ShowEndGameWindow(winners);
//            turnScheduler.shutdown();
//            return;
//        }
//
//        Player currentPlayer = players.get(currentPlayerIndex);
//
//        if (currentPlayer.hasLost()) {
//            winners.add(currentPlayer);
//            players.remove(currentPlayerIndex);
//
//            if (players.size() == 1) {
//                winners.add(players.get(0));
//                gui.ShowEndGameWindow(winners);
//                turnScheduler.shutdown();
//                return;
//            }
//
//            currentPlayerIndex = currentPlayerIndex % players.size();
//
//            JOptionPane.showMessageDialog(gui, currentPlayer.getName() + " Has Lost!", "Player Eliminated", JOptionPane.INFORMATION_MESSAGE);
//            nextTurn();
//        } else {
//            currentPlayer.changeFoodPairTurn();
//            currentPlayer.changeGoldPairTurn();
//            gui.updateResourceLabels(currentPlayer);
//            gui.ShowGameBoardWindow(currentPlayer,board);
//            gui.startCountdown(20);
//            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
//        }
//    }
//    public void startTurnLoop() {
//        turnScheduler.scheduleAtFixedRate(() -> {
//            SwingUtilities.invokeLater(() -> {
//                try {
//                    nextTurn();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            });
//        }, 0, 20, TimeUnit.SECONDS);
//    }
//
//}

public class Game {
    private GUI gui;
    private ArrayList<Player> players;
    private int currentPlayerIndex;
    private ArrayList<Player> winners;
    private Board board;
    private ScheduledExecutorService turnScheduler;

    public Game(GUI gui, ArrayList<Player> players, Board board) {
        this.gui = gui;
        this.players = players;
        this.currentPlayerIndex = 0;
        this.winners = new ArrayList<>();
        this.board = board;
        // حذف nextTurn() از constructor
    }

    public void startTurnLoop() {
        // هر بار بازی جدید شروع شد → یک scheduler جدید می‌سازیم
        if (turnScheduler == null || turnScheduler.isShutdown() || turnScheduler.isTerminated()) {
            turnScheduler = Executors.newScheduledThreadPool(1);
        }

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

    public void stopTurnLoop() {
        if (turnScheduler != null && !turnScheduler.isShutdown()) {
            turnScheduler.shutdown();
        }
    }

    public void nextTurn() {
        if (players.size() == 1) {
            winners.add(players.get(0));
            gui.ShowEndGameWindow(winners);
            stopTurnLoop(); // به جای turnScheduler.shutdown()
            return;
        }

        Player currentPlayer = players.get(currentPlayerIndex);

        if (currentPlayer.hasLost()) {
            winners.add(currentPlayer);
            players.remove(currentPlayerIndex);

            if (players.size() == 1) {
                winners.add(players.get(0));
                gui.ShowEndGameWindow(winners);
                stopTurnLoop();
                return;
            }

            currentPlayerIndex = currentPlayerIndex % players.size();

            JOptionPane.showMessageDialog(gui, currentPlayer.getName() + " Has Lost!", "Player Eliminated", JOptionPane.INFORMATION_MESSAGE);
            nextTurn();
        } else {
            currentPlayer.changeFoodPairTurn();
            currentPlayer.changeGoldPairTurn();
            gui.updateResourceLabels(currentPlayer);
            gui.ShowGameBoardWindow(currentPlayer, board);
            gui.startCountdown(20);
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }
}


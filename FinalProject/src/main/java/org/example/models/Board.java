package org.example.models;

import org.example.models.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Board extends JPanel {
    int rows = 12;
    int cols = 12;
    private JButton[][] buttons = new JButton[rows][cols];
    private ImageIcon voidIcon;
    private ImageIcon emptyIcon;
    private ImageIcon forestIcon;
    private ImageIcon townHallIcon;
    private ImageIcon player1;
    private ImageIcon player2;
    private ImageIcon player3;
    private ImageIcon player4;

    public Board(int numberOfPlayers) {
        voidIcon = new ImageIcon("img/Void.jpg");
        emptyIcon = new ImageIcon("img/Empty.jpg");
        forestIcon = new ImageIcon("img/Forest.png");
        townHallIcon = new ImageIcon("img/TownHall.jpg");
        player1 = new ImageIcon("img/player1.png");
        player2 = new ImageIcon("img/player2.jpg");
        player3 = new ImageIcon("img/player3.jpg");
        player4 = new ImageIcon("img/player4.png");

        this.setLayout(new GridLayout(rows, cols));

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JButton button = new JButton();
                if (row == 0 || col == 0 || row == rows - 1 || col == cols - 1) {
                    button.setIcon(voidIcon);
                    button.setEnabled(false);
                } else {
                    button.setIcon(emptyIcon);
                }
                buttons[row][col] = button;
                this.add(button);
            }
        }
        switch(numberOfPlayers) {

            case 1, 2:
                buttons[rows - 2][1].setIcon(townHallIcon);
                buttons[rows - 3][1].setIcon(player1);
                buttons[rows - 3][2].setIcon(player1);
                buttons[rows - 2][2].setIcon(player1);

                buttons[1][cols -2].setIcon(townHallIcon);
                buttons[1][cols - 3].setIcon(player2);
                buttons[2][cols -3].setIcon(player2);
                buttons[2][cols - 2].setIcon(player2);

                break;

            case 3:
                buttons[rows - 2][1].setIcon(townHallIcon);
                buttons[rows - 3][1].setIcon(player1);
                buttons[rows - 3][2].setIcon(player1);
                buttons[rows - 2][2].setIcon(player1);

                buttons[1][cols -2].setIcon(townHallIcon);
                buttons[1][cols - 3].setIcon(player2);
                buttons[2][cols -3].setIcon(player2);
                buttons[2][cols - 2].setIcon(player2);

                buttons[rows - 2][cols-2].setIcon(townHallIcon);
                buttons[rows - 3][cols -2].setIcon(player3);
                buttons[rows - 3][cols -3].setIcon(player3);
                buttons[rows - 2][cols - 3].setIcon(player3);

                break;

            case 4:
                buttons[rows - 2][1].setIcon(townHallIcon);
                buttons[rows - 3][1].setIcon(player1);
                buttons[rows - 3][2].setIcon(player1);
                buttons[rows - 2][2].setIcon(player1);

                buttons[1][cols -2].setIcon(townHallIcon);
                buttons[1][cols - 3].setIcon(player2);
                buttons[2][cols -3].setIcon(player2);
                buttons[2][cols - 2].setIcon(player2);

                buttons[rows - 2][cols-2].setIcon(townHallIcon);
                buttons[rows - 3][cols -2].setIcon(player3);
                buttons[rows - 3][cols -3].setIcon(player3);
                buttons[rows - 2][cols - 3].setIcon(player3);

                buttons[1][1].setIcon(townHallIcon);
                buttons[1][2].setIcon(player4);
                buttons[2][1].setIcon(player4);
                buttons[2][2].setIcon(player4);
                break;

            default:

        }

        int count = 0;
        Random rand = new Random();
        while (count < 10) {
            int row = rand.nextInt(rows - 2) + 1;
            int col = rand.nextInt(cols - 2) + 1;

            if (!(buttons[row][col].getIcon().equals(forestIcon) ||
                    buttons[row][col].getIcon().equals(voidIcon) ||
                    buttons[row][col].getIcon().equals(townHallIcon) ||
                    buttons[row][col].getIcon().equals(player1) ||
                    buttons[row][col].getIcon().equals(player2) ||
                    buttons[row][col].getIcon().equals(player3) ||
                    buttons[row][col].getIcon().equals(player4))) {
                buttons[row][col].setIcon(forestIcon);
                count++;
            }
        }

    }

    public void update(Player player) {
        if(!canPlaceStructure()){
            JOptionPane.showMessageDialog(this, "You can't place a structure!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean canPlaceStructure() {
        if(){
            return true;
        }
        return false;
    }
}



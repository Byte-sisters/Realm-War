package org.example.models;

import org.example.models.player.Player;
import org.example.models.structures.*;
import org.example.models.units.*;
import org.example.swing.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Board extends JPanel {
    int rows = 12;
    int cols = 12;
    public JButton[][] buttons = new JButton[rows][cols];
    private ImageIcon voidIcon;
    private ImageIcon emptyIcon;
    private ImageIcon forestIcon;
    private ImageIcon townHallIcon;
    private ImageIcon player1;
    private ImageIcon player2;
    private ImageIcon player3;
    private ImageIcon player4;
    private ImageIcon farmIcon;
    private ImageIcon barrackIcon;
    private ImageIcon marketIcon;
    private ImageIcon towerIcon;
    private ImageIcon peasantIcon;
    private ImageIcon spearmanIcon;
    private ImageIcon swordManIcon;
    private ImageIcon knightIcon;

    private GUI gui;  // Add GUI reference here

    public Board(int numberOfPlayers, GUI gui) {
        this.gui = gui;

        voidIcon = new ImageIcon("img/Void.jpg");
        emptyIcon = new ImageIcon("img/Empty.jpg");
        forestIcon = new ImageIcon("img/Forest.png");
        townHallIcon = new ImageIcon("img/TownHall.png");
        player1 = new ImageIcon("img/player1.png");
        player2 = new ImageIcon("img/player2.jpg");
        player3 = new ImageIcon("img/player3.jpg");
        player4 = new ImageIcon("img/player4.png");
        farmIcon = new ImageIcon("img/Farm.png");
        barrackIcon = new ImageIcon("img/Barrack.jpg");
        marketIcon = new ImageIcon("img/Market.jpg");
        towerIcon = new ImageIcon("img/Tower.jpg");
        peasantIcon = new ImageIcon("img/peasent.jpeg");
        spearmanIcon = new ImageIcon("img/spearman.jpeg");
        swordManIcon = new ImageIcon("img/swordMan.jpeg");
        knightIcon = new ImageIcon("img/knight.jpeg");


        this.setLayout(new GridLayout(rows, cols));

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JButton button = new JButton();
                if (row == 0 || col == 0 || row == rows - 1 || col == cols - 1) {
                    button.setIcon(voidIcon);
                    button.setBackground(Color.WHITE);
                    button.setEnabled(false);
                } else {
                    button.setIcon(emptyIcon);
                    button.setBackground(Color.WHITE);
                }
                buttons[row][col] = button;
                this.add(button);
            }
        }

        switch (numberOfPlayers) {
            case 1, 2:
                buttons[rows - 2][1].setIcon(townHallIcon);
                buttons[rows - 3][1].setIcon(player1);
                buttons[rows - 3][2].setIcon(player1);
                buttons[rows - 2][2].setIcon(player1);

                buttons[1][cols - 2].setIcon(townHallIcon);
                buttons[1][cols - 3].setIcon(player2);
                buttons[2][cols - 3].setIcon(player2);
                buttons[2][cols - 2].setIcon(player2);
                break;

            case 3:
                buttons[rows - 2][1].setIcon(townHallIcon);
                buttons[rows - 3][1].setIcon(player1);
                buttons[rows - 3][2].setIcon(player1);
                buttons[rows - 2][2].setIcon(player1);

                buttons[1][cols - 2].setIcon(townHallIcon);
                buttons[1][cols - 3].setIcon(player2);
                buttons[2][cols - 3].setIcon(player2);
                buttons[2][cols - 2].setIcon(player2);

                buttons[rows - 2][cols - 2].setIcon(townHallIcon);
                buttons[rows - 3][cols - 2].setIcon(player3);
                buttons[rows - 3][cols - 3].setIcon(player3);
                buttons[rows - 2][cols - 3].setIcon(player3);
                break;

            case 4:
                buttons[rows - 2][1].setIcon(townHallIcon);
                buttons[rows - 3][1].setIcon(player1);
                buttons[rows - 3][2].setIcon(player1);
                buttons[rows - 2][2].setIcon(player1);

                buttons[1][cols - 2].setIcon(townHallIcon);
                buttons[1][cols - 3].setIcon(player2);
                buttons[2][cols - 3].setIcon(player2);
                buttons[2][cols - 2].setIcon(player2);

                buttons[rows - 2][cols - 2].setIcon(townHallIcon);
                buttons[rows - 3][cols - 2].setIcon(player3);
                buttons[rows - 3][cols - 3].setIcon(player3);
                buttons[rows - 2][cols - 3].setIcon(player3);

                buttons[1][1].setIcon(townHallIcon);
                buttons[1][2].setIcon(player4);
                buttons[2][1].setIcon(player4);
                buttons[2][2].setIcon(player4);
                break;

            default:
                // Handle other cases if needed
                break;
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

    public void update(Player player, ArrayList<Player> players, int i, int j, Structures selectedStructure,Units selectedUnit) {
        if(selectedStructure != null) {
            if (!canPlaceStructure(player, players, i, j)) {
                JOptionPane.showMessageDialog(this, "You can't place a structure here!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (selectedStructure instanceof Farm) {
                Farm farm = new Farm();
                if (player.HaveMoneyToPayForFarm(farm)) {
                    player.setGold(player.getGold() - farm.getPrice());
                    player.getFarms().add(farm);
                    player.setPlaceUnit(farm,i,j);
                    buttons[i][j].setIcon(farmIcon);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough gold or max farm limit reached!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (selectedStructure instanceof Barrack) {
                Barrack barrack = new Barrack();
                if (player.HaveMoneyToPayForBarrack(barrack)) {
                    player.setGold(player.getGold() - barrack.getPrice());
                    player.getBarracks().add(barrack);
                    player.setPlaceUnit(barrack,i,j);
                    buttons[i][j].setIcon(barrackIcon);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough gold or max barrack limit reached!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (selectedStructure instanceof Market) {
                Market market = new Market();
                if (player.HaveMoneyToPayForMarket(market)) {
                    player.setGold(player.getGold() - market.getPrice());
                    player.getMarkets().add(market);
                    player.setPlaceUnit(market,i,j);
                    buttons[i][j].setIcon(marketIcon);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough gold or max market limit reached!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (selectedStructure instanceof Tower) {
                Tower tower = new Tower();
                if (player.HaveMoneyToPayForTower(tower)) {
                    player.setGold(player.getGold() - tower.getPrice());
                    player.getTowers().add(tower);
                    player.setPlaceUnit(tower,i,j);
                    buttons[i][j].setIcon(towerIcon);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough gold or max tower limit reached!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        } else if (selectedUnit != null) {
            if (!canPlaceUnit(player, players, i, j)) {
                JOptionPane.showMessageDialog(this, "You can't place a unit here!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (selectedUnit instanceof Peasant) {
                Peasant peasant = new Peasant();
                if (player.HaveMoneyToPayForPeasant(peasant)) {
                    player.setGold(player.getGold() - peasant.getPrice());
                    player.getPeasants().add(peasant);
                    buttons[i][j].setIcon(peasantIcon);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough gold or max peasant limit reached!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (selectedUnit instanceof Spearman) {
                Spearman spearman = new Spearman();
                if (player.HaveMoneyToPayForSpearman(spearman)) {
                    player.setGold(player.getGold() - spearman.getPrice());
                    player.getSpearmen().add(spearman);
                    buttons[i][j].setIcon(spearmanIcon);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough gold or max spearman limit reached!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (selectedUnit instanceof Swordman) {
                Swordman swordman = new Swordman();
                if (player.HaveMoneyToPayForSwordMan(swordman)) {
                    player.setGold(player.getGold() - swordman.getPrice());
                    player.getSwordmen().add(swordman);
                    buttons[i][j].setIcon(swordManIcon);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough gold or max swordMan limit reached!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (selectedUnit instanceof Knight) {
                Knight knight = new Knight();
                if (player.HaveMoneyToPayForKnight(knight)) {
                    player.setGold(player.getGold() - knight.getPrice());
                    player.getKnights().add(knight);
                    buttons[i][j].setIcon(knightIcon);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough gold or max knight limit reached!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
    }

    public boolean canPlaceStructure(Player player, ArrayList<Player> players, int i, int j) {
        ImageIcon[] playerIcons = {player1, player2, player3, player4};
        int playerIndex = players.indexOf(player);
        if (playerIndex == -1 || playerIndex >= playerIcons.length) return false;

        return playerIcons[playerIndex].equals(buttons[i][j].getIcon());
    }

    public boolean canPlaceUnit(Player player, ArrayList<Player> players, int i, int j) {
        ImageIcon[] playerIcons = {player1, player2, player3, player4};
        int playerIndex = players.indexOf(player);
        if (playerIndex == -1 || playerIndex >= playerIcons.length) return false;

        return player.getPlaceUnit()[i][j];
    }

    public boolean isPlayerUnitAt(Player player, int row, int col) {
        Icon icon = buttons[row][col].getIcon();
        return icon.equals(peasantIcon) || icon.equals(spearmanIcon) ||
                icon.equals(swordManIcon) || icon.equals(knightIcon);
    }

    public boolean moveUnit(Player player,ArrayList<Player> players, int fromRow, int fromCol, int toRow, int toCol) {
        Icon iconTo = buttons[toRow][toCol].getIcon();
        if(!canPlaceUnit(player, players, toRow , toCol) || (fromRow == toRow && fromCol == toCol) || iconTo.equals(voidIcon)) {
            JOptionPane.showMessageDialog(this, "You can't move a unit here!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Icon icon = buttons[fromRow][fromCol].getIcon();
        buttons[toRow][toCol].setIcon(icon);
        ImageIcon[] playerIcons = {player1, player2, player3, player4};
        int playerIndex = players.indexOf(player);
        buttons[fromRow][fromCol].setIcon(playerIcons[playerIndex]);

        return true;
    }

}

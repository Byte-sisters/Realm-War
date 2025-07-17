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
                    player.buyStructure(farm);
                    player.getFarms().add(farm);
                    player.setPlaceUnit(farm,i,j);
                    buttons[i][j].setIcon(farmIcon);
                    player.setHasStructure(i,j);
                    player.setOwns(i,j,true);
                    player.setStructureAt(i,j,farm);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough gold or max farm limit reached!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (selectedStructure instanceof Barrack) {
                Barrack barrack = new Barrack();
                if (player.HaveMoneyToPayForBarrack(barrack)) {
                    player.buyStructure(barrack);
                    player.getBarracks().add(barrack);
                    player.setPlaceUnit(barrack,i,j);
                    buttons[i][j].setIcon(barrackIcon);
                    player.setHasStructure(i,j);
                    player.setOwns(i,j,true);
                    player.setStructureAt(i,j,barrack);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough gold or max barrack limit reached!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (selectedStructure instanceof Market) {
                Market market = new Market();
                if (player.HaveMoneyToPayForMarket(market)) {
                    player.buyStructure(market);
                    player.getMarkets().add(market);
                    player.setPlaceUnit(market,i,j);
                    buttons[i][j].setIcon(marketIcon);
                    player.setHasStructure(i,j);
                    player.setOwns(i,j,true);
                    player.setStructureAt(i,j,market);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough gold or max market limit reached!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (selectedStructure instanceof Tower) {
                Tower tower = new Tower();
                if (player.HaveMoneyToPayForTower(tower)) {
                    player.buyStructure(tower);
                    player.getTowers().add(tower);
                    player.setPlaceUnit(tower,i,j);
                    buttons[i][j].setIcon(towerIcon);
                    player.setHasStructure(i,j);
                    player.setOwns(i,j,true);
                    player.setStructureAt(i,j,tower);
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
                    player.buyUnit(peasant);
                    player.getPeasants().add(peasant);
                    if(buttons[i][j].getIcon().equals(forestIcon)){
                        player.UpdateFoodSupply();
                        gui.updateFoodLabel(player);
                    }
                    buttons[i][j].setIcon(peasantIcon);
                    player.setOwns(i,j,true);
                    player.setUnitAt(i,j,peasant);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough gold or max peasant limit reached!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (selectedUnit instanceof Spearman) {
                Spearman spearman = new Spearman();
                if (player.HaveMoneyToPayForSpearman(spearman)) {
                    player.buyUnit(spearman);
                    player.getSpearmen().add(spearman);
                    if(buttons[i][j].getIcon().equals(forestIcon)){
                        player.UpdateFoodSupply();
                        gui.updateFoodLabel(player);
                    }
                    buttons[i][j].setIcon(spearmanIcon);
                    player.setOwns(i,j,true);
                    player.setUnitAt(i,j,spearman);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough gold or max spearman limit reached!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (selectedUnit instanceof Swordman) {
                Swordman swordman = new Swordman();
                if (player.HaveMoneyToPayForSwordMan(swordman)) {
                    player.buyUnit(swordman);
                    player.getSwordmen().add(swordman);
                    if(buttons[i][j].getIcon().equals(forestIcon)){
                        player.UpdateFoodSupply();
                        gui.updateFoodLabel(player);
                    }
                    buttons[i][j].setIcon(swordManIcon);
                    player.setOwns(i,j,true);
                    player.setUnitAt(i,j,swordman);
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough gold or max swordMan limit reached!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (selectedUnit instanceof Knight) {
                Knight knight = new Knight();
                if (player.HaveMoneyToPayForKnight(knight)) {
                    player.buyUnit(knight);
                    player.getKnights().add(knight);
                    if(buttons[i][j].getIcon().equals(forestIcon)){
                        player.UpdateFoodSupply();
                        gui.updateFoodLabel(player);
                    }
                    buttons[i][j].setIcon(knightIcon);
                    player.setOwns(i,j,true);
                    player.setUnitAt(i,j,knight);
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
        Icon selectedIcon = buttons[i][j].getIcon();

        if (selectedIcon.equals(towerIcon)
                || selectedIcon.equals(farmIcon)
                || selectedIcon.equals(marketIcon)
                || selectedIcon.equals(barrackIcon)){
            return false;
        }

        return playerIcons[playerIndex].equals(buttons[i][j].getIcon());
    }

    public boolean canPlaceUnit(Player player, ArrayList<Player> players, int i, int j) {
        ImageIcon[] playerIcons = {player1, player2, player3, player4};
        int playerIndex = players.indexOf(player);
        if (playerIndex == -1 || playerIndex >= playerIcons.length) return false;
        if(player.getHasStructure(i,j)){
            return false;
        }
        if(getUnitAt(player,i,j)!=null){
            return false;
        }
        return player.getPlaceUnit()[i][j];
    }


    public boolean isPlayerUnitAt(Player player, int row, int col) {
        Icon icon = buttons[row][col].getIcon();
        return icon.equals(peasantIcon) || icon.equals(spearmanIcon) ||
                icon.equals(swordManIcon) || icon.equals(knightIcon);
    }

    public boolean moveUnit(Player player, ArrayList<Player> players, int fromRow, int fromCol, int toRow, int toCol, Units unit) {
        if (fromRow == toRow && fromCol == toCol) {
            JOptionPane.showMessageDialog(this, "You can't move a unit here!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (player.getHasStructure(toRow, toCol)) {
            JOptionPane.showMessageDialog(this, "You can't move a unit here!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (player.isThereTownHall(toRow, toCol)) {
            JOptionPane.showMessageDialog(this, "You can't move a unit here!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!player.getOwns(fromRow,fromCol)){
            JOptionPane.showMessageDialog(this, "You can only move your own units!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        int dx = Math.abs(toRow - fromRow);
        int dy = Math.abs(toCol - fromCol);
        if (Math.max(dx, dy) > unit.getMovementRange()) {
            JOptionPane.showMessageDialog(this, "You can't move a unit here!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        for (Player other : players) {
            if (other != player) {
                Units targetUnit = other.getUnitOnBoard(toRow,toCol);
                if (targetUnit != null) {
                    other.getUnitOnBoard(toRow,toCol).takeDamage(unit.getAttackPower());
                    JOptionPane.showMessageDialog(this,"unit damaged!\nHP left: "+targetUnit.getHitPoint(),null,JOptionPane.INFORMATION_MESSAGE);
                    if (other.getUnitOnBoard(toRow,toCol).getHitPoint() <= 0) {
                        other.setUnitAt(toRow,toCol, null);
                        JOptionPane.showMessageDialog(this,"unit destroyed!",null,JOptionPane.INFORMATION_MESSAGE);
                        buttons[toRow][toCol].setIcon(emptyIcon);
                    }
                    player.onUnitMove(unit);
                    gui.updateFoodLabel(player);
                    return true;
                }

                Structures targetStructure = other.getStructureOnBoard(toRow,toCol);
                if (targetStructure != null) {
                    other.getStructureOnBoard(toRow,toCol).loseHealthPoints(unit.getAttackPower());
                    JOptionPane.showMessageDialog(this,"Structure damaged!\nHP left: "+targetStructure.getHealthPoints(),null,JOptionPane.INFORMATION_MESSAGE);
                    if (other.getStructureOnBoard(toRow,toCol).getHealthPoints() <= 0) {
                        other.setStructureAt(toRow,toCol,null);
                        JOptionPane.showMessageDialog(this,"structure destroyed!",null,JOptionPane.INFORMATION_MESSAGE);
                        buttons[toRow][toCol].setIcon(emptyIcon);
                    }
                    player.onUnitMove(unit);
                    gui.updateFoodLabel(player);
                    return true;
                }
            }
        }

        Units destinationUnit = getUnitAt(player, toRow, toCol);

        if (destinationUnit != null) {

            if (destinationUnit.getClass() == unit.getClass()) {

                removeUnitAt(player, fromRow, fromCol, unit);
                removeUnitAt(player, toRow, toCol, destinationUnit);
                Units upgradedUnit = getUpgradedUnit(unit);
                addUnitAt(player, toRow, toCol, upgradedUnit);


                buttons[toRow][toCol].setIcon(getIconForUnit(upgradedUnit));
                buttons[fromRow][fromCol].setIcon(playerIconFor(player,players));
                player.setOwns(fromRow, fromCol,false);
                player.setOwns(toRow, toCol,true);
                player.setUnitAt(fromRow,fromCol,null);
                player.setUnitAt(toRow, toCol, upgradedUnit);
                player.onUnitMove(upgradedUnit);
                gui.updateFoodLabel(player);
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "You can't move a unit here!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        if(buttons[toRow][toCol].getIcon().equals(forestIcon)){
            player.UpdateFoodSupply();
            gui.updateFoodLabel(player);
        }

        Icon icon = buttons[fromRow][fromCol].getIcon();
        buttons[toRow][toCol].setIcon(icon);
        buttons[fromRow][fromCol].setIcon(playerIconFor(player,players));

        player.getPlaceUnit()[fromRow][fromCol] = false;
        player.getPlaceUnit()[toRow][toCol] = true;
        player.setOwns(fromRow, fromCol,false);
        player.setOwns(toRow, toCol,true);
        player.setUnitAt(fromRow,fromCol,null);
        player.setUnitAt(toRow,toCol,unit);
        player.onUnitMove(unit);
        gui.updateFoodLabel(player);

        removeUnitAt(player, fromRow, fromCol, unit);
        addUnitAt(player, toRow, toCol, unit);

        return true;
    }


    private void removeUnitAt(Player player, int row, int col, Units unit) {
        if (unit instanceof Peasant) player.getPeasants().remove(unit);
        else if (unit instanceof Spearman) player.getSpearmen().remove(unit);
        else if (unit instanceof Swordman) player.getSwordmen().remove(unit);
        else if (unit instanceof Knight) player.getKnights().remove(unit);

        player.getPlaceUnit()[row][col] = false;
    }

    private void addUnitAt(Player player, int row, int col, Units unit) {
        if (unit instanceof Peasant) player.getPeasants().add((Peasant) unit);
        else if (unit instanceof Spearman) player.getSpearmen().add((Spearman) unit);
        else if (unit instanceof Swordman) player.getSwordmen().add((Swordman) unit);
        else if (unit instanceof Knight) player.getKnights().add((Knight) unit);

        player.getPlaceUnit()[row][col] = true;
    }

    private Units getUpgradedUnit(Units unit) {
        if (unit instanceof Peasant) return new Spearman();
        if (unit instanceof Spearman) return new Swordman();
        if (unit instanceof Swordman) return new Knight();
        return unit; // Knight cannot upgrade further
    }

    private ImageIcon getIconForUnit(Units unit) {
        if (unit instanceof Peasant) return peasantIcon;
        if (unit instanceof Spearman) return spearmanIcon;
        if (unit instanceof Swordman) return swordManIcon;
        if (unit instanceof Knight) return knightIcon;
        return null;
    }

    private ImageIcon playerIconFor(Player player,ArrayList<Player> players) {
        ImageIcon[] playerIcons = {player1, player2, player3, player4};
        int playerIndex = players.indexOf(player);
        if (playerIndex >= 0 && playerIndex < playerIcons.length)
            return playerIcons[playerIndex];
        return null;
    }

    public Units getUnitAt(Player player, int row, int col) {
        Icon icon = buttons[row][col].getIcon();

        if (icon.equals(peasantIcon)) return new Peasant();
        if (icon.equals(spearmanIcon)) return new Spearman();
        if (icon.equals(swordManIcon)) return new Swordman();
        if (icon.equals(knightIcon)) return new Knight();

        return null;
    }
}
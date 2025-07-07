package org.example.swing;

import org.example.controller.Game;
import org.example.models.Board;
import org.example.models.player.Player;
import org.example.models.structures.*;
import org.example.models.units.*;
import org.example.models.units.Knight;
import org.example.models.units.Swordman;

import javax.swing.*;
import javax.swing.text.TextAction;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame{
    private JPanel currentPanel;
    int numberOfPlayers;
    ArrayList<Player> players = new ArrayList<Player>();
    Game game;
    Board board;
    Structures selectedStructure;
    Units selectedUnit;
    private JLabel goldLabel;
    private JLabel foodLabel;
    private JLabel playerNameLabel;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private boolean isMovingUnit = false;



    public GUI() {
        setTitle("RealM War");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        foodLabel = new JLabel("food: 0");
        goldLabel = new JLabel("gold: 0");

        currentPanel = new JPanel(new BorderLayout());
        this.add(currentPanel, BorderLayout.CENTER);

        ShowMainMenu();

        setVisible(true);
    }

    public void ShowMainMenu(){
        currentPanel.removeAll();

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.VERTICAL;

        JButton button1 = new JButton("New Game");
        button1.setPreferredSize(new Dimension(200, 30));
        JButton button2 = new JButton("Continue");
        button2.setPreferredSize(new Dimension(200, 30));
        JButton button3 = new JButton("Exit");
        button3.setPreferredSize(new Dimension(200, 30));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(button1,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(button2,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(button3,gbc);

        button1.addActionListener(e -> {
            ShowNewGame();
        });
        button2.addActionListener(e -> {

        });
        button3.addActionListener(e -> {
            System.exit(0);
        });

        currentPanel.add(panel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public void ShowNewGame(){
        currentPanel.removeAll();

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.VERTICAL;


        JButton singleButton = new JButton("Single Player");
        singleButton.setPreferredSize(new Dimension(200, 30));
        JButton doubleButton = new JButton("Double Player");
        doubleButton.setPreferredSize(new Dimension(200, 30));
        JButton threeButton = new JButton("Three Player");
        threeButton.setPreferredSize(new Dimension(200, 30));
        JButton fourthButton = new JButton("Fourth Player");
        fourthButton.setPreferredSize(new Dimension(200, 30));
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 30));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(singleButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(doubleButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(threeButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(fourthButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(backButton, gbc);

        singleButton.addActionListener(actionPerformed -> {
            numberOfPlayers = 1;
            board = new Board(numberOfPlayers,this);
            GameControl();
        });
        doubleButton.addActionListener(actionPerformed -> {
            numberOfPlayers = 2;
            board = new Board(numberOfPlayers,this);
            GameControl();
        });
        threeButton.addActionListener(actionPerformed -> {
            numberOfPlayers = 3;
            board = new Board(numberOfPlayers,this);
            GameControl();
        });
        fourthButton.addActionListener(actionPerformed -> {
            numberOfPlayers = 4;
            board = new Board(numberOfPlayers,this);
            GameControl();
        });
        backButton.addActionListener(actionPerformed -> {
            ShowMainMenu();
        });

        currentPanel.add(panel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public void ShowEndGameWindow(ArrayList<Player> winners) {
        currentPanel.removeAll();
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.VERTICAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel label = new JLabel("Game Over!\n Results:");
        label.setPreferredSize(new Dimension(200, 35));
        mainPanel.add(label,gbc);

        for (int i = 0; i < winners.size(); i++) {
            gbc.gridx = 0;
            gbc.gridy = i+1;
            Player p = winners.get(winners.size() - 1 - i);
            label = new JLabel((i + 1) + ": " + p.getName());
            label.setPreferredSize(new Dimension(200, 30));
            mainPanel.add(label,gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = winners.size()+1;
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(80, 40));
        okButton.setBackground(Color.pink);
        okButton.setForeground(Color.BLACK);
        okButton.addActionListener(e -> System.exit(0));
        mainPanel.add(okButton,gbc);

        currentPanel.add(mainPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }


    public void ShowGameBoardWindow(Player player){
        currentPanel.removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout());

        ImageIcon farmIcon = new ImageIcon("img/Farm.png");
        JButton farm = new JButton(farmIcon);
        farm.setBackground(Color.WHITE);
        farm.addActionListener(actionPerformed -> {
            selectedStructure = new Farm();

        });
        farm.setPreferredSize(new Dimension(50, 50));
        ImageIcon barrackIcon = new ImageIcon("img/Barrack.jpg");
        JButton barrack = new JButton(barrackIcon);
        barrack.setBackground(Color.WHITE);
        barrack.addActionListener(actionPerformed -> {
            selectedStructure = new Barrack();
        });
        barrack.setPreferredSize(new Dimension(50, 50));
        ImageIcon marketIcon = new ImageIcon("img/Market.jpg");
        JButton market = new JButton(marketIcon);
        market.setBackground(Color.WHITE);
        market.addActionListener(actionPerformed -> {
            selectedStructure = new Market();
        });
        market.setPreferredSize(new Dimension(50, 50));
        ImageIcon towerIcon = new ImageIcon("img/Tower.jpg");
        JButton tower = new JButton(towerIcon);
        tower.setBackground(Color.WHITE);
        tower.addActionListener(actionPerformed -> {
            selectedStructure = new Tower();
        });
        tower.setPreferredSize(new Dimension(50, 50));

        ImageIcon peasantIcon = new ImageIcon("img/peasent.jpeg");
        JButton peasant = new JButton(peasantIcon);
        peasant.setBackground(Color.WHITE);
        peasant.addActionListener(actionPerformed -> {
            selectedUnit = new Peasant();
        });
        peasant.setPreferredSize(new Dimension(50, 50));
        ImageIcon spearmanIcon = new ImageIcon("img/spearman.jpeg");
        JButton spearman = new JButton(spearmanIcon);
        spearman.setBackground(Color.WHITE);
        spearman.addActionListener(actionPerformed -> {
            selectedUnit = new Spearman();
        });
        spearman.setPreferredSize(new Dimension(50, 50));
        ImageIcon swordManIcon = new ImageIcon("img/swordMan.jpeg");
        JButton swordMan = new JButton(swordManIcon);
        swordMan.setBackground(Color.WHITE);
        swordMan.addActionListener(actionPerformed -> {
            selectedUnit = new Swordman();
        });
        swordMan.setPreferredSize(new Dimension(50, 50));
        ImageIcon knightIcon = new ImageIcon("img/knight.jpeg");
        JButton knight = new JButton(knightIcon);
        knight.setBackground(Color.WHITE);
        knight.addActionListener(actionPerformed -> {
            selectedUnit = new Knight();
        });
        knight.setPreferredSize(new Dimension(50, 50));


        JPanel topPanel=new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
        playerNameLabel = new JLabel("Player Name: " + player.getName());
        foodLabel = new JLabel("Food: " + player.getFoodSupply());
        goldLabel = new JLabel("Gold: " + player.getGold());
        topPanel.add(playerNameLabel);
        topPanel.add(foodLabel);
        topPanel.add(goldLabel);



        board.setSize(300,300);



        JPanel ButtonPanel = new JPanel(new BorderLayout());

        JPanel nextTurnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5,0));
        JButton nextTurn = new JButton("Next");
        nextTurnPanel.add(nextTurn);

        JPanel StructurePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        StructurePanel.add(farm);
        StructurePanel.add(barrack);
        StructurePanel.add(market);
        StructurePanel.add(tower);

        JPanel UnitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        UnitPanel.add(peasant);
        UnitPanel.add(spearman);
        UnitPanel.add(swordMan);
        UnitPanel.add(knight);

        ButtonPanel.add(StructurePanel, BorderLayout.WEST);
        ButtonPanel.add(UnitPanel, BorderLayout.EAST);
        ButtonPanel.add(nextTurnPanel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(board, BorderLayout.CENTER);
        mainPanel.add(ButtonPanel, BorderLayout.SOUTH);

        for (int i = 0; i < board.buttons.length; i++) {
            for (int j = 0; j < board.buttons[i].length; j++) {
                final int row = i;
                final int col = j;

                for (ActionListener al : board.buttons[i][j].getActionListeners()) {
                    board.buttons[i][j].removeActionListener(al);
                }
                board.buttons[i][j].addActionListener(e -> {
                    if(player.getHasStructure(row,col)){
                        int result = JOptionPane.showConfirmDialog(
                                this,
                                "Do you want to level up your structure to level "+player.getStructureOnBoard(row,col).getLevel()+"?",
                                "Confirmation",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (result == JOptionPane.YES_OPTION) {
                           if(player.getStructureOnBoard(row,col).levelUp(player)){
                               if(player.getStructureOnBoard(row,col)instanceof Farm){
                                   JOptionPane.showMessageDialog(this, "Your structure has been level up! You have level "+player.getStructureOnBoard(row,col).getLevel()+"!\nHP increased by 5\nGiven Food increased by 5!");
                               }
                               else if(player.getStructureOnBoard(row,col)instanceof Market){
                                   JOptionPane.showMessageDialog(this, "Your structure has been level up! You have level "+player.getStructureOnBoard(row,col).getLevel()+"!\nHP increased by 5\nGiven Gold increased by 5!");
                               }
                               else{
                                   JOptionPane.showMessageDialog(this, "Your structure has been level up! You have level "+player.getStructureOnBoard(row,col).getLevel()+"!");
                               }
                           }
                           else{
                               JOptionPane.showMessageDialog(this, "You Dont Have Enough Money or you have reached the max of level up!");
                           }
                        } else if (result == JOptionPane.NO_OPTION) {
                            JOptionPane.showMessageDialog(this, "OK");
                        }


                    }else if (isMovingUnit) {
                        if (board.moveUnit(player, players, selectedRow, selectedCol, row, col , selectedUnit)) {
                            updateGoldLabel(player);
                            lossGoldAndFood(player);
                        }
                        isMovingUnit = false;
                        selectedRow = -1;
                        selectedCol = -1;
                        selectedUnit = null;
                    } else {
                        if (selectedStructure == null && selectedUnit == null) {
                            if (board.isPlayerUnitAt(player, row, col)) {
                                selectedRow = row;
                                selectedCol = col;
                                isMovingUnit = true;
                                selectedUnit = board.getUnitAt(player, row, col);
                            } else {
                                JOptionPane.showMessageDialog(this, "Please choose a structure or unit to place, or select your own unit to move or level up your structure.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            return;
                        }

                        board.update(player, players, row, col, selectedStructure, selectedUnit);
                        updateGoldLabel(player);
                        lossGoldAndFood(player);
                        selectedUnit = null;
                        selectedStructure = null;
                    }
                });

            }
        }

        nextTurn.addActionListener(e -> {
            game.nextTurn();
        });

        currentPanel.add(mainPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    public void lossGoldAndFood(Player player) {
        ImageIcon emptyIcon = new ImageIcon("img/Empty.jpg");
        if(player.getFoodSupply()<0) {
            Units[][] units = player.GetUnitGrid();
            JOptionPane.showMessageDialog(this,"Food is too low!\nHP decreased.","warning",JOptionPane.INFORMATION_MESSAGE);
            if(units != null) {
                for (int i = 0; i < 12; i++) {
                    for (int j = 0; j < 12; j++) {
                        if(units[i][j] != null) {
                            units[i][j].takeDamage(5);
                            if (units[i][j].getHitPoint() < 0) {
                                player.setStructureAt(i, j, null);
                                JOptionPane.showMessageDialog(this, "Structure destroyed because of loss of gold!", "warning", JOptionPane.INFORMATION_MESSAGE);
                                board.buttons[i][j].setIcon(emptyIcon);
                            }
                        }
                    }
                }
            }
        }
        if(player.getGold()<0) {
            Structures[][] structures = player.GetStructureAt();
            JOptionPane.showMessageDialog(this, "Gold is too low!\nHP decreased.", "warning", JOptionPane.INFORMATION_MESSAGE);
            if(structures != null) {
                for (int i = 0; i < 12; i++) {
                    for (int j = 0; j < 12; j++) {
                        if(structures[i][j] != null && !(structures[i][j] instanceof TownHall)) {
                            structures[i][j].loseHealthPoints(5);
                            if (structures[i][j].getHealthPoints() < 0) {
                                player.setStructureAt(i, j, null);
                                JOptionPane.showMessageDialog(this, "Structure destroyed because of loss of gold!", "warning", JOptionPane.INFORMATION_MESSAGE);
                                board.buttons[i][j].setIcon(emptyIcon);
                            }
                        }
                    }
                }
            }
        }
    }
    public void updateFoodLabel(Player player) {
        foodLabel.setText("Food: " + player.getFoodSupply());
    }

    public void updateGoldLabel(Player player) {
        goldLabel.setText("Gold: " + player.getGold());
        foodLabel.setText("Food: " + player.getFoodSupply());
    }


    public void GameControl(){
        players.clear();

        for (int i = 0; i < numberOfPlayers; i++) {
            String name = JOptionPane.showInputDialog(this, "Enter name for Player " + (i + 1) + ":");
            if (name == null || name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a valid name!", "Error", JOptionPane.ERROR_MESSAGE);
                i--;
            } else {
                players.add(new Player(name));
            }
        }

        switch (numberOfPlayers) {
            case 1, 2:
                TownHall townHall1 = new TownHall();
                players.get(0).setStructureAt(10,1,townHall1);
                players.get(0).setIJ(10,1);
                TownHall townHall2 = new TownHall();
                players.get(1).setStructureAt(1,10,townHall2);
                players.get(1).setIJ(1,10);
                break;

            case 3:
                TownHall townHall01 = new TownHall();
                players.get(0).setStructureAt(10,1,townHall01);
                players.get(0).setIJ(10,1);
                TownHall townHall02 = new TownHall();
                players.get(1).setStructureAt(1,10,townHall02);
                players.get(1).setIJ(1,10);
                TownHall townHall03 = new TownHall();
                players.get(2).setStructureAt(10,10,townHall03);
                players.get(2).setIJ(10,10);
                break;

            case 4:
                TownHall townHall001 = new TownHall();
                players.get(0).setStructureAt(10,1,townHall001);
                players.get(0).setIJ(10,1);
                TownHall townHall002 = new TownHall();
                players.get(1).setStructureAt(1,10,townHall002);
                players.get(1).setIJ(1,10);
                TownHall townHall003 = new TownHall();
                players.get(2).setStructureAt(10,10,townHall003);
                players.get(2).setIJ(10,10);
                TownHall townHall004 = new TownHall();
                players.get(3).setStructureAt(1,1,townHall004);
                players.get(3).setIJ(1,1);
                break;

            default:
                break;
        }

        game = new Game(this, players);
    }

    public void updateResourceLabels(Player player) {
        updateGoldLabel(player);
        lossGoldAndFood(player);
        updateFoodLabel(player);
    }


}

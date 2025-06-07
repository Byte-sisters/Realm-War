package org.example.swing;

import org.example.controller.Game;
import org.example.models.Board;
import org.example.models.player.Player;
import org.example.models.structures.*;
import org.example.models.units.*;
import org.example.models.units.Knight;
import org.example.models.units.Swordman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

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

        nextTurn.addActionListener(e -> {
            game.nextTurn();

        });
        for (int i = 0; i < board.buttons.length; i++) {
            for (int j = 0; j < board.buttons[i].length; j++) {
                final int row = i;
                final int col = j;

                for (ActionListener al : board.buttons[i][j].getActionListeners()) {
                    board.buttons[i][j].removeActionListener(al);
                }
                board.buttons[i][j].addActionListener(e -> {
                    if (isMovingUnit) {
                        if (board.moveUnit(player, players, selectedRow, selectedCol, row, col , selectedUnit)) {
                            updateGoldLabel(player);
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
                                JOptionPane.showMessageDialog(this, "Please choose a structure or unit to place, or select your own unit to move.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            return;
                        }

                        board.update(player, players, row, col, selectedStructure, selectedUnit);
                        updateGoldLabel(player);
                        selectedUnit = null;
                        selectedStructure = null;
                    }
                });

            }
        }

        currentPanel.add(mainPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
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

        game = new Game(this, players);
    }


}

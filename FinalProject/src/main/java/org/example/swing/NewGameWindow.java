package org.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameWindow extends JFrame implements ActionListener {
    public int numberOfPlayers;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private GridBagConstraints gbc;

    public NewGameWindow() {
        JPanel panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.VERTICAL;


        button1 = new JButton("Single Player");
        button1.setPreferredSize(new Dimension(200, 30));
        button2 = new JButton("Double Player");
        button2.setPreferredSize(new Dimension(200, 30));
        button3 = new JButton("Three Player");
        button3.setPreferredSize(new Dimension(200, 30));
        button4 = new JButton("Fourth Player");
        button4.setPreferredSize(new Dimension(200, 30));
        button5 = new JButton("Back");
        button5.setPreferredSize(new Dimension(200, 30));

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(button1, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(button2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(button3, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(button4, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(button5, gbc);

        this.add(panel, BorderLayout.CENTER);
        this.setTitle("RealM War");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "Single Player":
                numberOfPlayers=1;
                break;
                case "Double Player":
                    numberOfPlayers=2;
                    break;
                    case "Three Player":
                        numberOfPlayers=3;
                        break;
                        case "Fourth Player":
                            numberOfPlayers=4;
                            break;
                            case "Back":
                                this.setVisible(false);
                                new MainMenu();
                                break;




            default:
        }

    }
}

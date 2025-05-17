package org.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private GridBagConstraints gbc;
    public MainMenu() {
        JPanel panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.VERTICAL;


        button1 = new JButton("New Game");
        button1.setPreferredSize(new Dimension(200, 30));
        button2 = new JButton("Continue");
        button2.setPreferredSize(new Dimension(200, 30));
        button3 = new JButton("Exit");
        button3.setPreferredSize(new Dimension(200, 30));


        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(button1,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(button2,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(button3,gbc);

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
            case "New Game":
                NewGameWindow window = new NewGameWindow();
                setVisible(false);
                break;

                case "Continue":
                    break;

                    case "Exit":
                        System.exit(0);
                        break;

                        default:
        }

    }
    public static void main(String[] args) {
        new MainMenu();
    }
}

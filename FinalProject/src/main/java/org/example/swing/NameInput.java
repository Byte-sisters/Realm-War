package org.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameInput extends JFrame implements ActionListener {
    public String playerName;
    TextField name;
    public NameInput() {
        name = new TextField();
        name.setPreferredSize(new Dimension(200, 20));
        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("Name: ");
        JButton button = new JButton("submit");
        panel.add(label);
        panel.add(name);
        button.addActionListener(this);

        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.add(button, BorderLayout.SOUTH);

        setTitle("RealM War");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        playerName=name.getText();
        setVisible(false);
    }

    public String getPlayerName() {
        return playerName;
    }
}

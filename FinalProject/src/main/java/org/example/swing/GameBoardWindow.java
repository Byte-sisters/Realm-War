package org.example.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardWindow extends JFrame implements ActionListener {
    public GameBoardWindow() {
        ImageIcon icon1 = new ImageIcon("10751558.png");
        ImageIcon icon2 = new ImageIcon("10751558.png");
        ImageIcon icon3 = new ImageIcon("10751558.png");
        ImageIcon icon4 = new ImageIcon("10751558.png");
        ImageIcon icon5 = new ImageIcon("10751558.png");

        ImageIcon icon6 = new ImageIcon("images.png");
        ImageIcon icon7 = new ImageIcon("images.png");
        ImageIcon icon8 = new ImageIcon("images.png");
        ImageIcon icon9 = new ImageIcon("images.png");


        JPanel panel1=new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));
        JLabel label1 = new JLabel("Food");
        JLabel label2 = new JLabel("Gold");
        panel1.add(label1);
        panel1.add(label2);


        JPanel panel2=new JPanel();
        panel2.setSize(300,300);


        JPanel panel3=new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER,50,0));


        JPanel panel31=new JPanel();
        panel31.setLayout(new FlowLayout());
        JLabel label11 = new JLabel();
        label11.setIcon(icon6);
        panel31.add(label11);
        JLabel label12 = new JLabel();
        label12.setIcon(icon7);
        panel31.add(label12);
        JLabel label13 = new JLabel();
        label13.setIcon(icon8);
        panel31.add(label13);
        JLabel label14 = new JLabel();
        label14.setIcon(icon9);
        panel31.add(label14);
        panel3.add(panel31);

        JPanel panel32=new JPanel();
        panel32.setLayout(new FlowLayout());
        JLabel label01 = new JLabel();
        label01.setIcon(icon1);
        panel32.add(label01);
        JLabel label02 = new JLabel();
        label02.setIcon(icon2);
        panel32.add(label02);
        JLabel label03 = new JLabel();
        label03.setIcon(icon3);
        panel32.add(label03);
        JLabel label04 = new JLabel();
        label04.setIcon(icon4);
        panel32.add(label04);
        JLabel label05 = new JLabel();
        label05.setIcon(icon5);
        panel32.add(label05);
        panel3.add(panel32);






        this.setTitle("RealM War");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {

    }
   

}

package com.cardcreator.application.ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class NavigationBar extends JPanel {
    public NavigationBar(ActionListener listener) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(new Color(230, 230, 230));
        TitledBorder border = BorderFactory.createTitledBorder("Navigation Bar");
        border.setTitleFont(new Font("Arial", Font.BOLD, 14));
        border.setTitleColor(Color.BLACK);
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        setBorder(border);


        String[] buttons = {"Main Menu", "Add Card", "Delete Card", "Edit Card"};
        for (String i : buttons) {
            JButton btn = new JButton(i);
            btn.setPreferredSize(new Dimension(120, 30));
            btn.addActionListener(listener);
            add(btn);
        }

    }
}

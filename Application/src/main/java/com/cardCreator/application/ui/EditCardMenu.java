package com.cardcreator.application.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCardMenu extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel parentPanel;

    public EditCardMenu(CardLayout cardLayout, JPanel parentPanel){
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setLayout(new BorderLayout());
        add(new NavigationBar(this),BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.add(new JLabel("Edit Card Menu"));
        add(content,BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command){
            case "Main Menu":
                cardLayout.show(parentPanel,"MainMenu");
                break;

            case "Add Card":
                cardLayout.show(parentPanel,"AddCardMenu");
                break;
            case "Delete Card":
                cardLayout.show(parentPanel,"DeleteCardMenu");
                break;

            case "Edit Card":
                cardLayout.show(parentPanel,"EditCardMenu");
                break;
        }
    }
}

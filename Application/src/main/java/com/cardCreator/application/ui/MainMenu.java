package com.cardcreator.application.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenu{

    private JButton addCardBtn,listCardsBtn,editCardBtn,removeCardBtn,uploadFileBtn;
    private JFileChooser fileChooser;
    private JFrame frame;
    public MainMenu(){
        frame = new JFrame("Card Editor");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,900);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        //create screen
        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new BoxLayout(mainMenu,BoxLayout.Y_AXIS));
        mainMenu.add(Box.createVerticalStrut(50));
        mainMenu.add(new JLabel("Main Menu", SwingConstants.CENTER));


        addCardBtn = new JButton("Add Card");
        editCardBtn = new JButton("Edit Card");
        removeCardBtn = new JButton("Delete Card");
        listCardsBtn = new JButton("List Cards");
        uploadFileBtn = new JButton("Upload File");

        //straight outa chat. //TODO fix my own version lol
        Dimension btnSize = new Dimension(200, 40);
        for (JButton b : new JButton[]{addCardBtn, editCardBtn, removeCardBtn, listCardsBtn, uploadFileBtn}) {
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setMaximumSize(btnSize);
            mainMenu.add(Box.createVerticalStrut(15));
            mainMenu.add(b);
        }

        fileChooser = new JFileChooser();

        AddCardMenu addCardMenu = new AddCardMenu(cardLayout, mainPanel);
        DeleteCardMenu deleteCardMenu = new DeleteCardMenu(cardLayout, mainPanel);
        EditCardMenu editCardMenu = new EditCardMenu(cardLayout, mainPanel);

        //add all panels to CardLayout
        mainPanel.add(mainMenu, "MainMenu");
        mainPanel.add(addCardMenu, "AddCardMenu");
        mainPanel.add(deleteCardMenu, "DeleteCardMenu");
        mainPanel.add(editCardMenu, "EditCardMenu");

        //switch screens
        addCardBtn.addActionListener((ActionEvent e) -> cardLayout.show(mainPanel, "AddCardMenu"));
        editCardBtn.addActionListener((ActionEvent e) -> cardLayout.show(mainPanel, "EditCardMenu"));
        removeCardBtn.addActionListener((ActionEvent e) -> cardLayout.show(mainPanel, "DeleteCardMenu"));

        //upload file button
        uploadFileBtn.addActionListener(e -> {
            int returnVal = fileChooser.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("Selected file: " + fileChooser.getSelectedFile().getName());
            }
        });


        mainPanel.add(mainMenu,"MainMenu");
        mainPanel.add(addCardMenu, "AddCardMenu");
        mainPanel.add(deleteCardMenu,"DeleteCardMenu");
        mainPanel.add(editCardMenu, "EditCardMenu");


        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }


}
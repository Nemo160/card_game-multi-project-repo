package com.cardCreator.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    public JFrame createJFrame(String title, int width, int height){
        JFrame f = new JFrame(title);

        //Create window and set sizes
        f.setSize(width,height);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        //create icon for window
        ImageIcon icon = new ImageIcon("file");
        f.setIconImage(icon.getImage());

        f.setResizable(false);
        return f;
    }
    public JPanel createPanel(JFrame container, Integer width, Integer height){
        JPanel panel = new JPanel();
        if(width !=null && height != null){
            panel.setPreferredSize(new Dimension(width,height));
        }

            panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        container.add(panel);
        return panel;
    }

    public JLabel addLabel(Container container, String title) {
        JLabel label = new JLabel();
        label.setText(title);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(label);
        return label;
    }

    public JButton addButton(Container container, String name){
        JButton button = new JButton(name);
        button.addActionListener(this);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusable(false);
        button.setMaximumSize(new Dimension(150,40));
        container.add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

package com.cardcreator.application.ui;
import com.cardcreator.application.logic.CardParser;
import com.cardcreator.application.model.Ability;
import com.cardcreator.application.model.BiomeCard;
import com.cardcreator.application.model.CreatureCard;
import com.cardcreator.application.model.Resource;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddCardMenu extends JPanel implements ActionListener {
    private CardLayout cardLayout;
    private JPanel parentPanel;
    private JPanel formPanel;
    private JPanel biomePanel,creaturePanel;

    //navbar
    private JRadioButton biomeBtn, creatureBtn;

    //creature fields
    private List<JComboBox> creature_resourceField = new ArrayList<>();
    private List<JComboBox> creature_resourceAmountField = new ArrayList<>();
    private List<JTextField> creature_abilityNameField = new ArrayList<>();
    private List<JTextField> creature_abilityEffectField = new ArrayList<>();
    private List<JComboBox> creature_abilityTypeField = new ArrayList<>();
    private List<JTextField> creature_tagNameField = new ArrayList<>();

    private JComboBox<String> creature_input_cardLevelField, creature_evasionType;
    private JButton creature_addResourceBtn, creature_addAbilityBtn, creature_createCardBtn, creature_addTagBtn;
    private JTextField creature_cardIdField, creature_cardNameField, creature_scientificNameField, creature_evasionName;
    private JTextArea creature_flavorTextField, creature_abilityDescField;
    private JPanel creature_costPanel, creature_abilityPanel, creature_tagsPanel;

    //biome fields
    private JTextField biome_nameField, biome_idField;
    private JTextField biome_abilityNameField;
    private JComboBox<String> biome_activeTypeField;
    private JTextArea biome_flavorTextField,biome_abilityDescField;
    private JButton biome_addResourceBtn, biome_createCardBtn;
    private JPanel biome_resourcePanel;
    private ArrayList<JComboBox> biome_resourceField = new ArrayList<>();
    private ArrayList<JComboBox> biome_resourceAmountField = new ArrayList<>();

    //constants
    private final String[] activeType = {"activated", "passive"};
    private final String[] trueOrFales = {"true","false"};
    private final String[] level = {"0","1","2","3","4","5"};
    private final String[] biomeType = {"Grassland","Forest", "Tundra","Desert","Urban","Freshwater","Marine"};
    private final String tagExample = (
            "Examples: predator, prey, " +
                    "carnivore, herbivore, omnivore, " +
                    "insectivore, mammal, fish, amphibian, reptile, " +
                    "bird, invertebrate, domesticated, wild");

    public AddCardMenu(CardLayout cardLayout, JPanel parentPanel){
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setLayout(new BorderLayout());
        setBackground(new Color(245,245,245));
        setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 13));
        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 13));
        UIManager.put("TitledBorder.font", new Font("Segoe UI", Font.BOLD, 14));

        add(new NavigationBar(this),BorderLayout.NORTH);

        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel,BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(245,245,245));

        JPanel typeSelector = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typeSelector.setAlignmentX(Component.LEFT_ALIGNMENT);
        typeSelector.setBackground(new Color(240,240,240));

        biomeBtn = new JRadioButton("Biome");
        creatureBtn = new JRadioButton("Creature");
        ButtonGroup group1 = new ButtonGroup();
        group1.add(biomeBtn);
        group1.add(creatureBtn);
        biomeBtn.addActionListener(this);
        creatureBtn.addActionListener(this);

        typeSelector.add(new JLabel("Select Card Type: "));
        typeSelector.add(biomeBtn);
        typeSelector.add(creatureBtn);
        typeSelector.setAlignmentX(CENTER_ALIGNMENT);
        formPanel.add(typeSelector);
        formPanel.add(Box.createRigidArea(new Dimension(0,10)));

        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
    }

    //method to style a pane with a consistent title border
    private void stylePanel(JPanel panel, String title) {
        TitledBorder border = BorderFactory.createTitledBorder(title);
        border.setTitleFont(new Font("Segoe UI", Font.BOLD, 14));
        border.setTitleColor(new Color(60, 60, 60));
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        panel.setBorder(BorderFactory.createCompoundBorder(
                border,
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        panel.setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Main Menu" -> cardLayout.show(parentPanel, "MainMenu");
            case "Add Card" -> cardLayout.show(parentPanel, "AddCardMenu");
            case "Delete Card" -> cardLayout.show(parentPanel, "DeleteCardMenu");
            case "Edit Card" -> cardLayout.show(parentPanel, "EditCardMenu");
        }

        //switch creature and biome panels
        if(e.getSource() == biomeBtn) {
            if(biomePanel == null){
                biomePanel = optionBiome();
                formPanel.add(biomePanel);
            }
            if(creaturePanel != null) creaturePanel.setVisible(false);
            biomePanel.setVisible(true);
        } else if(e.getSource() == creatureBtn){
            if(creaturePanel == null){
                creaturePanel = optionCreature();
                formPanel.add(creaturePanel);
            }
            if(biomePanel != null) biomePanel.setVisible(false);
            creaturePanel.setVisible(true);
        }

        // creature button events
        if (e.getSource() == creature_addResourceBtn) creature_addResource();
        if (e.getSource() == creature_addAbilityBtn) creature_addAbility();
        if (e.getSource() == creature_addTagBtn) addTag();
        if (e.getSource() == creature_createCardBtn){ creature_getFields();
            System.out.println("clicked button Creats");
        }

        //biome button event
        if (e.getSource() == biome_addResourceBtn) biome_addResource();
        if (e.getSource() == biome_createCardBtn){
            System.out.println("clicked button");
            biome_getFields();
        }

        //repaints after btn changes
        formPanel.revalidate();
        formPanel.repaint();
    }
    private JPanel optionBiome(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245,245,245));

        //name and id panel
        JPanel namesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namesPanel.add(new JLabel("ID:"));
        biome_idField = new JTextField(5);
        namesPanel.add(biome_idField);
        namesPanel.add(new JLabel("Name"));
        biome_nameField = new JTextField(10);
        namesPanel.add(biome_nameField);
        stylePanel(namesPanel, "Id and Name");

        //resource panel
        biome_resourcePanel = new JPanel();
        biome_resourcePanel.setLayout(new BoxLayout(biome_resourcePanel, BoxLayout.Y_AXIS));
        biome_addResourceBtn = new JButton("Add Resource");
        biome_addResourceBtn.addActionListener(this);
        biome_resourcePanel.add(biome_addResourceBtn);
        stylePanel(biome_resourcePanel, "Resources");

        //ability panel


        JPanel abilityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        abilityPanel.add(new JLabel("Ability Name"));
        biome_abilityNameField = new JTextField(10);
        abilityPanel.add(biome_abilityNameField);

        abilityPanel.add(new JLabel("Type"));
        biome_activeTypeField = new JComboBox<>(activeType);
        abilityPanel.add(biome_activeTypeField);
        stylePanel(abilityPanel, "Ability");


        //ability description
        JPanel abilityDescPanel = new JPanel();
        biome_abilityDescField = new JTextArea(5,20); //ability description FIELD
        biome_abilityDescField.setLineWrap(true);
        biome_abilityDescField.setWrapStyleWord(true);
        JScrollPane abilityScroll = new JScrollPane(biome_abilityDescField);
        abilityScroll.setPreferredSize(new Dimension(300,100)); // keeps it stable
        abilityDescPanel.add(abilityScroll);
        stylePanel(abilityDescPanel,"Ability Description");


        JPanel flavorPanel = new JPanel();
        biome_flavorTextField = new JTextArea(5,20); //Flavor FIELD
        biome_flavorTextField.setLineWrap(true);
        biome_flavorTextField.setWrapStyleWord(true);
        JScrollPane flavorScrollPane = new JScrollPane(biome_flavorTextField);
        flavorScrollPane.setPreferredSize(new Dimension(200,100)); // keeps it stable
        flavorPanel.add(flavorScrollPane);
        stylePanel(flavorPanel, "Flavor Text");

        JPanel lastRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        biome_createCardBtn = new JButton("Create Card");
        biome_createCardBtn.addActionListener(this);
        lastRow.add(biome_createCardBtn);

        //combine panels
        panel.add(namesPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(biome_resourcePanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(abilityPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(abilityDescPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(flavorPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lastRow);

        biome_addResource();
        return panel;
    }

    private JPanel optionCreature(){

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 245));

        //name and id
        JPanel namesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namesPanel.add(new JLabel("id:"));
        creature_cardIdField = new JTextField(5);
        namesPanel.add(creature_cardIdField);
        namesPanel.add(new JLabel("Card Name:"));
        creature_cardNameField = new JTextField(10);
        namesPanel.add(creature_cardNameField);
        namesPanel.add(new JLabel("Scientific Name:"));
        creature_scientificNameField = new JTextField(10);
        namesPanel.add(creature_scientificNameField);
        namesPanel.add(new JLabel("Card LVL"));
        creature_input_cardLevelField = new JComboBox<>(level);
        namesPanel.add(creature_input_cardLevelField);
        stylePanel(namesPanel,"Id and Info");

        //cost panel
        creature_costPanel = new JPanel();
        creature_costPanel.setLayout(new BoxLayout(creature_costPanel,BoxLayout.Y_AXIS));
        creature_addResourceBtn = new JButton("Add Resource");
        creature_addResourceBtn.addActionListener(this);
        creature_costPanel.add(creature_addResourceBtn);
        stylePanel(creature_costPanel, "Cost");

        //evasionPanel
        JPanel evasionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        evasionPanel.add(new JLabel("Evasion Name"));
        creature_evasionName = new JTextField(10);
        evasionPanel.add(creature_evasionName);
        evasionPanel.add(new JLabel("Tap:"));
        creature_evasionType = new JComboBox<>(trueOrFales);
        evasionPanel.add(creature_evasionType);
        stylePanel(evasionPanel,"Evasion");

        //ability panel
        creature_abilityPanel = new JPanel();
        creature_abilityPanel.setLayout(new BoxLayout(creature_abilityPanel,BoxLayout.Y_AXIS));
        creature_addAbilityBtn = new JButton("Add ability");
        creature_addAbilityBtn.addActionListener(this);
        creature_abilityPanel.add(creature_addAbilityBtn);
        stylePanel(creature_abilityPanel, "Abilities");


        JPanel flavorPanel = new JPanel();
        creature_flavorTextField = new JTextArea(5, 20);
        JScrollPane flavorScroll = new JScrollPane(creature_flavorTextField);
        flavorScroll.setPreferredSize(new Dimension(300, 100));
        flavorPanel.add(flavorScroll);
        stylePanel(flavorPanel, "Flavor Text");


        //tags panel
        creature_tagsPanel = new JPanel();
        creature_tagsPanel.setLayout(new BoxLayout(creature_tagsPanel,BoxLayout.Y_AXIS));
        creature_addTagBtn = new JButton("Add Tag");
        creature_addTagBtn.addActionListener(this);
        creature_tagsPanel.add(creature_addTagBtn);
       stylePanel(creature_tagsPanel, "Tags");


        //upload row/
        JPanel createCardRow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        creature_createCardBtn = new JButton("Create Card");
        creature_createCardBtn.addActionListener(this);
        createCardRow.add(creature_createCardBtn);


        // Add sections
        panel.add(namesPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(creature_costPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(evasionPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(creature_abilityPanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(creature_tagsPanel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(flavorPanel);
        panel.add(Box.createVerticalStrut(10));

        panel.add(createCardRow);


        creature_addResource();
        creature_addAbility();
        addTag();
        return panel;
    }

    //different functions
    private void creature_addAbility(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Name:"));

        JTextField add_abilityNameField = new JTextField(10);
        JComboBox<String> add_abilityType = new JComboBox<>(activeType);
        JTextField add_abilityEffectField = new JTextField(10);
        JButton removeBtn = new JButton("Remove");

        panel.add(add_abilityNameField);
        panel.add(new JLabel("Tappable:"));
        panel.add(add_abilityType);
        panel.add(new JLabel("Effect:"));

        panel.add(add_abilityEffectField);
        panel.add(removeBtn);
        creature_abilityNameField.add(add_abilityNameField);
        creature_abilityTypeField.add(add_abilityType);
        creature_abilityEffectField.add(add_abilityEffectField);


        //adds remove button
        removeBtn.addActionListener(e -> {
            creature_abilityPanel.remove(panel);
            creature_abilityPanel.revalidate();
            creature_abilityPanel.repaint();
        });

        creature_abilityPanel.add(panel);
        creature_abilityPanel.revalidate();
        creature_abilityPanel.repaint();


    }
    private void creature_addResource(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT
        ));
        panel.add(new JLabel("Biome"));
        JComboBox<String> biomeTypeField = new JComboBox<>(biomeType);
        panel.add(biomeTypeField);

        panel.add(new JLabel("Amount"));
        JComboBox<String> amountField = new JComboBox<>(level);
        panel.add(amountField);

        //save to list
        creature_resourceField.add(biomeTypeField);
        creature_resourceAmountField.add(amountField);
        JButton removeBtn = new JButton("Remove");
        panel.add(removeBtn);

        removeBtn.addActionListener(e -> {
            //remove UI panel
            creature_costPanel.remove(panel);
            creature_costPanel.revalidate();
            creature_costPanel.repaint();

            //remove references from lists
            creature_resourceField.remove(biomeTypeField);
            creature_resourceAmountField.remove(amountField);
        });

        creature_costPanel.add(panel);
        creature_costPanel.revalidate();
        creature_costPanel.repaint();
    }
    private void biome_addResource(){
        JPanel panel = new JPanel();
        panel.add(new JLabel("Provides"));
        JComboBox<String> biomeTypeField = new JComboBox<>(biomeType);
        panel.add(biomeTypeField);

        panel.add(new JLabel("Amount"));
        JComboBox<String> amountField = new JComboBox<>(level);
        panel.add(amountField);

        biome_resourceField.add(biomeTypeField);
        biome_resourceAmountField.add(amountField);

        JButton removeBtn = new JButton("Remove");
        panel.add(removeBtn);
        removeBtn.addActionListener(e -> {
            biome_resourcePanel.remove(panel);
            biome_resourcePanel.revalidate();
            biome_resourcePanel.repaint();

            //remove references from lists
            biome_resourceField.remove(biomeTypeField);
            biome_resourceAmountField.remove(amountField);
        });
        biome_resourcePanel.add(panel);
        biome_resourcePanel.revalidate();
        biome_resourcePanel.repaint();

    }
    private void addTag(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT
        ));
        panel.setToolTipText(tagExample);

        JLabel label_tag = new JLabel("tag:");
        JTextField text_tagName = new JTextField(5);

        panel.add(label_tag);
        panel.add(text_tagName);

        //save to list
        creature_tagNameField.add(text_tagName);
        JButton removeBtn = new JButton("Remove");
        panel.add(removeBtn);

        removeBtn.addActionListener(e -> {
            //remove UI panel
            creature_tagsPanel.remove(panel);
            creature_tagsPanel.revalidate();
            creature_tagsPanel.repaint();

            //remove references from lists
            creature_tagNameField.remove(text_tagName);
        });

        creature_tagsPanel.add(panel);
        creature_tagsPanel.revalidate();
        creature_tagsPanel.repaint();
    }
    public void creature_getFields(){

        //create list for resources from GUI.
        ArrayList<Resource> resourcesList = new ArrayList<>();
        ArrayList<Ability> abilitiesList = new ArrayList<>();
        ArrayList<String> tagsList = new ArrayList<>();
        for (int i = 0; i < creature_resourceField.size(); i++) {
            Resource r = new Resource();
            r.setAmount((String) creature_resourceField.get(i).getSelectedItem());
            r.setResource((String) creature_resourceAmountField.get(i).getSelectedItem());
            resourcesList.add(r);

        }
        //get inputs in ability panel
        for (int i = 0; i < creature_abilityNameField.size(); i++) {
            Ability ab = new Ability();
            ab.setName(creature_abilityNameField.get(i).getText().trim());
            ab.setType((String) creature_abilityTypeField.get(i).getSelectedItem());
            ab.setEffect(creature_abilityEffectField.get(i).getText());
            abilitiesList.add(ab);
        }

        for (JTextField i: creature_tagNameField) {
            tagsList.add(i.getText().trim());
        }
        //create creature card with inputs and pass the card to the card parser.
        CreatureCard card = creatureCardInputs(resourcesList,abilitiesList,tagsList);

        CardParser p = new CardParser();
        p.parseCreatureJSON(card);
        System.out.println(card);
    }
    public void biome_getFields() {
        ArrayList<Resource> resourcesList = new ArrayList<>();
        ArrayList<Ability> abilitiesList = new ArrayList<>();
        Ability ab = new Ability();
        ab.setName(biome_abilityNameField.getText());
        ab.setType( (String) biome_activeTypeField.getSelectedItem());
        ab.setEffect(biome_abilityDescField.getText());
        abilitiesList.add(ab);

        for (int i = 0; i < biome_resourceField.size(); i++) {
            Resource r = new Resource();
            r.setAmount((String) biome_resourceField.get(i).getSelectedItem());
            r.setResource((String) biome_resourceAmountField.get(i).getSelectedItem());
            resourcesList.add(r);
        }

        BiomeCard card = biomeCardInputs(resourcesList,abilitiesList);
        CardParser p = new CardParser();
        p.parseBiomeJSON(card);
        System.out.println(card);

    }


    private BiomeCard biomeCardInputs(ArrayList<Resource> res, ArrayList<Ability> abi){
        BiomeCard c = new BiomeCard();
        c.setId(biome_idField.getText());
        c.setName(biome_nameField.getText());
        c.setFlavorText(biome_flavorTextField.getText());
        c.setResources(res);
        c.setAbilities(abi);

        return c;
    }


    private CreatureCard creatureCardInputs(ArrayList<Resource> res, ArrayList<Ability> abi, ArrayList<String> tag){
        CreatureCard c = new CreatureCard();
        c.setId(creature_cardIdField.getText().trim()); //;
        c.setName(creature_cardNameField.getText().trim());
        c.setScientificName(creature_scientificNameField.getText().trim());
        c.setCardLVL( (String) creature_input_cardLevelField.getSelectedItem());
        c.setFlavorText(creature_flavorTextField.getText().trim());
        c.setEvasionName(creature_evasionName.getText().trim());
        c.setEvasionType((String) creature_evasionType.getSelectedItem());
        c.setTags(tag);
        c.setResources(res);
        c.setAbilities(abi);
        /*TODO remove abilityDesc from creature option and
            make evasionName and evasionType be an array of strings like tags. Group them basically*/

        return c;
    }
}




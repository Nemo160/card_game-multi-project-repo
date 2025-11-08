package com.cardCreator.application;

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
    private JButton backBtn;
    //Get fields
    //CREATURE OPTION
    private List<JComboBox> creature_resourceField = new ArrayList<>();
    private List<JComboBox> creature_resourceAmountField = new ArrayList<>();

    private List<JTextField> creature_abilityNameField = new ArrayList<>();
    private List<JTextField> creature_abilityEffectField = new ArrayList<>();
    private List<JComboBox> creature_abilityTappableField = new ArrayList<>();

    private List<JTextField> creature_tagNameField = new ArrayList<>();
    private JComboBox<String> creature_input_cardLevelField, creature_evasionType;
    private JButton creature_addResourceBtn, creature_addAbilityBtn, creature_createCardBtn, creature_addTagBtn;
    private JTextField creature_cardIdField, creature_cardNameField, creature_scientificNameField, creature_evasionName;
    private JTextArea flavorTextField,abilityDesc_Text;
    private JRadioButton biomeBtn, creatureBtn;
    private JPanel formPanel;
    private JPanel biomePanel,creaturePanel;
    private JPanel creature_costPanel, creature_abilityPanel, creature_tagsPanel;
    private final String[] activeType = {"activated", "passive"};
    private final String[] trueOrFales = {"true","false"};
    private final String[] level = {"0","1","2","3","4","5"};
    private final String[] biomeType = {"Grassland","Forest", "Tundra","Desert","Urban","Freshwater","Marine"};
    private final String tagExample = (
            "Examples: predator, prey, carnivore, herbivore, omnivore, insectivore, mammal, fish, amphibian, reptile, bird, invertebrate, domesticated, wild");
//    private final String[] tagExample = {
//            "predator","prey","carnivore", "herbivore","omnivore","insectivore",
//            "mammal", "fish","amphibian","reptile", "bird","invertebrate",
//            "domesticated", "wild"
//    };

    //BIOME OPTION
    private JTextField biome_nameField, biome_idField;

    //TODO WORK ON optionBiome(). The same as creature but now for biome.
    //TODO Create different menus and send an instance of each and add them to parent_navigationPanel


    public AddCardMenu(CardLayout cardLayout, JPanel parentPanel){
        this.cardLayout = cardLayout;
        this.parentPanel = parentPanel;

        setLayout(new BorderLayout());
        add(new NavigationBar(this),BorderLayout.NORTH);

        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel,BoxLayout.Y_AXIS));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.setMaximumSize(new Dimension(180,50));
        row1.setBackground(Color.RED);

        //Radio buttons
        biomeBtn = new JRadioButton("Biome");
        creatureBtn = new JRadioButton("Creature");
        ButtonGroup group1 = new ButtonGroup();
        group1.add(biomeBtn);
        group1.add(creatureBtn);
        biomeBtn.addActionListener(this);
        creatureBtn.addActionListener(this);

        JPanel radioButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioButtonPanel.add(biomeBtn);
        radioButtonPanel.add(creatureBtn);
        radioButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        row1.add(radioButtonPanel);
        formPanel.add(row1);
        formPanel.add(Box.createRigidArea(new Dimension(0,10)));



        add(formPanel,BorderLayout.CENTER);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command){
            case "Main Menu":
                cardLayout.show(parentPanel,"MainMenu");
                break;

            case "Add Card":
                cardLayout.show(parentPanel,"addCardMenu");
                break;
            case "Delete Card":
                cardLayout.show(parentPanel,"DeleteCardMenu");
                break;

            case "Edit Card":
                cardLayout.show(parentPanel,"EditCardMenu");
                break;
        }

        if(e.getSource() == creature_addResourceBtn){
            creature_addResource();
        }
        if(e.getSource() == creature_addAbilityBtn){
            creature_addAbility();
        }
        if(e.getSource() == creature_addTagBtn){
            addTag();
        }
        if (e.getSource() == creature_createCardBtn) {
            creature_getFields();
        }

        //more if-statements for buttons

        if(e.getSource() == biomeBtn) {
            if(biomePanel == null){
                biomePanel = optionBiome();
                formPanel.add(biomePanel);
            }
            if(creaturePanel != null) {
                creaturePanel.setVisible(false);
                biomePanel.setVisible(true);
            }
        }
        else if(e.getSource() == creatureBtn){
            if(creaturePanel == null){
                creaturePanel = optionCreature();
                formPanel.add(creaturePanel);
            }
            if(biomePanel != null){
                biomePanel.setVisible(false);
                creaturePanel.setVisible(true);
            }
        }

        //repaints after btn changes
        formPanel.revalidate();
        formPanel.repaint();
    }
    private JPanel optionBiome(){

        /*TODO CREATE FIELDS FOR BIOME CARD SCHEME
        * id, name, type, imageUrl, biomeCategory, provides( Resource: ), abilities(name, type, cost), flavorText
        * */
        JPanel biomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //name and id panel
        JPanel biome_namesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        biome_namesPanel.add(new JLabel("id"));
        biome_idField = new JTextField(5);
        biome_namesPanel.add(biome_idField);

        biome_namesPanel.add(new JLabel("Card Name"));
        biome_nameField = new JTextField(10);
        biome_namesPanel.add(biome_nameField);

        //resource panel
        JPanel biome_resourcesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        biome_resourcesPanel.add(new JLabel("Resources")); //temphere
        //biome_addResourceBtn = new JButton("Add Resource");
       // biome_addResourceBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
       // biome_addResourceBtn.addActionListener(this);

        JPanel biome_abilityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField biome_abilityNameField = new JTextField(10);
        biome_abilityPanel.add(new JLabel("Type"));
        JComboBox<String> biome_activeType = new JComboBox<>(activeType);
        biome_abilityPanel.add(biome_activeType);

        biome_abilityPanel.add(new JLabel("Effect"));

        biome_abilityPanel.add(new JLabel("Ability Name"));
        JComboBox<String> biome_abilityType = new JComboBox<>(trueOrFales);
        JTextField biome_abilityDesc = new JTextField(5);
        biome_abilityPanel.add(biome_abilityType);
        biome_abilityPanel.add(biome_abilityDesc);

        //lista för JPanel och Borders
        String[] titles_border = {
                "Name and Type","Resource", "Ability", "Effect"};
        JPanel[] panels = { /*JPanels in here*/};

        for (String s : titles_border) {
            TitledBorder border = BorderFactory.createTitledBorder(s);
            border.setTitleFont(new Font("Arial", Font.BOLD, 14));
            border.setTitleColor(Color.BLACK);
            border.setTitleJustification(TitledBorder.CENTER);
            border.setTitlePosition(TitledBorder.TOP);
            //panels[i].setBorder(border);
        }
        //debugging colors:
        biomePanel.setBackground(Color.DARK_GRAY);
        biome_namesPanel.setBackground(Color.YELLOW);

        biomePanel.add(biome_namesPanel);
        biomePanel.add(biome_resourcesPanel);
        biomePanel.add(biome_abilityPanel);

        return biomePanel;
    }

    private JPanel optionCreature(){
        JPanel creaturePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //name and id panel
        JPanel creature_namesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        creature_namesPanel.add(new JLabel("id:"));
        creature_cardIdField = new JTextField(5);
        creature_namesPanel.add(creature_cardIdField);

        creature_namesPanel.add(new JLabel("Card Name:"));
        creature_cardNameField = new JTextField(10);
        creature_namesPanel.add(creature_cardNameField);

        creature_namesPanel.add(new JLabel("Scientific Name:"));
        creature_scientificNameField = new JTextField(10);
        creature_namesPanel.add(creature_scientificNameField);

        creature_namesPanel.add(new JLabel("Card LVL"));
        creature_input_cardLevelField = new JComboBox<>(level);
        creature_namesPanel.add(creature_input_cardLevelField);


        //Resource panel
        creature_costPanel = new JPanel();
        creature_costPanel.setLayout(new BoxLayout(creature_costPanel,BoxLayout.Y_AXIS));
        creature_addResourceBtn = new JButton("Add Resource");
        creature_addResourceBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        creature_addResourceBtn.addActionListener(this);
        creature_costPanel.add(creature_addResourceBtn);

        //evasionPanel
        JPanel creature_evasionPanel = new JPanel();
        creature_evasionPanel.setLayout(new BoxLayout(creature_evasionPanel,BoxLayout.Y_AXIS));
        creature_evasionPanel.add(new JLabel("Evasion Name"));
        creature_evasionName = new JTextField(10);
        creature_evasionPanel.add(creature_evasionName);
        creature_evasionPanel.add(new JLabel("Tap:"));
        creature_evasionType = new JComboBox<>(trueOrFales);
        creature_evasionPanel.add(creature_evasionType);


        //ability panel
        creature_abilityPanel = new JPanel();
        creature_abilityPanel.setLayout(new BoxLayout(creature_abilityPanel,BoxLayout.Y_AXIS));
        creature_addAbilityBtn = new JButton("Add ability");
        creature_addAbilityBtn.addActionListener(this);
        creature_abilityPanel.add(creature_addAbilityBtn);

        //flavor panel
        JPanel creature_flavorPanel = new JPanel();
        flavorTextField = new JTextArea(5,20); //Flavor FIELD
        flavorTextField.setLineWrap(true);
        flavorTextField.setWrapStyleWord(true);
        JScrollPane flavorScrollPane = new JScrollPane(flavorTextField);
        flavorScrollPane.setPreferredSize(new Dimension(200,100)); // keeps it stable
        creature_flavorPanel.add(new JLabel("Flavor Text:"));
        creature_flavorPanel.add(flavorScrollPane);

        //ability description panel
        JPanel creature_abilityDescPanel = new JPanel();
        abilityDesc_Text = new JTextArea(5,20); //ability description FIELD
        abilityDesc_Text.setLineWrap(true);
        abilityDesc_Text.setWrapStyleWord(true);
        JScrollPane ability_DescScrollPane = new JScrollPane(abilityDesc_Text);
        ability_DescScrollPane.setPreferredSize(new Dimension(200,100)); // keeps it stable
        creature_abilityDescPanel.add(new JLabel("Ability Description:"));
        creature_abilityDescPanel.add(ability_DescScrollPane);

        //tags panel
        creature_tagsPanel = new JPanel();
        creature_tagsPanel.setLayout(new BoxLayout(creature_tagsPanel,BoxLayout.Y_AXIS));
        creature_tagsPanel.add(new JLabel("tagName"));
        creature_addTagBtn = new JButton("Add Tag");
        creature_addTagBtn.addActionListener(this);
        creature_tagsPanel.add(creature_addTagBtn);


        //UPLOAD
        creature_createCardBtn = new JButton("Create Card");
        JPanel creature_lastRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        creature_lastRow.setMaximumSize(new Dimension(180,50));
        creature_lastRow.setBackground(Color.RED);
        creature_createCardBtn.addActionListener(this);
        creature_lastRow.add(creature_createCardBtn);

        //putting panels and borders in List
        String[] titles_border = {
                "Name","Cost", "Evasion","Abilities","Flavor Text", "Ability Descriptor", "Tags"
        };
        JPanel[] panels ={
                creature_namesPanel, creature_costPanel,creature_evasionPanel,
                creature_abilityPanel,creature_flavorPanel, creature_abilityDescPanel,
                creature_tagsPanel
        };
        //give each JPanel a border except lastRow
        for(int i = 0; i < titles_border.length; i++){
            TitledBorder border = BorderFactory.createTitledBorder(titles_border[i]);
            border.setTitleFont(new Font("Arial", Font.BOLD, 14));
            border.setTitleColor(Color.BLACK);
            border.setTitleJustification(TitledBorder.CENTER);
            border.setTitlePosition(TitledBorder.TOP);
            panels[i].setBorder(border);
        }

        //add each JPanel to creaturePanel. Also adds lastRow
        creaturePanel.add(creature_namesPanel);
        creaturePanel.add(creature_costPanel);
        creaturePanel.add(creature_evasionPanel);
        creaturePanel.add(creature_abilityPanel);
        creaturePanel.add(creature_flavorPanel);
        creaturePanel.add(creature_abilityDescPanel);
        creaturePanel.add(creature_tagsPanel);
        creaturePanel.add(creature_lastRow);

        //debugging backgrounds
        formPanel.setBackground(Color.gray);
        creaturePanel.setBackground(Color.darkGray);
        creature_namesPanel.setBackground(Color.yellow);
        creature_costPanel.setBackground(Color.pink);
        creature_abilityPanel.setBackground(Color.red);
        creature_lastRow.setBackground(Color.pink);

        creature_addAbility();
        creature_addResource();
        addTag();
        return creaturePanel;
    }

    //different functions
    private void creature_addAbility(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label_abilityName = new JLabel("Name:");
        JLabel label_abilityTap = new JLabel("Tappable:");
        JLabel label_abilityEffect = new JLabel("Effect:");

        JTextField add_abilityNameField = new JTextField(10);
        JComboBox<String> add_abilityTappable = new JComboBox<>(trueOrFales);
        JTextField add_abilityEffectField = new JTextField(10);
        JButton removeBtn = new JButton("Remove");


        panel.add(label_abilityName);
        panel.add(add_abilityNameField);

        panel.add(label_abilityTap);
        panel.add(add_abilityTappable);
        panel.add(label_abilityEffect);

        panel.add(add_abilityEffectField);
        panel.add(removeBtn);
        creature_abilityNameField.add(add_abilityNameField);
        creature_abilityTappableField.add(add_abilityTappable);
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
        JLabel label_biome = new JLabel("Biome");
        JLabel label_amount = new JLabel("Amount");
        JComboBox<String> biomeTypeField = new JComboBox<>(biomeType);
        JComboBox<String> amountField = new JComboBox<>(level);

        panel.add(label_biome);
        panel.add(biomeTypeField);
        panel.add(label_amount);
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
       // CreatureCard c = creature_getInput();

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
            ab.setTappable((String) creature_abilityTappableField.get(i).getSelectedItem());
            ab.setEffect(creature_abilityEffectField.get(i).getText());
            abilitiesList.add(ab);
        }

        for (JTextField i: creature_tagNameField) {
            tagsList.add(i.getText().trim());
        }
        //create creature card with inputs and pass the card to the card parser.
        creatureCard card = cardInputs(resourcesList,abilitiesList,tagsList);

        CardParser p = new CardParser();
        p.parseCreatureJSON(card);
        System.out.println(card);
    }
    private creatureCard cardInputs(ArrayList<Resource> res, ArrayList<Ability> abi, ArrayList<String> tag){
        creatureCard c = new creatureCard();
        c.setId(creature_cardIdField.getText().trim()); //;
        c.setCardName(creature_cardNameField.getText().trim());
        c.setScientificName(creature_scientificNameField.getText().trim());
        c.setCardLVL( (String) creature_input_cardLevelField.getSelectedItem());
        c.setFlavorText(flavorTextField.getText().trim());
        c.setAbilityDesc(abilityDesc_Text.getText().trim());
        c.setEvasionName(creature_evasionName.getText().trim());
        c.setEvasionType((String) creature_evasionType.getSelectedItem());
        c.setResources(res);
        c.setTags(tag);
        c.setResources(res);
        c.setAbilities(abi);
        return c;
    }
}

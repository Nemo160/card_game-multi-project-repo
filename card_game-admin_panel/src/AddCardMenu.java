import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class AddCardMenu extends GUI{
    JFrame cardFrame;
    private List<JComboBox> resourceField = new ArrayList<>();
    private List<JComboBox> resourceAmountField = new ArrayList<>();
    private JComboBox<String> input_cardLevelField;
    private JButton addResourceBtn, addAbilityBtn;
    private JTextField cardIDField, cardNameField,scientificNameField;
    private JRadioButton biomeBtn, creatureBtn;
    private JButton createCardBtn;
    private JPanel formPanel;
    private JPanel biomePanel;
    private JPanel creaturePanel, costPanel, abilityPanel;
    private final String[] trueOrFales = {"true","false"};
    private final String[] level = {"0","1","2","3","4","5"};
    private final String[] biomeType = {"Grassland","Forest", "Tundra","Desert","Urban","Freshwater","Marine"};
    private final String[] tags = {
            "predator","prey","carnivore", "herbivore","omnivore","insectivore",
            "mammal", "fish","amphibian","reptile", "bird","invertebrate",
            "domesticated", "wild"
    };

    public AddCardMenu(){
        cardFrame = createJFrame("Add Card Menu", 650,700);
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

        //biome and creature panels BUTTONS
        radioButtonPanel.setMaximumSize(new Dimension(200,30));
        radioButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        formPanel.add(row1);
        formPanel.add(Box.createRigidArea(new Dimension(0,10)));
        cardFrame.add(formPanel, BorderLayout.CENTER);
        cardFrame.setVisible(true);
    }

    public void getFields(){
        String id = cardIDField.getText();
        String name = cardNameField.getText();
        String scientificName = scientificNameField.getText();
        String LVL = (String) input_cardLevelField.getSelectedItem();

        for (int i = 0; i < resourceField.size(); i++) {
            String resource = (String) resourceField.get(i).getSelectedItem();
            String amount   = (String) resourceAmountField.get(i).getSelectedItem();

            System.out.println("Resource: " + resource + ", Amount: " + amount);
        }




        //get all the fields, put them into strings, arrays of strings. Send as parameter into Parser. Parse and end
        System.out.println(id);
        System.out.println(LVL);


    }
    private void validateFields(){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addResourceBtn){
            addResource();
        }
        if(e.getSource() == addAbilityBtn){
            addAbility();
        }
        if(e.getSource() == biomeBtn) {
            if(biomePanel == null){
                biomePanel = optionBiome();
                formPanel.add(biomePanel);
            }
            if(creaturePanel != null)
            creaturePanel.setVisible(false);
            biomePanel.setVisible(true);
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
            creaturePanel.setVisible(true);
            }
        else if (e.getSource() == createCardBtn) {
            getFields();
        }
        formPanel.revalidate();
        formPanel.repaint();
    }
    private JPanel optionBiome(){
        JPanel biomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel underCon = new JLabel("PAGE UNDER CONSTRUCTION");
        underCon.setPreferredSize(new Dimension(300,300));
        underCon.setAlignmentX(Component.CENTER_ALIGNMENT);
        biomePanel.add(new JLabel("Biome Name:"));
        biomePanel.add(new JTextField(15));
        biomePanel.add(new JTextField(5));
        biomePanel.add(underCon);
        return biomePanel;
    }

    private JPanel optionCreature(){
        JPanel creaturePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //name and id panel
        JPanel namesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namesPanel.add(new JLabel("id:"));
        cardIDField = new JTextField(5);
        namesPanel.add(cardIDField);

        namesPanel.add(new JLabel("Card Name:"));
        cardNameField = new JTextField(10);
        namesPanel.add(cardNameField);

        namesPanel.add(new JLabel("Scientific Name:"));
        scientificNameField = new JTextField(10);
        namesPanel.add(scientificNameField);

        namesPanel.add(new JLabel("Card LVL"));
        input_cardLevelField = new JComboBox<>(level);
        namesPanel.add(input_cardLevelField);

        //Resource panel
        costPanel = new JPanel();
        costPanel.setLayout(new BoxLayout(costPanel,BoxLayout.Y_AXIS));
        costPanel.add(new JLabel("Resource Cost:"));
        addResourceBtn = new JButton("Add Resource");
        addResourceBtn.addActionListener(this);
        costPanel.add(addResourceBtn);

        //evasionPanel
        JPanel evasionPanel = new JPanel();
        evasionPanel.setLayout(new BoxLayout(evasionPanel,BoxLayout.Y_AXIS));
        evasionPanel.add(new JLabel("Evasion Name"));
        JTextField evasionName = new JTextField(10);
        evasionPanel.add(evasionName);
        evasionPanel.add(new JLabel("Tap:"));
        JComboBox<String> evasionType = new JComboBox<>(trueOrFales);
        evasionPanel.add(evasionType);

        JPanel tagsPanel = new JPanel();
        tagsPanel.setLayout(new BoxLayout(tagsPanel,BoxLayout.X_AXIS));

        //ability panel
        abilityPanel = new JPanel();
        abilityPanel.setLayout(new BoxLayout(abilityPanel,BoxLayout.Y_AXIS));
        abilityPanel.add(new JLabel("Abilities"));
        addAbilityBtn = new JButton("Add ability");
        addAbilityBtn.addActionListener(this);
        abilityPanel.add(addAbilityBtn);

        //flavor panel
        JPanel flavorPanel = new JPanel();
        JTextArea flavorTextField = new JTextArea(5,20); //Flavor FIELD
        flavorTextField.setLineWrap(true);
        flavorTextField.setWrapStyleWord(true);
        JScrollPane flavorScrollPane = new JScrollPane(flavorTextField);
        flavorScrollPane.setPreferredSize(new Dimension(200,100)); // keeps it stable
        flavorPanel.add(new JLabel("Flavor Text:"));
        flavorPanel.add(flavorScrollPane);

        //Ability description
        JPanel abilityDescPanel = new JPanel();
        JTextArea abilityDesc_Text = new JTextArea(5,20); //ability description FIELD
        abilityDesc_Text.setLineWrap(true);
        abilityDesc_Text.setWrapStyleWord(true);
        JScrollPane ability_DescScrollPane = new JScrollPane(abilityDesc_Text);
        ability_DescScrollPane.setPreferredSize(new Dimension(200,100)); // keeps it stable
        abilityDescPanel.add(new JLabel("Ability Description:"));
        abilityDescPanel.add(ability_DescScrollPane);

        //UPLOAD
        createCardBtn = new JButton("Create Card");
        JPanel lastRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lastRow.setMaximumSize(new Dimension(180,50));
        lastRow.setBackground(Color.RED);
        createCardBtn.addActionListener(this);
        lastRow.add(createCardBtn);

        //add panels to creaturePanel
        creaturePanel.add(namesPanel);
        creaturePanel.add(costPanel);
        creaturePanel.add(evasionPanel);
        creaturePanel.add(abilityPanel);
        creaturePanel.add(flavorPanel);
        creaturePanel.add(abilityDescPanel);
        creaturePanel.add(lastRow);

        //debugging backgrounds
        formPanel.setBackground(Color.gray);
        creaturePanel.setBackground(Color.darkGray);
        namesPanel.setBackground(Color.yellow);
        costPanel.setBackground(Color.pink);
        abilityPanel.setBackground(Color.red);

        addAbility();
        addResource();
        return creaturePanel;
    }

    private void addAbility(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Name:"));
        JTextField abilityNameField = new JTextField(10);
        panel.add(abilityNameField);

        panel.add(new JLabel("Tappable:"));
        JComboBox<String> abilityTappable = new JComboBox<>(trueOrFales);
        panel.add(abilityTappable);

        panel.add(new JLabel("Effect:"));
        JTextField abilityEffectField = new JTextField(10);
        panel.add(abilityEffectField);

        JButton removeBtn = new JButton("Remove");
        panel.add(removeBtn);

        //adds remove button
        removeBtn.addActionListener(e -> {
            abilityPanel.remove(panel);
            abilityPanel.revalidate();
            abilityPanel.repaint();
        });

        abilityPanel.add(panel);
        abilityPanel.revalidate();
        abilityPanel.repaint();


    }
    private void addResource(){
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
        resourceField.add(biomeTypeField);
        resourceAmountField.add(amountField);
        JButton removeBtn = new JButton("Remove");
        panel.add(removeBtn);

        removeBtn.addActionListener(e -> {
            // Remove UI panel
            costPanel.remove(panel);
            costPanel.revalidate();
            costPanel.repaint();

            // Remove references from lists
            resourceField.remove(biomeTypeField);
            resourceAmountField.remove(amountField);
        });

        costPanel.add(panel);
        costPanel.revalidate();
        costPanel.repaint();
    }

}

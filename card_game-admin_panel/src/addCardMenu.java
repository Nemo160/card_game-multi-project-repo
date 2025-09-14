import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class addCardMenu extends GUI{
    JFrame cardFrame;
    private List<JComboBox> resourceField = new ArrayList<>();
    private List<JComboBox> resourceAmountField = new ArrayList<>();
    private JButton addResourceBtn, addAbilityBtn;
    private JTextField cardIDField, cardNameField,scientificNameField;
    private JRadioButton biomeButton,creatureButton;
    private JPanel formPanel;
    private JPanel biomePanel;
    private JPanel creaturePanel, costPanel, abilityPanel;
    private final String[] trueOrFales = {"true","false"};
    private final String[] tags = {
            "predator","prey","carnivore", "herbivore","omnivore","insectivore",
            "mammal", "fish","amphibian","reptile", "bird","invertebrate",
            "domesticated", "wild"
    };
    private final String[] level = {"0","1","2","3","4","5"};
    private final String[] biomeType = {"Grassland","Forest", "Tundra","Desert","Urban","Freshwater","Marine"};

    public addCardMenu(){
        cardFrame = createJFrame("Add Card Menu", 600,650);
        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel,BoxLayout.Y_AXIS));
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.setMaximumSize(new Dimension(180,50));
        row1.setBackground(Color.RED);

        //Radio buttons
        biomeButton = new JRadioButton("Biome");
        creatureButton = new JRadioButton("Creature");
        ButtonGroup group1 = new ButtonGroup();
        group1.add(biomeButton);
        group1.add(creatureButton);
        biomeButton.addActionListener(this);
        creatureButton.addActionListener(this);

        JPanel radioButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        radioButtonPanel.add(biomeButton);
        radioButtonPanel.add(creatureButton);
        radioButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        row1.add(radioButtonPanel);

        //biome and creature panels
        radioButtonPanel.setMaximumSize(new Dimension(200,30));
        radioButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);


        formPanel.add(row1);
        formPanel.add(Box.createRigidArea(new Dimension(0,10)));
        cardFrame.add(formPanel, BorderLayout.CENTER);
        cardFrame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addResourceBtn){
            addResource();
        }
        if(e.getSource() == addAbilityBtn){
            addAbility();
        }
        if(e.getSource() == biomeButton) {
            if(biomePanel == null){
                biomePanel = optionBiome();
                formPanel.add(biomePanel);
            }
            if(creaturePanel != null)
            creaturePanel.setVisible(false);
            biomePanel.setVisible(true);
        }
        else if(e.getSource() == creatureButton){
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
        JComboBox<String> input_cardLevelField = new JComboBox<>(level);
        namesPanel.add(input_cardLevelField);

        //cost panel
        costPanel = new JPanel();
        costPanel.setLayout(new BoxLayout(costPanel,BoxLayout.Y_AXIS));
        costPanel.add(new JLabel("Resource Cost:"));
        addResourceBtn = new JButton("Add Resource");
        addResourceBtn.addActionListener(this);


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
        JTextArea flavorTextField = new JTextArea(5,20);
        JPanel flavorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flavorPanel.add(new JLabel("Flavor Text:"));
        flavorPanel.add(flavorTextField);

        //add panels to creaturePanel
        creaturePanel.add(namesPanel);
        creaturePanel.add(costPanel);
        creaturePanel.add(evasionPanel);
        creaturePanel.add(abilityPanel);
        creaturePanel.add(flavorPanel);
        costPanel.add(addResourceBtn);

        namesPanel.setBackground(Color.yellow);
        formPanel.setBackground(Color.gray);
        creaturePanel.setBackground(Color.darkGray);
        costPanel.setBackground(Color.pink);
        abilityPanel.setBackground(Color.red);


        addAbility();
        addResource();
        return creaturePanel;
    }

    private void addAbility(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT
        ));


        panel.add(new JLabel("Name:"));
        JTextField abilityNameField = new JTextField(10);
        panel.add(abilityNameField);

        panel.add(new JLabel("Tappable:"));
        JComboBox<String> abilityTappable = new JComboBox<>(trueOrFales);
        panel.add(abilityTappable);

        panel.add(new JLabel("Effect:"));
        panel.add(new JTextField(10));
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

        costPanel.add(panel);
        costPanel.revalidate();
        costPanel.repaint();
    }


}

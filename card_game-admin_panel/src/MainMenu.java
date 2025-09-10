
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class MainMenu extends GUI{
    //private addCardMenu addCardMenu;
    private JButton editCardBtn;
    private JButton addCardBtn;
    private JButton removeCardBtn;
    private JButton listCardsBtn;
    private JButton uploadFile;
    private JFileChooser j;
    JFrame frame;
    public MainMenu(){

        frame = createJFrame("Card Game GUI",500,600);
        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS)); //manually sets layout
        Border border =  BorderFactory.createLineBorder(Color.red);
        // Border informationBorder = BorderFactory.createLineBorder(Color.black);

        JPanel panel_cards = createPanel(frame,null, null);
        JPanel panel_viewCards = createPanel(frame,null, null);
        //JPanel panel_information = createPanel(frame,200, 150);

        //Manage cards panel
        JLabel label_manageCards = addLabel(panel_cards,"Manage Cards");
        label_manageCards.setFont(new Font("Arial",Font.BOLD,15));
        editCardBtn = addButton(panel_cards,"Edit Card");
        addCardBtn = addButton(panel_cards, "Add card");
        removeCardBtn = addButton(panel_cards, "Remove card");

        //Card information panel
        JLabel label_cardInformation = addLabel(panel_viewCards,"Card Information");
        label_cardInformation.setFont(new Font("Times New Roman",Font.BOLD,15));
        listCardsBtn = addButton(panel_viewCards, "List cards");
        uploadFile = addButton(panel_viewCards,"Upload File");


        panel_cards.setBorder(border);
        frame.setVisible(true);
        // panel_information.setBorder(informationBorder);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addCardBtn){
            System.out.println("add card");
            addCardBtn.setEnabled(false);
           // addCardMenu = new addCardMenu();
            frame.dispose();


        }
        if(e.getSource() == removeCardBtn){
            //Task.write();
            System.out.println("remove card");
        }
        if(e.getSource() == listCardsBtn){
            System.out.println("list cards");
           // Task.upload();
        }

        if(e.getSource() == uploadFile){
            j = new JFileChooser("C:\\Users\\pc\\Desktop");
            j.showOpenDialog(null);
            if(j.getSelectedFile() != null){
                File selectedFile = j.getSelectedFile();
                String filepath = selectedFile.getAbsolutePath();
                System.out.println(filepath);

            }



        }

        if(e.getSource() == editCardBtn){
            System.out.println("edit card");
        }

    }
}
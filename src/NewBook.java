import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class NewBook {

    private JFrame newBookFrame;
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtIsbn;
    private JTextField txtPrice;
    private JTextField txtPublishingDate;
    private JTextField txtAuthorFirst;
    private JTextField txtInitials;
    private SQLConnection connection;


    /**
     * Create the application.
     */
    public NewBook(SQLConnection connection) {
        this.connection = connection;
        initialize();
        newBookFrame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    @SuppressWarnings("unchecked")
    private void initialize() {
        newBookFrame = new JFrame();
        newBookFrame.setTitle("Add a New Book");
        newBookFrame.setBounds(100, 100, 1044, 822);
        newBookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newBookFrame.getContentPane().setLayout(null);
        newBookFrame.setResizable(false);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        newBookFrame.setLocation(dim.width / 2 - newBookFrame.getSize().width / 2,
                (int) (dim.getHeight() / 2 - newBookFrame.getSize().height / 2));
        newBookFrame.setVisible(true);
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        btnSubmit.setBounds(861, 709, 115, 29);
        newBookFrame.getContentPane().add(btnSubmit);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIinit();
                newBookFrame.dispose();
            }
        });
        btnCancel.setBounds(79, 709, 115, 29);
        newBookFrame.getContentPane().add(btnCancel);

        txtTitle = new JTextField();
        txtTitle.setToolTipText("Please enter the Title of the book");
        txtTitle.setBounds(35, 27, 250, 29);
        newBookFrame.getContentPane().add(txtTitle);
        txtTitle.setColumns(10);

        txtAuthor = new JTextField();
        txtAuthor.setToolTipText("Last Name of Author");
        txtAuthor.setBounds(35, 100, 121, 29);
        newBookFrame.getContentPane().add(txtAuthor);
        txtAuthor.setColumns(10);

        txtIsbn = new JTextField();
        txtIsbn.setToolTipText("13 digit identifier");
        txtIsbn.setBounds(35, 211, 146, 26);
        newBookFrame.getContentPane().add(txtIsbn);
        txtIsbn.setColumns(10);

        txtPrice = new JTextField();
        txtPrice.setToolTipText("ex. 25.50");
        txtPrice.setBounds(35, 264, 146, 26);
        newBookFrame.getContentPane().add(txtPrice);
        txtPrice.setColumns(10);

        JLabel lblBookStoresThat = new JLabel("Stocked at:");
        lblBookStoresThat.setBounds(35, 534, 204, 34);
        newBookFrame.getContentPane().add(lblBookStoresThat);

        JCheckBox chckbxBarnsAndNoble = new JCheckBox("Barns and Noble");
        chckbxBarnsAndNoble.setBounds(35, 580, 170, 29);
        newBookFrame.getContentPane().add(chckbxBarnsAndNoble);

        JCheckBox chckbxAmazon = new JCheckBox("Amazon");
        chckbxAmazon.setBounds(35, 622, 139, 29);
        newBookFrame.getContentPane().add(chckbxAmazon);

        JCheckBox chckbxBorders = new JCheckBox("Borders");
        chckbxBorders.setBounds(286, 580, 139, 29);
        newBookFrame.getContentPane().add(chckbxBorders);

        JCheckBox chckbxHalfPriceBookvb = new JCheckBox("Half Price Books");
        chckbxHalfPriceBookvb.setBounds(286, 622, 183, 29);
        newBookFrame.getContentPane().add(chckbxHalfPriceBookvb);

        JCheckBox chckbxPowells = new JCheckBox("Powells");
        chckbxPowells.setBounds(539, 580, 139, 29);
        newBookFrame.getContentPane().add(chckbxPowells);

        JCheckBox chckbxUwBookStore = new JCheckBox("UW Book Store");
        chckbxUwBookStore.setBounds(539, 622, 170, 29);
        newBookFrame.getContentPane().add(chckbxUwBookStore);

        JCheckBox chckbxNewCheckBox = new JCheckBox("PLU Book Store");
        chckbxNewCheckBox.setBounds(781, 580, 183, 29);
        newBookFrame.getContentPane().add(chckbxNewCheckBox);

        JCheckBox chckbxUpsBookStore = new JCheckBox("UPS Book Store");
        chckbxUpsBookStore.setBounds(781, 622, 170, 29);
        newBookFrame.getContentPane().add(chckbxUpsBookStore);

        JLabel lblPublisher = new JLabel("Publisher:");
        lblPublisher.setBounds(35, 348, 115, 29);
        newBookFrame.getContentPane().add(lblPublisher);



        JRadioButton rdbtnPengiun = new JRadioButton("Pengiun");
        rdbtnPengiun.setBounds(35, 378, 155, 29);
        newBookFrame.getContentPane().add(rdbtnPengiun);

        JRadioButton rdbtnSchoolastic = new JRadioButton("Schoolastic");
        rdbtnSchoolastic.setBounds(35, 415, 155, 29);
        newBookFrame.getContentPane().add(rdbtnSchoolastic);

        JRadioButton rdbtnPearson = new JRadioButton("Pearson");
        rdbtnPearson.setBounds(35, 452, 155, 29);
        newBookFrame.getContentPane().add(rdbtnPearson);

        JRadioButton rdbtnSimonandSchuster = new JRadioButton("Simon and Schuster");
        rdbtnSimonandSchuster.setBounds(35, 489, 188, 29);
        newBookFrame.getContentPane().add(rdbtnSimonandSchuster);

        ButtonGroup publisher = new ButtonGroup();
        publisher.add(rdbtnPearson);
        publisher.add(rdbtnSchoolastic);
        publisher.add(rdbtnPengiun);
        publisher.add(rdbtnSimonandSchuster);

        JTextPane txtpnBookDescription = new JTextPane();
        txtpnBookDescription.setToolTipText("Enter the Book Description");
        txtpnBookDescription.setBounds(563, 53, 432, 279);
        newBookFrame.getContentPane().add(txtpnBookDescription);

        txtPublishingDate = new JTextField();
        txtPublishingDate.setToolTipText("mm/dd/yyyy");
        txtPublishingDate.setBounds(35, 320, 146, 26);
        newBookFrame.getContentPane().add(txtPublishingDate);
        txtPublishingDate.setColumns(10);

        JComboBox comboBox = new JComboBox();
        comboBox.setToolTipText("Select the Genre");
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Select Genre>", "Fiction", "Non-Fiction",
                "Sci-Fi/Fantacy", "Horror", "Comics/Graphic Novel", "Cooking", "Education", "Biography"}));
        comboBox.setBounds(35, 145, 250, 29);
        newBookFrame.getContentPane().add(comboBox);

        txtAuthorFirst = new JTextField();
        txtAuthorFirst.setToolTipText("First Name of Author");
        txtAuthorFirst.setBounds(171, 101, 128, 26);
        newBookFrame.getContentPane().add(txtAuthorFirst);
        txtAuthorFirst.setColumns(10);

        txtInitials = new JTextField();
        txtInitials.setToolTipText("Initials, leave blank if none");
        txtInitials.setBounds(314, 101, 56, 26);
        newBookFrame.getContentPane().add(txtInitials);
        txtInitials.setColumns(10);

        JLabel lblTitleOfBook = new JLabel("Title of Book");
        lblTitleOfBook.setBounds(35, 0, 100, 39);
        newBookFrame.getContentPane().add(lblTitleOfBook);

        JLabel lblPublishingDate = new JLabel("Publishing Date");
        lblPublishingDate.setBounds(35, 293, 115, 29);
        newBookFrame.getContentPane().add(lblPublishingDate);

        JLabel lblListPrice = new JLabel("List Price");
        lblListPrice.setBounds(35, 242, 69, 20);
        newBookFrame.getContentPane().add(lblListPrice);

        JLabel lblIsbn = new JLabel("ISBN");
        lblIsbn.setBounds(35, 190, 69, 20);
        newBookFrame.getContentPane().add(lblIsbn);

        JLabel lblBookDescription = new JLabel("Book Description");
        lblBookDescription.setBounds(563, 31, 146, 20);
        newBookFrame.getContentPane().add(lblBookDescription);

        JLabel lblAuthorLast = new JLabel("Author Last");
        lblAuthorLast.setBounds(35, 76, 105, 20);
        newBookFrame.getContentPane().add(lblAuthorLast);

        JLabel lblAuthorFirst = new JLabel("Author First");
        lblAuthorFirst.setBounds(170, 76, 115, 20);
        newBookFrame.getContentPane().add(lblAuthorFirst);

        JLabel lblInitials = new JLabel("Initials");
        lblInitials.setBounds(314, 76, 69, 20);
        newBookFrame.getContentPane().add(lblInitials);
    }

    private void GUIinit() {
        // TODO Auto-generated method stub
        Gui initWIndow;
        try {
            initWIndow = new Gui();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

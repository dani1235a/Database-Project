import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class NewBook {

    //Jframe
    private JFrame newBookFrame;
    //Text Feilds for info
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtIsbn;
    private JTextField txtPrice;
    private JTextField txtPublishingDate;
    private JTextField txtAuthorFirst;
    private JTextField txtInitials;
    private JTextPane txtpnBookDescription;
    private JComboBox comboBox;
    private ButtonGroup publishers;
    //SQLconnection that was established during startup of program.
    private SQLConnection connection;
    //text field input for book
    private String TitleOfBook;
    private String AuthorFirstName;
    private String AuthorLastName;
    private String AuthorInit;
    private String ISBN;
    private String PublishingDate;
    private String Price;
    private String Description;
    private String Publisher;
    private String Genre;


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
    private void initialize() {
        setUpNewBookFrame();
        addJLables();
        addJTextFields();
        addBookStores();
        addRadioButtons();
        addCancelButton();
        addSubmitButton();
    }


    /**
     * Takes in the basic book information.
     * Title, ISBN, Author First, Author Last, Initials (optional), Price, PublishingDate
     */
    private void basicBookInfo() {
        TitleOfBook = txtTitle.getText();
        AuthorFirstName = txtAuthorFirst.getText();
        AuthorLastName = txtAuthor.getText();
        AuthorInit = txtInitials.getText();
        PublishingDate = txtPublishingDate.getText();
        ISBN = txtIsbn.getText();
        Price = txtPrice.getText();
        Publisher = publishers.getSelection().getActionCommand();
        Description = txtpnBookDescription.getText();
        Genre = comboBox.getSelectedItem().toString();
    }

    /**
     * Adds a submit button that prompts success! when it adds the book to the table.
     */
    private void addSubmitButton() {
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(arg0 -> {
            //TODO: Have prompt say "Success!" or "Failure/MIssing Information!" if it works or doesnt
                //TODO: Make sure all vital information feilds are populated.
            //TODO: write script to add the book into database. - optional we can show bookID
            //TODO: Go to a JTable with just "select * from Books" so they can see the book there.
            basicBookInfo();

            System.out.println("Book Title: " + TitleOfBook);
            System.out.println("Author First: " + AuthorFirstName);
            System.out.println("Author Last: " + AuthorLastName);
            System.out.println("Author init: " + AuthorInit);
            System.out.println("Publishing date: " + PublishingDate);
            System.out.println("Publisher: " + Publisher);
            System.out.println("ISBN:" + ISBN);
            System.out.println("List Price: " + Price);
            System.out.println("Description: " + Description);
            System.out.println("Genre: " + Genre);


        });
        btnSubmit.setBounds(861, 709, 115, 29);
        newBookFrame.getContentPane().add(btnSubmit);
    }

    /**
     * Adds cancel button that takes us back to the Gui class.
     */
    private void addCancelButton() {
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> {
            try {
                Gui back = new Gui();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            newBookFrame.dispose();
        });
        btnCancel.setBounds(79, 709, 115, 29);
        newBookFrame.getContentPane().add(btnCancel);
    }


    /**
     * This method adds the Publishers and Bookstores to the GUI
     */
    private void addRadioButtons() {

        comboBox = new JComboBox();
        comboBox.setToolTipText("Select the Genre");
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"<Select Genre>", "Fiction", "Non-Fiction",
                "Sci-Fi/Fantacy", "Horror", "Comics/Graphic Novel", "Education", "Biography"}));
        comboBox.setBounds(35, 145, 250, 29);
        newBookFrame.getContentPane().add(comboBox);

        //create radio buttons
        JRadioButton rdbtnPengiun = new JRadioButton("Pengiun");
        rdbtnPengiun.setBounds(35, 378, 155, 29);
        rdbtnPengiun.setSelected(true);
        newBookFrame.getContentPane().add(rdbtnPengiun);

        JRadioButton rdbtnScholastic = new JRadioButton("Schoolastic");
        rdbtnScholastic.setBounds(35, 415, 155, 29);
        newBookFrame.getContentPane().add(rdbtnScholastic);

        JRadioButton rdbtnPearson = new JRadioButton("Pearson");
        rdbtnPearson.setBounds(35, 452, 155, 29);
        newBookFrame.getContentPane().add(rdbtnPearson);

        JRadioButton rdbtnSimonandSchuster = new JRadioButton("Simon and Schuster");
        rdbtnSimonandSchuster.setBounds(35, 489, 188, 29);
        newBookFrame.getContentPane().add(rdbtnSimonandSchuster);

        publishers = new ButtonGroup();
        publishers.add(rdbtnPearson);
        publishers.add(rdbtnScholastic);
        publishers.add(rdbtnPengiun);
        publishers.add(rdbtnSimonandSchuster);

        rdbtnPearson.setActionCommand("Pearson");
        rdbtnPengiun.setActionCommand("Pengiun");
        rdbtnScholastic.setActionCommand("Scholastic");
        rdbtnSimonandSchuster.setActionCommand("SimonAndSchuster");
    }

    /**
     * Adds the Bookstores to the GUI.
     */
    private void addBookStores() {

        // create greyed out checkBoxed
        JCheckBox chckbxBarnsAndNoble = new JCheckBox("Barns and Noble");
        chckbxBarnsAndNoble.setBounds(35, 580, 170, 29);
        chckbxBarnsAndNoble.setEnabled(false);
        newBookFrame.getContentPane().add(chckbxBarnsAndNoble);

        JCheckBox chckbxAmazon = new JCheckBox("Amazon");
        chckbxAmazon.setBounds(35, 622, 139, 29);
        chckbxAmazon.setEnabled(false);
        newBookFrame.getContentPane().add(chckbxAmazon);

        JCheckBox chckbxBorders = new JCheckBox("Borders");
        chckbxBorders.setBounds(286, 580, 139, 29);
        chckbxBorders.setEnabled(false);
        newBookFrame.getContentPane().add(chckbxBorders);

        JCheckBox chckbxHalfPriceBookvb = new JCheckBox("Half Price Books");
        chckbxHalfPriceBookvb.setBounds(286, 622, 183, 29);
        chckbxHalfPriceBookvb.setEnabled(false);
        newBookFrame.getContentPane().add(chckbxHalfPriceBookvb);

        JCheckBox chckbxPowells = new JCheckBox("Powells");
        chckbxPowells.setBounds(539, 580, 139, 29);
        chckbxPowells.setEnabled(false);
        newBookFrame.getContentPane().add(chckbxPowells);

        JCheckBox chckbxUwBookStore = new JCheckBox("UW Book Store");
        chckbxUwBookStore.setBounds(539, 622, 170, 29);
        chckbxUwBookStore.setEnabled(false);
        newBookFrame.getContentPane().add(chckbxUwBookStore);

        JCheckBox chckbxPluBookStore = new JCheckBox("PLU Book Store");
        chckbxPluBookStore.setBounds(781, 580, 183, 29);
        chckbxPluBookStore.setEnabled(false);
        newBookFrame.getContentPane().add(chckbxPluBookStore);

        JCheckBox chckbxUpsBookStore = new JCheckBox("UPS Book Store");
        chckbxUpsBookStore.setBounds(781, 622, 170, 29);
        chckbxUpsBookStore.setEnabled(false);
        newBookFrame.getContentPane().add(chckbxUpsBookStore);
    }

    /**
     * Sets up the overall frame.
     */
    private void setUpNewBookFrame() {
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
    }

    /**
     * Adds all the JTextFields that will be used to input information.
     */
    private void addJTextFields() {
        //Title of Book
        txtTitle = new JTextField();
        txtTitle.setToolTipText("Please enter the Title of the book");
        txtTitle.setBounds(35, 27, 250, 29);
        newBookFrame.getContentPane().add(txtTitle);
        txtTitle.setColumns(10);
        //Author Last Name
        txtAuthor = new JTextField();
        txtAuthor.setToolTipText("Last Name of Author");
        txtAuthor.setBounds(35, 100, 121, 29);
        newBookFrame.getContentPane().add(txtAuthor);
        txtAuthor.setColumns(10);
        //Author first name
        txtAuthorFirst = new JTextField();
        txtAuthorFirst.setToolTipText("First Name of Author");
        txtAuthorFirst.setBounds(171, 101, 128, 26);
        newBookFrame.getContentPane().add(txtAuthorFirst);
        txtAuthorFirst.setColumns(10);
        //Author initialls
        txtInitials = new JTextField();
        txtInitials.setToolTipText("Initials, leave blank if none");
        txtInitials.setBounds(314, 101, 56, 26);
        newBookFrame.getContentPane().add(txtInitials);
        txtInitials.setColumns(10);
        //ISBN
        txtIsbn = new JTextField();
        txtIsbn.setToolTipText("13 digit identifier");
        txtIsbn.setBounds(35, 211, 146, 26);
        newBookFrame.getContentPane().add(txtIsbn);
        txtIsbn.setColumns(10);
        //price
        txtPrice = new JTextField();
        txtPrice.setToolTipText("ex. 25.50");
        txtPrice.setBounds(35, 264, 146, 26);
        newBookFrame.getContentPane().add(txtPrice);
        txtPrice.setColumns(10);
        //publishing date
        txtPublishingDate = new JTextField();
        txtPublishingDate.setToolTipText("mm/dd/yyyy");
        txtPublishingDate.setBounds(35, 320, 146, 26);
        newBookFrame.getContentPane().add(txtPublishingDate);
        txtPublishingDate.setColumns(10);
        // book description
        txtpnBookDescription = new JTextPane();
        txtpnBookDescription.setToolTipText("Enter the Book Description");
        txtpnBookDescription.setBounds(563, 53, 432, 279);
        newBookFrame.getContentPane().add(txtpnBookDescription);

    }

    /**
     * Adds the JLables throughout the GUI
     */
    private void addJLables() {
        JLabel lblAuthorLast = new JLabel("Author Last");
        lblAuthorLast.setBounds(35, 76, 105, 20);
        newBookFrame.getContentPane().add(lblAuthorLast);

        JLabel lblAuthorFirst = new JLabel("Author First");
        lblAuthorFirst.setBounds(170, 76, 115, 20);
        newBookFrame.getContentPane().add(lblAuthorFirst);

        JLabel lblInitials = new JLabel("Initials");
        lblInitials.setBounds(314, 76, 69, 20);
        newBookFrame.getContentPane().add(lblInitials);

        JLabel lblIsbn = new JLabel("ISBN");
        lblIsbn.setBounds(35, 190, 69, 20);
        newBookFrame.getContentPane().add(lblIsbn);

        JLabel lblBookDescription = new JLabel("Book Description");
        lblBookDescription.setBounds(563, 31, 146, 20);
        newBookFrame.getContentPane().add(lblBookDescription);

        JLabel lblTitleOfBook = new JLabel("Title of Book");
        lblTitleOfBook.setBounds(35, 0, 100, 39);
        newBookFrame.getContentPane().add(lblTitleOfBook);

        JLabel lblPublishingDate = new JLabel("Publishing Date");
        lblPublishingDate.setBounds(35, 293, 115, 29);
        newBookFrame.getContentPane().add(lblPublishingDate);

        JLabel lblListPrice = new JLabel("List Price");
        lblListPrice.setBounds(35, 242, 69, 20);
        newBookFrame.getContentPane().add(lblListPrice);

        JLabel lblPublisher = new JLabel("Publisher:");
        lblPublisher.setBounds(35, 348, 115, 29);
        newBookFrame.getContentPane().add(lblPublisher);

        JLabel lblBookStores = new JLabel("Stocked at:");
        lblBookStores.setBounds(35, 534, 204, 34);
        newBookFrame.getContentPane().add(lblBookStores);
    }
}

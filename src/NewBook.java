import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class NewBook {

    //SQLconnection that was established during startup of program.
    private final Connection connection;
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
    private JComboBox<String> comboBox;
    private ButtonGroup publishers;
    //Add checkBoxes (Bookstores) as fields
    private JCheckBox chckbxBarnsAndNoble;
    private JCheckBox chckbxAmazon;
    private JCheckBox chckbxBorders;
    private JCheckBox chckbxHalfPriceBooks;
    private JCheckBox chckbxPowells;
    private JCheckBox chckbxUwBookStore;
    private JCheckBox chckbxPluBookStore;
    private JCheckBox chckbxUpsBookStore;
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

    //booleans for fields
    private Boolean hasTitle = false;
    private Boolean hasAuthorF = false;
    private Boolean hasAuthorL = false;
    private Boolean hasAuthorI = false;
    private Boolean hasISBN = false;
    private Boolean hasPublishingDate = false;
    private Boolean hasPrice = false;
    private Boolean hasDescription = false;
    private Boolean hasGenre = false;


    /**
     * Create the application.
     */
    public NewBook(Connection connection) {
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
        //noinspection ConstantConditions
        Genre = comboBox.getSelectedItem().toString();

        if (!TitleOfBook.equals("")) {
            hasTitle = true;
        }
        if (!AuthorLastName.equals("")) {
            hasAuthorL = true;
        }
        if (!AuthorFirstName.equals("")) {
            hasAuthorF = true;
        }
        if (!AuthorInit.equals("")) {
            hasAuthorI = true;
        }
        if (!PublishingDate.equals("")) {
            hasPublishingDate = true;
        }
        if (!(ISBN.equals("") || ISBN == null)) {
            hasISBN = true;
        }
        if (!Price.equals("")) {
            hasPrice = true;
        }
        if (!Description.equals("")) {
            hasDescription = true;
        }
        if (!Genre.equals("<Select Genre>")) {
            hasGenre = true;
        }
    }

    /**
     * Adds a submit button that prompts success! when it adds the book to the table.
     */
    private void addSubmitButton() {
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(arg0 -> {
            basicBookInfo();
            if (hasTitle && hasISBN && hasAuthorL && hasAuthorF) {
                Book book = new Book(TitleOfBook, AuthorFirstName, AuthorLastName, Publisher, ISBN, connection);
                if (hasGenre) book.setGenre(Genre);
                if (hasDescription) book.setBookDescription(Description);
                if (hasPrice) book.setListPrice(Price);
                if (hasPublishingDate) book.setDate(PublishingDate);
                if (hasAuthorI) book.setAuthorInit(AuthorInit);
                book.addToBookStoreDatabase();
            } else {
                JOptionPane.showMessageDialog(null, "Warning: Not enough Information to create a Book!\n"
                        + "Please make sure that the Title, Author First Name, Author Last Name, and ISBN have values.");

            }

            //TODO: Go to a JTable with just "select * from Books" so they can see the book there.



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
            new Gui();
            newBookFrame.dispose();
        });
        btnCancel.setBounds(79, 709, 115, 29);
        newBookFrame.getContentPane().add(btnCancel);
    }


    /**
     * This method adds the Publishers and Bookstores to the GUI
     */
    private void addRadioButtons() {

        comboBox = new JComboBox<>();
        comboBox.setToolTipText("Select the Genre");
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"<Select Genre>", "Fiction", "Non-Fiction",
                "Sci-Fi/Fantacy", "Horror", "Comics/Graphic Novel", "Education", "Biography"}));
        comboBox.setBounds(35, 145, 250, 29);
        newBookFrame.getContentPane().add(comboBox);

        //create radio buttons
        JRadioButton rdbtnPengiun = new JRadioButton(Gui.Penguin);
        rdbtnPengiun.setBounds(35, 378, 155, 29);
        rdbtnPengiun.setSelected(true);
        newBookFrame.getContentPane().add(rdbtnPengiun);

        JRadioButton rdbtnScholastic = new JRadioButton(Gui.Scholastic);
        rdbtnScholastic.setBounds(35, 415, 155, 29);
        newBookFrame.getContentPane().add(rdbtnScholastic);

        JRadioButton rdbtnPearson = new JRadioButton(Gui.Pearson);
        rdbtnPearson.setBounds(35, 452, 155, 29);
        newBookFrame.getContentPane().add(rdbtnPearson);

        JRadioButton rdbtnSimonandSchuster = new JRadioButton(Gui.SandS);
        rdbtnSimonandSchuster.setBounds(35, 489, 188, 29);
        newBookFrame.getContentPane().add(rdbtnSimonandSchuster);

        publishers = new ButtonGroup();
        publishers.add(rdbtnPearson);
        publishers.add(rdbtnScholastic);
        publishers.add(rdbtnPengiun);
        publishers.add(rdbtnSimonandSchuster);

        rdbtnPearson.setActionCommand(Gui.Pearson);
        rdbtnPengiun.setActionCommand(Gui.Penguin);
        rdbtnScholastic.setActionCommand(Gui.Scholastic);
        rdbtnSimonandSchuster.setActionCommand(Gui.SandS);

        class SelectBookStores{
            private void deselectAll(){
                chckbxAmazon.setSelected(false);
                chckbxBarnsAndNoble.setSelected(false);
                chckbxBorders.setSelected(false);
                chckbxHalfPriceBooks.setSelected(false);
                chckbxPluBookStore.setSelected(false);
                chckbxPowells.setSelected(false);
                chckbxUpsBookStore.setSelected(false);
                chckbxUwBookStore.setSelected(false);
            }
            public void setPenguin(){
                deselectAll();
                chckbxBarnsAndNoble.setSelected(true);
                chckbxBorders.setSelected(true);
                chckbxHalfPriceBooks.setSelected(true);
                chckbxPowells.setSelected(true);
            }
            public void setScholastic() {
                deselectAll();
                chckbxAmazon.setSelected(true);
                chckbxBarnsAndNoble.setSelected(true);
                chckbxBorders.setSelected(true);
            }
            public void setPearson(){
                deselectAll();
                chckbxPluBookStore.setSelected(true);
                chckbxUpsBookStore.setSelected(true);
                chckbxUwBookStore.setSelected(true);
            }
            public void setSimonAndSchuster() {
                deselectAll();
                chckbxHalfPriceBooks.setSelected(true);
                chckbxPowells.setSelected(true);
                chckbxUwBookStore.setSelected(true);
            }
        }
        SelectBookStores BookStores = new SelectBookStores();
        rdbtnPearson.addActionListener(e -> {
            BookStores.setPearson();
        });
        rdbtnPengiun.addActionListener(e -> {
            BookStores.setPenguin();
        });
        rdbtnScholastic.addActionListener(e -> {
            BookStores.setScholastic();
        });
        rdbtnSimonandSchuster.addActionListener(e -> {
            BookStores.setSimonAndSchuster();
        });

    }

    /**
     * Adds the Bookstores to the GUI.
     */
    private void addBookStores() {

        // create greyed out checkBoxed
        chckbxBarnsAndNoble = new JCheckBox("Barns and Noble");
        chckbxBarnsAndNoble.setBounds(35, 580, 170, 29);
        chckbxBarnsAndNoble.setEnabled(false);
        chckbxBarnsAndNoble.setSelected(true);
        newBookFrame.getContentPane().add(chckbxBarnsAndNoble);

        chckbxAmazon = new JCheckBox("Amazon");
        chckbxAmazon.setBounds(35, 622, 139, 29);
        chckbxAmazon.setEnabled(false);
        newBookFrame.getContentPane().add(chckbxAmazon);

        chckbxBorders = new JCheckBox("Borders");
        chckbxBorders.setBounds(286, 580, 139, 29);
        chckbxBorders.setEnabled(false);
        chckbxBorders.setSelected(true);
        newBookFrame.getContentPane().add(chckbxBorders);

        chckbxHalfPriceBooks = new JCheckBox("Half Price Books");
        chckbxHalfPriceBooks.setBounds(286, 622, 183, 29);
        chckbxHalfPriceBooks.setEnabled(false);
        chckbxHalfPriceBooks.setSelected(true);
        newBookFrame.getContentPane().add(chckbxHalfPriceBooks);

        chckbxPowells = new JCheckBox("Powells");
        chckbxPowells.setBounds(539, 580, 139, 29);
        chckbxPowells.setEnabled(false);
        chckbxPowells.setSelected(true);
        newBookFrame.getContentPane().add(chckbxPowells);

        chckbxUwBookStore = new JCheckBox("UW Book Store");
        chckbxUwBookStore.setBounds(539, 622, 170, 29);
        chckbxUwBookStore.setEnabled(false);
        newBookFrame.getContentPane().add(chckbxUwBookStore);

        chckbxPluBookStore = new JCheckBox("PLU Book Store");
        chckbxPluBookStore.setBounds(781, 580, 183, 29);
        chckbxPluBookStore.setEnabled(false);
        newBookFrame.getContentPane().add(chckbxPluBookStore);

        chckbxUpsBookStore = new JCheckBox("UPS Book Store");
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
        newBookFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

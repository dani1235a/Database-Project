import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;

public class FindBook {

    private JFrame findFrame;
    private JTextField txtTitle;
    private JTextField txtAuthor;
    private JTextField txtIsbn;
    private JTextField txtPrice;
    private JTextField txtPublishingDate;
    private JTextField txtAuthorFirst;
    private JTextField txtInitials;
    private JTextField textField;
    private SQLConnection connection;

    /**
     * Create the application.
     */
    public FindBook(SQLConnection connection) {
        this.connection = connection;
        initialize();
        findFrame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        findFrame = new JFrame();
        findFrame.setTitle("Search By");
        findFrame.setBounds(100, 100, 1044, 822);
        findFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        findFrame.getContentPane().setLayout(null);
        findFrame.setResizable(false);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        findFrame.setLocation(dim.width / 2 - findFrame.getSize().width / 2,
                (int) (dim.getHeight() / 2 - findFrame.getSize().height / 2));
        findFrame.setVisible(true);
        JButton btnSubmit = new JButton("Search");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }
        });
        btnSubmit.setBounds(861, 709, 115, 29);
        findFrame.getContentPane().add(btnSubmit);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                initGui();
                findFrame.dispose();

            }
        });
        btnCancel.setBounds(79, 709, 115, 29);
        findFrame.getContentPane().add(btnCancel);

        txtTitle = new JTextField();
        txtTitle.setToolTipText("Please enter the Title of the book");
        txtTitle.setBounds(35, 34, 250, 29);
        findFrame.getContentPane().add(txtTitle);
        txtTitle.setColumns(10);

        txtAuthor = new JTextField();
        txtAuthor.setToolTipText("Last Name of Author");
        txtAuthor.setBounds(35, 107, 121, 29);
        findFrame.getContentPane().add(txtAuthor);
        txtAuthor.setColumns(10);

        txtIsbn = new JTextField();
        txtIsbn.setToolTipText("13 digit identifier");
        txtIsbn.setBounds(236, 181, 146, 26);
        findFrame.getContentPane().add(txtIsbn);
        txtIsbn.setColumns(10);

        txtPrice = new JTextField();
        txtPrice.setToolTipText("ex. 25.50");
        txtPrice.setBounds(35, 249, 146, 26);
        findFrame.getContentPane().add(txtPrice);
        txtPrice.setColumns(10);

        JLabel lblBookStoresThat = new JLabel("Stocked at:");
        lblBookStoresThat.setBounds(35, 491, 204, 34);
        findFrame.getContentPane().add(lblBookStoresThat);

        JCheckBox chckbxBarnsAndNoble = new JCheckBox("Barns and Noble");
        chckbxBarnsAndNoble.setBounds(35, 547, 170, 29);
        findFrame.getContentPane().add(chckbxBarnsAndNoble);

        JCheckBox chckbxAmazon = new JCheckBox("Amazon");
        chckbxAmazon.setBounds(35, 597, 139, 29);
        findFrame.getContentPane().add(chckbxAmazon);

        JCheckBox chckbxBorders = new JCheckBox("Borders");
        chckbxBorders.setBounds(284, 547, 139, 29);
        findFrame.getContentPane().add(chckbxBorders);

        JCheckBox chckbxHalfPriceBookvb = new JCheckBox("Half Price Books");
        chckbxHalfPriceBookvb.setBounds(284, 597, 183, 29);
        findFrame.getContentPane().add(chckbxHalfPriceBookvb);

        JCheckBox chckbxPowells = new JCheckBox("Powells");
        chckbxPowells.setBounds(539, 547, 139, 29);
        findFrame.getContentPane().add(chckbxPowells);

        JCheckBox chckbxUwBookStore = new JCheckBox("UW Book Store");
        chckbxUwBookStore.setBounds(539, 597, 170, 29);
        findFrame.getContentPane().add(chckbxUwBookStore);

        JCheckBox chckbxNewCheckBox = new JCheckBox("PLU Book Store");
        chckbxNewCheckBox.setBounds(781, 547, 183, 29);
        findFrame.getContentPane().add(chckbxNewCheckBox);

        JCheckBox chckbxUpsBookStore = new JCheckBox("UPS Book Store");
        chckbxUpsBookStore.setBounds(781, 597, 170, 29);
        findFrame.getContentPane().add(chckbxUpsBookStore);

        JLabel lblPublisher = new JLabel("Publisher:");
        lblPublisher.setBounds(35, 291, 115, 29);
        findFrame.getContentPane().add(lblPublisher);

        ButtonGroup publisher = new ButtonGroup();

        txtPublishingDate = new JTextField();
        txtPublishingDate.setToolTipText("mm/dd/yyyy");
        txtPublishingDate.setBounds(35, 181, 146, 26);
        findFrame.getContentPane().add(txtPublishingDate);
        txtPublishingDate.setColumns(10);

        txtAuthorFirst = new JTextField();
        txtAuthorFirst.setToolTipText("First Name of Author");
        txtAuthorFirst.setBounds(171, 108, 128, 26);
        findFrame.getContentPane().add(txtAuthorFirst);
        txtAuthorFirst.setColumns(10);

        txtInitials = new JTextField();
        txtInitials.setToolTipText("Initials, leave blank if none");
        txtInitials.setBounds(314, 108, 56, 26);
        findFrame.getContentPane().add(txtInitials);
        txtInitials.setColumns(10);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(35, 0, 69, 42);
        findFrame.getContentPane().add(lblTitle);

        JLabel lblAuthorLast = new JLabel("Author Last");
        lblAuthorLast.setBounds(35, 79, 97, 20);
        findFrame.getContentPane().add(lblAuthorLast);

        JLabel lblAuthorFirst = new JLabel("Author First");
        lblAuthorFirst.setBounds(171, 79, 115, 20);
        findFrame.getContentPane().add(lblAuthorFirst);

        JLabel lblInitials = new JLabel("Initials");
        lblInitials.setBounds(315, 79, 69, 20);
        findFrame.getContentPane().add(lblInitials);

        JLabel lblGenre = new JLabel("Genre:");
        lblGenre.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblGenre.setBounds(825, 31, 69, 20);
        findFrame.getContentPane().add(lblGenre);

        JCheckBox chckbxFiction = new JCheckBox("Fiction");
        chckbxFiction.setBounds(837, 70, 139, 29);
        findFrame.getContentPane().add(chckbxFiction);

        JCheckBox chckbxNonfiction = new JCheckBox("Non-Fiction");
        chckbxNonfiction.setBounds(837, 107, 139, 29);
        findFrame.getContentPane().add(chckbxNonfiction);

        JCheckBox chckbxHorror = new JCheckBox("Horror");
        chckbxHorror.setBounds(837, 143, 139, 29);
        findFrame.getContentPane().add(chckbxHorror);

        JCheckBox chckbxScififantacy = new JCheckBox("Sci-Fi/Fantacy");
        chckbxScififantacy.setBounds(837, 180, 139, 29);
        findFrame.getContentPane().add(chckbxScififantacy);

        JCheckBox chckbxBiography = new JCheckBox("Biography");
        chckbxBiography.setBounds(837, 217, 139, 29);
        findFrame.getContentPane().add(chckbxBiography);

        JCheckBox chckbxEducation = new JCheckBox("Education");
        chckbxEducation.setBounds(837, 254, 139, 29);
        findFrame.getContentPane().add(chckbxEducation);

        JCheckBox chckbxCooking = new JCheckBox("Cooking");
        chckbxCooking.setBounds(837, 291, 139, 29);
        findFrame.getContentPane().add(chckbxCooking);

        JCheckBox chckbxGraphicNovels = new JCheckBox("Graphic Novels");
        chckbxGraphicNovels.setBounds(837, 332, 139, 29);
        findFrame.getContentPane().add(chckbxGraphicNovels);

        JCheckBox chckbxPenguin = new JCheckBox("Penguin");
        chckbxPenguin.setBounds(35, 332, 139, 29);
        findFrame.getContentPane().add(chckbxPenguin);

        JCheckBox chckbxSchoolastic = new JCheckBox("Schoolastic");
        chckbxSchoolastic.setBounds(35, 369, 139, 29);
        findFrame.getContentPane().add(chckbxSchoolastic);

        JCheckBox chckbxPearson = new JCheckBox("Pearson");
        chckbxPearson.setBounds(35, 404, 139, 29);
        findFrame.getContentPane().add(chckbxPearson);

        JCheckBox chckbxSimonAndSchuster = new JCheckBox("Simon and Schuster");
        chckbxSimonAndSchuster.setBounds(35, 436, 183, 29);
        findFrame.getContentPane().add(chckbxSimonAndSchuster);

        JLabel lblPublishingDate = new JLabel("Publishing Date");
        lblPublishingDate.setBounds(35, 152, 110, 29);
        findFrame.getContentPane().add(lblPublishingDate);

        JLabel lblIsbn = new JLabel("ISBN");
        lblIsbn.setBounds(236, 152, 69, 20);
        findFrame.getContentPane().add(lblIsbn);

        JLabel lblListPrice = new JLabel("List Price");
        lblListPrice.setBounds(35, 223, 69, 20);
        findFrame.getContentPane().add(lblListPrice);

        JLabel lblKeyWords = new JLabel("Key Word");
        lblKeyWords.setBounds(236, 226, 146, 20);
        findFrame.getContentPane().add(lblKeyWords);

        textField = new JTextField();
        textField.setToolTipText("Search Book Description");
        textField.setBounds(236, 249, 146, 26);
        findFrame.getContentPane().add(textField);
        textField.setColumns(10);
    }

    protected void initGui() {
        // TODO Auto-generated method stub
        try {
            Gui initalWindow = new Gui();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

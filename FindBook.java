import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;

public class FindBook {
	// SQL connection
	private Connection connection;
	// JFrame
    private JFrame findFrame;
    //text fields in table
    private JTextField txtTitle;
    private JTextField txtAuthorF;
    private JTextField txtAuthorL;
    private JTextField txtAuthorI;
    private JTextField txtIsbn;
    private JTextField txtPrice;
    private JTextField txtPublishingDate;
    private JTextField textField;
    //check box fields
    private JCheckBox chckbxFiction;
    private JCheckBox chckbxNonfiction;
    private JCheckBox chckbxHorror;
    private JCheckBox chckbxScififantacy;
    private JCheckBox chckbxBiography;
    private JCheckBox chckbxEducation;
    private JCheckBox chckbxGraphicNovels;
    private JCheckBox chckbxPenguin;
    private JCheckBox chckbxSchoolastic;
    private JCheckBox chckbxPearson;
    private JCheckBox chckbxSimonAndSchuster;
    //JTextFieldBooleans
    private Boolean hasTitle = false;
    private Boolean hasAuthorF = false;
    private Boolean hasAuthorL = false;
    private Boolean hasAuthorI = false;
    private Boolean hasISBN = false;
    private Boolean hasPrice = false;
    private Boolean hasKeyword = false;
    private Boolean hasPublishingDate = false;
    //JCheckBoxBooleans
    private Boolean hasGenre = false;
    private Boolean hasPublisher = false;
    //
    private boolean multiCondition = false;
    /**
     * Create the application.
     */
    public FindBook(Connection connection) {
        this.connection = connection;
        setupFrame();
        addSubmitButton();
        addCancelButton();
        addTextFields();
        addLabels();
        addCheckBoxes();
    }

    /**
     * Setting up the Submit Button.
     */
    private void addSubmitButton() {
        JButton btnSubmit = new JButton("Search");
        btnSubmit.addActionListener(e ->  {
        	String sqlQuery = buildAQuery(); 
        	new Table(connection, sqlQuery);
            findFrame.dispose();
        	
        });
        btnSubmit.setBounds(500, 537, 115, 29);
        findFrame.getContentPane().add(btnSubmit);
    }

    /**
     * Add checkboxes to the frame.
     */
    private void addCheckBoxes() {

        chckbxFiction = new JCheckBox("Fiction");
        chckbxFiction.setBounds(500, 70, 139, 29);
        findFrame.getContentPane().add(chckbxFiction);

        chckbxNonfiction = new JCheckBox("Non-Fiction");
        chckbxNonfiction.setBounds(500, 107, 139, 29);
        findFrame.getContentPane().add(chckbxNonfiction);

        chckbxHorror = new JCheckBox("Horror");
        chckbxHorror.setBounds(500, 143, 139, 29);
        findFrame.getContentPane().add(chckbxHorror);

        chckbxScififantacy = new JCheckBox("Sci-Fi/Fantacy");
        chckbxScififantacy.setBounds(500, 180, 139, 29);
        findFrame.getContentPane().add(chckbxScififantacy);

        chckbxBiography = new JCheckBox("Biography");
        chckbxBiography.setBounds(500, 217, 139, 29);
        findFrame.getContentPane().add(chckbxBiography);

        chckbxEducation = new JCheckBox("Education");
        chckbxEducation.setBounds(500, 254, 139, 29);
        findFrame.getContentPane().add(chckbxEducation);

        chckbxGraphicNovels = new JCheckBox("Graphic Novels");
        chckbxGraphicNovels.setBounds(500, 291, 139, 29);
        findFrame.getContentPane().add(chckbxGraphicNovels);

        chckbxPenguin = new JCheckBox("Penguin");
        chckbxPenguin.setBounds(35, 332, 139, 29);
        findFrame.getContentPane().add(chckbxPenguin);

        chckbxSchoolastic = new JCheckBox("Schoolastic");
        chckbxSchoolastic.setBounds(35, 369, 139, 29);
        findFrame.getContentPane().add(chckbxSchoolastic);

        chckbxPearson = new JCheckBox("Pearson");
        chckbxPearson.setBounds(35, 404, 139, 29);
        findFrame.getContentPane().add(chckbxPearson);

        chckbxSimonAndSchuster = new JCheckBox("Simon and Schuster");
        chckbxSimonAndSchuster.setBounds(35, 436, 183, 29);
        findFrame.getContentPane().add(chckbxSimonAndSchuster);
    }


    /**
     * Putting all the labels in place.
     */
    private void addLabels() {
        JLabel lblPublisher = new JLabel("Publisher:");
        lblPublisher.setBounds(35, 291, 115, 29);
        findFrame.getContentPane().add(lblPublisher);

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
        lblGenre.setBounds(501, 34, 69, 20);
        findFrame.getContentPane().add(lblGenre);

        JLabel lblPublishingDate = new JLabel("Publishing Date");
        lblPublishingDate.setBounds(35, 152, 110, 29);
        findFrame.getContentPane().add(lblPublishingDate);

        JLabel lblIsbn = new JLabel("ISBN");
        lblIsbn.setBounds(236, 152, 69, 20);
        findFrame.getContentPane().add(lblIsbn);

        JLabel lblListPrice = new JLabel("List Price");
        lblListPrice.setBounds(35, 223, 69, 20);
        findFrame.getContentPane().add(lblListPrice);

        JLabel lblKeyWords = new JLabel("Key Word/Phrase from Description");
        lblKeyWords.setBounds(236, 226, 200, 20);
        findFrame.getContentPane().add(lblKeyWords);
    }

    /**
     * Setting up the Cancel Button.
     */
    private void addCancelButton() {
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(arg0 -> {
            new Gui();
            findFrame.dispose();

        });
        btnCancel.setBounds(35, 537, 115, 29);
        findFrame.getContentPane().add(btnCancel);
    }



    /**
     * Method for setting up the Find Book frame.
     */
    private void setupFrame() {
        findFrame = new JFrame();
        findFrame.setTitle("Search By");
        findFrame.setBounds(100, 100, 710, 682);
        findFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        findFrame.getContentPane().setLayout(null);
        findFrame.setResizable(false);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        findFrame.setLocation(dim.width / 2 - findFrame.getSize().width / 2,
                (int) (dim.getHeight() / 2 - findFrame.getSize().height / 2));
        findFrame.setVisible(true);
    }


    /**
     * Adding all of the text fields to the frame.
     */
    private void addTextFields() {
        txtTitle = new JTextField();
        txtTitle.setToolTipText("Please enter the Title of the book");
        txtTitle.setBounds(35, 34, 250, 29);
        findFrame.getContentPane().add(txtTitle);
        txtTitle.setColumns(10);

        txtAuthorL = new JTextField();
        txtAuthorL.setToolTipText("Last Name of Author");
        txtAuthorL.setBounds(35, 107, 121, 29);
        findFrame.getContentPane().add(txtAuthorL);
        txtAuthorL.setColumns(10);

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

        txtPublishingDate = new JTextField();
        txtPublishingDate.setToolTipText("mm/dd/yyyy");
        txtPublishingDate.setBounds(35, 181, 146, 26);
        findFrame.getContentPane().add(txtPublishingDate);
        txtPublishingDate.setColumns(10);

        txtAuthorF = new JTextField();
        txtAuthorF.setToolTipText("First Name of Author");
        txtAuthorF.setBounds(171, 108, 128, 26);
        findFrame.getContentPane().add(txtAuthorF);
        txtAuthorF.setColumns(10);

        txtAuthorI = new JTextField();
        txtAuthorI.setToolTipText("Initials, leave blank if none");
        txtAuthorI.setBounds(314, 108, 56, 26);
        findFrame.getContentPane().add(txtAuthorI);
        txtAuthorI.setColumns(10);

        textField = new JTextField();
        textField.setToolTipText("Search Book Description");
        textField.setBounds(236, 249, 201, 111);
        findFrame.getContentPane().add(textField);
        textField.setColumns(10);
    }
    
    private String buildAQuery(){
    	StringBuilder sqlQuery = new StringBuilder("select * from vDisplay");
    	//hasAuthorL = true;
    	//hasAuthorF = true;
        if(hasTitle || hasAuthorF || hasAuthorL || hasAuthorI || hasISBN || hasPrice || 
        		hasKeyword || hasPublishingDate || hasGenre || hasPublisher){
        	sqlQuery.append(" where");
        	if(hasTitle){
        		sqlQuery.append(" BookTitle = ");
        		sqlQuery.append("'" + txtTitle.getText() + "'");
        		multiCondition = true;
        	}
        	if(hasAuthorF){
        		if(multiCondition){
        			sqlQuery.append(" and");
        		}
        		sqlQuery.append(" FirstName = ");
        		sqlQuery.append("'" + txtAuthorF.getText() + "'");
        		multiCondition = true;
        	}
        	if(hasAuthorL){
        		if(multiCondition){
        			sqlQuery.append(" and");
        		}
        		sqlQuery.append(" LastName = ");
        		sqlQuery.append("'" + txtAuthorL.getText() + "'");
        		multiCondition = true;
        	}
        	if(hasAuthorI){
        		if(multiCondition){
        			sqlQuery.append(" and");
        		}
        		sqlQuery.append(" Initials = ");
        		sqlQuery.append("'" + txtAuthorI.getText() + "'");
        		multiCondition = true;
        	}
        	if(hasISBN){
        		if(multiCondition){
        			sqlQuery.append(" and");
        		}
        		sqlQuery.append(" ISBN =");
        		sqlQuery.append("'" + txtIsbn.getText() + "'");
        		multiCondition = true;
        	}
        	if(hasPrice){
        		if(multiCondition){
        			sqlQuery.append(" and");
        		}
        		sqlQuery.append(" listprice = ");
        		sqlQuery.append(txtPrice.getText());
        		multiCondition = true;
        	}
        	if(hasKeyword){
        		if(multiCondition){
        			sqlQuery.append(" and");
        		}
        		sqlQuery.append(" BookDescription like ");
        		sqlQuery.append("'%" + textField.getText() + "%'");
        		multiCondition = true;
        	}
        	if(hasPublishingDate){
        		if(multiCondition){
        			sqlQuery.append(" and");
        		}
        		sqlQuery.append(" ReleaseDate = ");
        		sqlQuery.append("'" + textField.getText() + "'");
        		multiCondition = true;
        	}
//-------------------------------------------------------------------------------------------------
        	if(hasGenre){
        		if(/*Checkbox*/){
        			if(multiCondition){
            			sqlQuery.append(" and (");
            			hasGenre = false; // a lazily recycled flag
            		} else {
            			sqlQuery.append(" (");
            			hasGenre = false; // a lazily recycled flag
            		}
            		sqlQuery.append(" Genre = ");
            		sqlQuery.append("'" + "/*String from Checkbox*/" + "'");
            		multiCondition = true;
        		}
        		if(/*Checkbox*/){
        			if(multiCondition && hasGenre){
            			sqlQuery.append(" and (");
            			hasGenre = false; // a lazily recycled flag
            		} else if(multiCondition && !hasGenre){
            			sqlQuery.append(" or");
            		} else {
            			sqlQuery.append(" (");
            			hasGenre = false; // a lazily recycled flag
            		}
            		sqlQuery.append(" Genre = ");
            		sqlQuery.append("'" + "/*String from Checkbox*/" + "'");
            		multiCondition = true;	
        		}
        		if(/*Checkbox*/){
        			if(multiCondition && hasGenre){
            			sqlQuery.append(" and (");
            			hasGenre = false; // a lazily recycled flag
            		} else if(multiCondition && !hasGenre){
            			sqlQuery.append(" or");
            		} else {
            			sqlQuery.append(" (");
            			hasGenre = false; // a lazily recycled flag
            		}
            		sqlQuery.append(" Genre = ");
            		sqlQuery.append("'" + "/*String from Checkbox*/" + "'");
            		multiCondition = true;	
        		}
        		if(/*Checkbox*/){
        			if(multiCondition && hasGenre){
            			sqlQuery.append(" and (");
            			hasGenre = false; // a lazily recycled flag
            		} else if(multiCondition && !hasGenre){
            			sqlQuery.append(" or");
            		} else {
            			sqlQuery.append(" (");
            			hasGenre = false; // a lazily recycled flag
            		}
            		sqlQuery.append(" Genre = ");
            		sqlQuery.append("'" + "/*String from Checkbox*/" + "'");
            		multiCondition = true;	
        		}
        		if(/*Checkbox*/){
        			if(multiCondition && hasGenre){
            			sqlQuery.append(" and (");
            			hasGenre = false; // a lazily recycled flag
            		} else if(multiCondition && !hasGenre){
            			sqlQuery.append(" or");
            		} else {
            			sqlQuery.append(" (");
            			hasGenre = false; // a lazily recycled flag
            		}
            		sqlQuery.append(" Genre = ");
            		sqlQuery.append("'" + "/*String from Checkbox*/" + "'");
            		multiCondition = true;	
        		}
        		if(/*Checkbox*/){
        			if(multiCondition && hasGenre){
            			sqlQuery.append(" and (");
            			hasGenre = false; // a lazily recycled flag
            		} else if(multiCondition && !hasGenre){
            			sqlQuery.append(" or");
            		} else {
            			sqlQuery.append(" (");
            			hasGenre = false; // a lazily recycled flag
            		}
            		sqlQuery.append(" Genre = ");
            		sqlQuery.append("'" + "/*String from Checkbox*/" + "'");
            		multiCondition = true;	
        		}
        		if(/*Checkbox*/){
        			if(multiCondition && hasGenre){
            			sqlQuery.append(" and (");
            			hasGenre = false; // a lazily recycled flag
            		} else if(multiCondition && !hasGenre){
            			sqlQuery.append(" or");
            		} else {
            			sqlQuery.append(" (");
            			hasGenre = false; // a lazily recycled flag
            		}
            		sqlQuery.append(" Genre = ");
            		sqlQuery.append("'" + "/*String from Checkbox*/" + "'");
            		multiCondition = true;	
        		}
        		sqlQuery.append(" )");
        	}
//---------------------------------------------------------------------------------------------------------
        	if(hasPublisher){
        		if(/*Checkbox*/){
        			if(multiCondition){
            			sqlQuery.append(" and (");
            			hasGenre = false; // a lazily recycled flag
            		} else {
            			sqlQuery.append(" (");
            			hasGenre = false; // a lazily recycled flag
            		}
            		sqlQuery.append(" Genre = ");
            		sqlQuery.append("'" + "/*String from Checkbox*/" + "'");
            		multiCondition = true;
        		}
        		if(/*Checkbox*/){
        			if(multiCondition && hasGenre){
            			sqlQuery.append(" and (");
            			hasGenre = false; // a lazily recycled flag
            		} else if(multiCondition && !hasGenre){
            			sqlQuery.append(" or");
            		} else {
            			sqlQuery.append(" (");
            			hasGenre = false; // a lazily recycled flag
            		}
            		sqlQuery.append(" Genre = ");
            		sqlQuery.append("'" + "/*String from Checkbox*/" + "'");
            		multiCondition = true;	
        		}
        		if(/*Checkbox*/){
        			if(multiCondition && hasGenre){
            			sqlQuery.append(" and (");
            			hasGenre = false; // a lazily recycled flag
            		} else if(multiCondition && !hasGenre){
            			sqlQuery.append(" or");
            		} else {
            			sqlQuery.append(" (");
            			hasGenre = false; // a lazily recycled flag
            		}
            		sqlQuery.append(" Genre = ");
            		sqlQuery.append("'" + "/*String from Checkbox*/" + "'");
            		multiCondition = true;	
        		}
        		if(/*Checkbox*/){
        			if(multiCondition && hasGenre){
            			sqlQuery.append(" and (");
            			hasGenre = false; // a lazily recycled flag
            		} else if(multiCondition && !hasGenre){
            			sqlQuery.append(" or");
            		} else {
            			sqlQuery.append(" (");
            			hasGenre = false; // a lazily recycled flag
            		}
            		sqlQuery.append(" Genre = ");
            		sqlQuery.append("'" + "/*String from Checkbox*/" + "'");
            		multiCondition = true;	
        		}
        		sqlQuery.append(" )");
        	}
//----------------------------------------------------------------------------------------------------------
        }
        return sqlQuery.toString();
    }
}

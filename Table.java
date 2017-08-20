import javax.swing.*;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class Table {
    private JFrame frame;
    private JFrame removeFrame;
    JTextField txtBookID;
    private Connection connection;


    public Table(Connection connection, String SQLQuery) {
        this.connection = connection;
        createFrame();
        addPanels(SQLQuery);
    }

    
    private void addPanels(String SQLQuery) {
        JPanel mainPanel = new JPanel();
        JPanel Display = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        Display.setLayout(new FlowLayout(FlowLayout.CENTER));
        Display.setSize(800, 400);
        addDoneButton(mainPanel);
        addRemoveButton(mainPanel);
        addAddBookButton(mainPanel);
        buildDisplay(Display, SQLQuery); 
        frame.add(mainPanel, BorderLayout.SOUTH);
        frame.add(Display, BorderLayout.CENTER);
    }
	
	
    private void addAddBookButton(JPanel mainPanel) {
        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(e -> {
            frame.dispose();
            new NewBook(connection);
        });
        mainPanel.add(addBookButton);
    }

    private void addRemoveButton(JPanel mainPanel) {
        JButton removeButton = new JButton("Remove Book");
        removeButton.addActionListener(e -> {
            createRemoveFrame();
            JPanel removeFieldPanel = new JPanel();
            JPanel removeButtonPanel = new JPanel();
            removeFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            removeButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            addRemoveTextField(removeFieldPanel);
            addDeleteButton(removeButtonPanel);
            addCancelButton(removeButtonPanel);
            removeFrame.add(removeButtonPanel, BorderLayout.SOUTH);
            removeFrame.add(removeFieldPanel, BorderLayout.CENTER);
        });
        mainPanel.add(removeButton);
    }

    private void addRemoveTextField(JPanel panel){
    	JLabel lblTitle = new JLabel("Book ID:");
        lblTitle.setBounds(35, 0, 75, 42);
        panel.add(lblTitle);
    	txtBookID = new JTextField();
    	txtBookID.setToolTipText("Enter Book ID to be removed");
    	txtBookID.setBounds(35, 107, 121, 29);
        panel.add(txtBookID);
        txtBookID.setColumns(10);
    }
    
    private void addCancelButton(JPanel mainPanel) {
        JButton doneButton = new JButton("Cancel");
        doneButton.addActionListener(e -> {
            removeFrame.dispose();
        });
        mainPanel.add(doneButton);
    }
    
    private void addDeleteButton(JPanel mainPanel) {
        JButton doneButton = new JButton("Delete");
        doneButton.addActionListener(e -> {
        	boolean illegal = false;
        	for(int i = 0; i < txtBookID.getText().length(); i++){
        		if( !('0' < txtBookID.getText().charAt(i) 
        				&& txtBookID.getText().charAt(i) < '9')){
        			illegal = true;
        		}
        	}
        	if(illegal){
        		//holler... or do nothing
        	} else {
        		int removeIndex = Integer.parseInt(txtBookID.getText());
        		String query = "Delete from Book where BookID = ?";
				try {
					PreparedStatement deleteSQL = connection.prepareStatement(query);
					deleteSQL.setInt(1, removeIndex);
					deleteSQL.execute();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	    frame.dispose();
        	    removeFrame.dispose();
        	    new Table(connection, null);
        	} 
        });
        mainPanel.add(doneButton);
    }
    
    private void addDoneButton(JPanel mainPanel) {
        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(e -> {
            frame.dispose();
            new Gui();
        });
        mainPanel.add(doneButton);
    }
    
    
    private void buildDisplay(JPanel DisplayPanel, String sqlCommand){
    	Vector columnNames = new Vector();
        Vector data = new Vector();
        String sql;
        if(sqlCommand == null){
        	sql = "select * from vDisplay";
        } else {
        	sql = sqlCommand;
        	System.out.println(sqlCommand + "\nPrint statement in Table.java \nBuilding Tables since 2017");
        	//sql = "select * from vDisplay where LastName = 'Boettcher'";
        }
        try{
		    Statement statement = connection.createStatement();
		    ResultSet resultSet = statement.executeQuery(sql);
		    ResultSetMetaData metaData = resultSet.getMetaData();
		    int columns = metaData.getColumnCount();
		    for (int i = 1; i <= columns; i++) {
		        columnNames.addElement(metaData.getColumnName(i));
		    }
		    
		    while (resultSet.next()) {
		        Vector row = new Vector(columns);
		        for (int i = 1; i <= columns; i++) {
		            row.addElement(resultSet.getObject(i));
		        }
		        data.addElement(row);
		    }
		    resultSet.close();
		    statement.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	    JTable table = new JTable(data, columnNames);
	    TableColumn column;
	    for (int i = 0; i < table.getColumnCount(); i++) {
	        column = table.getColumnModel().getColumn(i);
	        column.setMaxWidth(250);
	    }
    	JTable dataTable = new JTable(data, columnNames);
    	JScrollPane scrollPane = new JScrollPane(dataTable);
    	scrollPane.setSize(DisplayPanel.getSize());
    	DisplayPanel.add(scrollPane);
    	
    }


    private void createFrame() {
        frame = new JFrame();
        frame.setTitle("Book Database");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setResizable(true);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
                (int) (dim.getHeight() / 2 - frame.getSize().height / 2));
        frame.setVisible(true);
    }
    private void createRemoveFrame(){
    	removeFrame = new JFrame();
    	removeFrame.setTitle("Remove");
        removeFrame.setBounds(100, 100, 300, 200);
        removeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        removeFrame.setResizable(true);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        removeFrame.setLocation(dim.width / 2 - frame.getSize().width / 2,
                (int) (dim.getHeight() / 2 - frame.getSize().height / 2));
        removeFrame.setVisible(true);
    }
}

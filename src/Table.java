import javax.swing.*;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;


public class Table {
    private JFrame frame;
    private Connection connection;


    public Table(Connection connection) {
        this.connection = connection;
        createFrame();
        addPanel();
    }


    private void addPanel() {
        JPanel mainPanel = new JPanel();
        JPanel Display = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        Display.setLayout(new FlowLayout(FlowLayout.CENTER));
        addDoneButton(mainPanel);
        addRemoveButton(mainPanel);
        addAddBookButton(mainPanel);
        buildDisplay(Display);
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
            //TODO: create Popup asking for BOOKID
        });
        mainPanel.add(removeButton);
    }

    private void addDoneButton(JPanel mainPanel) {
        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(e -> {
            frame.dispose();
            new Gui();
        });
        mainPanel.add(doneButton);
    }
    
    private void buildDisplay(JPanel DisplayPanel){
    	Vector columnNames = new Vector();
        Vector data = new Vector();
        JPanel panel = new JPanel(); 
        String sql = "select FirstName, LastName from Person.person";
        Statement statement = conn.createStatement();
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
    }

    private void createFrame() {
        frame = new JFrame();
        frame.setTitle("Book Database");
        frame.setBounds(100, 100, 1044, 822);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setResizable(true);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
                (int) (dim.getHeight() / 2 - frame.getSize().height / 2));
        frame.setVisible(true);
    }
}

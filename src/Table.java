import javax.swing.*;
import java.awt.*;
import java.sql.Connection;


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
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        addDoneButton(mainPanel);
        addRemoveButton(mainPanel);
        addAddBookButton(mainPanel);
        frame.add(mainPanel, BorderLayout.SOUTH);

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

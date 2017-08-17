import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**
 * Class GUI is the main class. Starts off the GUI with Find or NewBook buttons.
 */
public class Gui {
    private SQLConnection sqlConnection;
    private Connection connection;
    private JFrame Startup;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(Gui::run);
    }

    /**
     * Create the application.
     * @throws SQLException
     */
    public Gui() throws SQLException {
        sqlConnection = new SQLConnection();
        sqlConnection.startConnection();
        connection = sqlConnection.getConnection();
        initialize();
        Startup.setVisible(true);
    }

    /**
     * This method runs the GUI Window.
     */
    private static void run() {
        try {
            Gui window = new Gui();
            JOptionPane.showMessageDialog(null, "Warning: This program is non-resizeable. \n"
                    + "If you have a hard time understanding the content please change your display resolution.");
            window.Startup.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setUpTheFrame();
        addNewBookButton();
        addFindButton();
    }

    /**
     * Add Find Button
     */
    private void addFindButton() {
        JButton btnLookUp = new JButton("Find");
        btnLookUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                FindBook find = new FindBook(connection);
                Startup.dispose();
            }
        });
        btnLookUp.setBounds(328, 102, 136, 52);
        Startup.getContentPane().add(btnLookUp);
    }

    /**
     * Add NewBookButton
     */
    private void addNewBookButton() {
        JButton btnNewBook = new JButton("New Book");
        btnNewBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // OPen new BOok Window

                NewBook book = new NewBook(connection);
                Startup.dispose();
            }
        });
        btnNewBook.setBounds(103, 102, 144, 52);
        Startup.getContentPane().add(btnNewBook);
    }

    /**
     * Set up the frame.
     */
    private void setUpTheFrame() {
        Startup = new JFrame();
        Startup.setTitle("Book Store Database");
        Startup.setBounds(100, 100, 613, 316);
        Startup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Startup.getContentPane().setLayout(null);
        Startup.setResizable(false);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Startup.setLocation(dim.width / 2 - Startup.getSize().width / 2,
                (int) (dim.getHeight() / 2 - Startup.getSize().height / 2));
    }

}

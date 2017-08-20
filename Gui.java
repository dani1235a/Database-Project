import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

/**
 * Class GUI is the main class. Starts off the GUI with Find or NewBook buttons.
 */
public class Gui {
    // global variables
	public final static String SandS = "Simon and Schuster";
    public final static String Scholastic = "Scholastic";
    public final static String Pearson = "Pearson";
    public final static String Penguin = "Penguin";
    //
    private final Connection connection;
    private JFrame Startup;

    /**
     * Create the application.
     */
    public Gui() {
        SQLConnection sqlConnection;
        sqlConnection = new SQLConnection();
        sqlConnection.startConnection();
        connection = sqlConnection.getConnection();
        initialize();
        Startup.setVisible(true);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(Gui::run);
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
        JButton btnLookUp = new JButton("Find A Book");
        btnLookUp.addActionListener(arg0 -> {
            new FindBook(connection);
            Startup.dispose();
        });
        btnLookUp.setBounds(334, 156, 136, 52);
        Startup.getContentPane().add(btnLookUp);
    }

    /**
     * Add NewBookButton
     */
    private void addNewBookButton() {
        JButton btnNewBook = new JButton("Add New Book");
        btnNewBook.addActionListener(arg0 -> {
            // OPen new BOok Window

            new NewBook(connection);
            Startup.dispose();
        });
        btnNewBook.setBounds(106, 156, 144, 52);
        Startup.getContentPane().add(btnNewBook);
    }

    /**
     * Set up the frame.
     */
    private void setUpTheFrame() {
        Startup = new JFrame();
        Startup.setTitle("Book Store Database");
        Startup.setBounds(100, 100, 613, 316);
        Startup.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Startup.getContentPane().setLayout(null);
        Startup.setResizable(false);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Startup.setLocation(dim.width / 2 - Startup.getSize().width / 2,
                (int) (dim.getHeight() / 2 - Startup.getSize().height / 2));


        JLabel lblWelcomeToThe = new JLabel("Welcome to the BookStore Database");
        lblWelcomeToThe.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 18));
        lblWelcomeToThe.setBounds(86, 16, 418, 34);
        Startup.getContentPane().add(lblWelcomeToThe);

        JLabel lblPleaseSelectAn = new JLabel("Please Select an option:");
        lblPleaseSelectAn.setBounds(86, 89, 293, 34);
        Startup.getContentPane().add(lblPleaseSelectAn);
    }

}

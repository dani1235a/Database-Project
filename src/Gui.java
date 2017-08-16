import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Gui {

    private JFrame Startup;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Gui window = new Gui();
                    JOptionPane.showMessageDialog(null, "Warning: This program is non-resizeable. \n"
                            + "If you have a hard time understanding the content please change your display resolution.");
                    window.Startup.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     * @throws SQLException
     */
    public Gui() throws SQLException {
        initialize();
        Startup.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        Startup = new JFrame();
        Startup.setTitle("Book Store Database");
        Startup.setBounds(100, 100, 613, 316);
        Startup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Startup.getContentPane().setLayout(null);
        Startup.setResizable(false);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Startup.setLocation(dim.width / 2 - Startup.getSize().width / 2,
                (int) (dim.getHeight() / 2 - Startup.getSize().height / 2));


        JButton btnNewBook = new JButton("New Book");
        btnNewBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // OPen new BOok Window
                NewBook();
                Startup.dispose();
            }
        });
        btnNewBook.setBounds(103, 102, 144, 52);
        Startup.getContentPane().add(btnNewBook);

        JButton btnLookUp = new JButton("Find");
        btnLookUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                FindButton();
                Startup.dispose();
            }
        });
        btnLookUp.setBounds(328, 102, 136, 52);
        Startup.getContentPane().add(btnLookUp);
    }

    protected void FindButton() {
        //Find window
        FindBook find = new FindBook();
    }

    protected void NewBook() {
        // pass in the parameters
        NewBook book = new NewBook();

    }
}

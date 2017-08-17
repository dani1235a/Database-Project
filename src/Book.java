import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class representation of a book.
 */
public class Book {
    //SQL connection to add book to database
    private SQLConnection SQLClassConnection;
    private Connection connection;

    // Book essentials
    private String title;
    private String authorFirst;
    private String authorLast;
    private String authorInit;
    private String publisher;
    private ArrayList<String> bookstores;
    private String listPrice;
    private String bookDescription;
    private String ISBN;
    private String date;
    private String Genre;
    private int GenreID;
    private java.sql.Date publishDate;
    private int PublisherID;
    private float ListPrice;
    private int AuthorID;

    /**
     * Book constructor.
     * @param Title
     * @param AuthorFirst
     * @param AuthorLast
     * @param Publisher
     * @param ISBN
     */
    public Book(String Title, String AuthorFirst, String AuthorLast,
                String Publisher, String ISBN) {
        this.ISBN = ISBN;
        this.publisher = Publisher;
        this.title = Title;
        this.authorFirst = AuthorFirst;
        this.authorLast = AuthorLast;

        if (publisher.equals("Pengiun")) {
            PublisherID = 1;
        } else if (publisher.equals("Schoolastic")) {
            PublisherID = 2;
        } else if (publisher.equals("Pearson")) {
            PublisherID = 3;
        } else if (publisher.equals("Simon and Schuster")) {
            PublisherID = 5;
        }

        getAuthorID();

    }

    private void getAuthorID() {
        //TODO: 1) search for author id with first name, and last name - if found, assign to author ID
        //TODO: 2) if not found, create a new Author with given first and last name and then repeat process to get ID
        //TODO: worry about inital in author name
        String search = "SELECT AuthorID from Author where FirstName = ? and LastName = ?";
        try {
            PreparedStatement searchSQL = connection.prepareStatement(search);
            searchSQL.setString(1, authorFirst);
            searchSQL.setString(2, authorLast);

            ResultSet result = searchSQL.executeQuery();

            if (!result.next()) {
                String addAuthor = "insert into Author(FirstName, LastName, Initials) " +
                        "values(?,?,?)";
                PreparedStatement addAnAuthor = connection.prepareStatement(addAuthor);
                addAnAuthor.setString(1, authorFirst);
                addAnAuthor.setString(2, authorLast);
                addAnAuthor.setString(3, authorInit);

                ResultSet result2 = searchSQL.executeQuery();
                AuthorID = result2.getInt("AuthorID");
            } else {
                AuthorID = result.getInt("AuthorID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the authorFirst
     */
    public String getAuthorFirst() {
        return authorFirst;
    }

    /**
     * @param authorFirst the authorFirst to set
     */
    public void setAuthorFirst(String authorFirst) {
        this.authorFirst = authorFirst;
    }

    /**
     * @return the authorLast
     */
    public String getAuthorLast() {
        return authorLast;
    }

    /**
     * @param authorLast the authorLast to set
     */
    public void setAuthorLast(String authorLast) {
        this.authorLast = authorLast;
    }

    /**
     * @return the authorInit
     */
    public String getAuthorInit() {
        return authorInit;
    }

    /**
     * @param authorInit the authorInit to set
     */
    void setAuthorInit(String authorInit) {
        this.authorInit = authorInit;
    }

    /**
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher the publisher to set
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @return the bookstores
     */
    public ArrayList<String> getBookstores() {
        return bookstores;
    }

    /**
     * @param bookstores the bookstores to set
     */
    public void setBookstores(ArrayList<String> bookstores) {
        this.bookstores = bookstores;
    }

    /**
     * @return the listPrice
     */
    public String getListPrice() {
        return listPrice;
    }

    /**
     * @param listPrice the listPrice to set
     */
    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
        ListPrice = Float.parseFloat(listPrice);
    }

    /**
     * @return the bookDescription
     */
    public String getBookDescription() {
        return bookDescription;
    }

    /**
     * @param bookDescription the bookDescription to set
     */
    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    /**
     * @return the iSBN
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * @param iSBN the iSBN to set
     */
    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
        //Calendar calendar = Calendar.getInstance();
        //java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date publishDates = df.parse(date);
            publishDate = new java.sql.Date(publishDates.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /**
     * get Genre
     * @return String Genre
     */
    public String getGenre() {
        return Genre;
    }

    /**
     * Set Genre
     * @param genre - genre to be set
     */
    public void setGenre(String genre) {
        Genre = genre;
        if (Genre.equals("Fiction")) {
            GenreID = 1;
        } else if (Genre.equals("Non-Fiction")) {
            GenreID = 2;
        } else if (Genre.equals("Sci-Fi/Fantacy")) {
            GenreID = 4;
        } else if (Genre.equals("Horror")) {
            GenreID = 3;
        } else if (Genre.equals("Comics/Graphic Novel")) {
            GenreID = 7;
        } else if (Genre.equals("Education")) {
            GenreID = 6;
        } else if (Genre.equals("Biography")) {
            GenreID = 5;
        }
    }


    /**
     * Sets the SQL connection with the book.
     * @param connectionToClass
     */
    public void setConnection(SQLConnection connectionToClass) {
        this.SQLClassConnection = connectionToClass;
        connection = SQLClassConnection.getConnection();
    }

    /**
     * adds the book to the database.
     */
    public void addToBookStoreDatabase() {
        if (connection == null) {
            JOptionPane.showMessageDialog(null, "Warning: SQL Connection not Established.");
        }
        String query = "insert into Book (BookTitle, ISBN, GenreID, ReleaseDate, PublisherID, ListPrice, AuthorID)"
                 + "values(?,?,?,?,?,?,?)";

        try {
            PreparedStatement s = connection.prepareStatement(query);
            s.setString(1, title);
            s.setString(2, ISBN);
            s.setInt(3, GenreID);
            s.setDate(4, publishDate);
            s.setInt(5, PublisherID);
            s.setFloat(6, ListPrice);
            s.setInt(7, AuthorID);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

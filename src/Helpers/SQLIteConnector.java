package Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import source.Article;

public class SQLIteConnector {

    private Connection          connection = null;
    private Statement           stmt       = null;
    private static final String INSERT_SQL = "INSERT INTO Article (TITLE, URL, IMG_URL) values(?,?,?)";

    public void init() {

        try {
            // Open connection
            connection = openDB();
            // Create the table
            stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Article "
                    + "(ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE," 
                    + " TITLE          TEXT     NOT NULL, "
                    + " URL            TEXT     NOT NULL, " 
                    + " IMG_URL        TEXT)";

            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
            System.out.println("Table created successfully");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Inserts an article into the article table.
     * 
     * @param article
     */
    public void InsertArticle(Article article) {
        PreparedStatement preparedStatement = null;
        connection = null;
        try {
            connection = openDB();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(INSERT_SQL);
            preparedStatement.setString(1, article.getmTitle());
            preparedStatement.setString(2, article.getmUrl());
            preparedStatement.setString(3, article.getmImgUrl());

            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            System.out.println("Could not insert into table: " + e.getMessage());
        } finally {
            closeDBConnection(preparedStatement, connection);
        }
    }

    /**
     * Gets an instance of the connector, Opens the database.
     * 
     * @return Connection
     */
    public Connection openDB() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            connection.setAutoCommit(false);
            System.out.println("Opened database successfully");

        } catch (SQLException e) {
            System.out.println("Could not insert into table: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Closes the database connection
     * 
     * @param statement
     */
    private void closeDBConnection(Statement statement, Connection connection) {
        try {
            if (statement != null) {
                statement.close();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

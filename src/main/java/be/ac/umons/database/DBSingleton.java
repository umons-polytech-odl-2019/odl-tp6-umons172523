package be.ac.umons.database;

import be.ac.umons.util.AnsiColor;
import be.ac.umons.util.ColorPrint;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;


public class DBSingleton {

    private static DBSingleton instance = null;

    private Connection connection;

    /**
     * Connection to database with configuration from database.properties file.
     */
    private DBSingleton() {
        try (InputStream stream = Files.newInputStream(Paths.get("database.properties"))) {

            Properties prop = new Properties();

            prop.load(stream);

            String url = prop.getProperty("db.url");
            String username = prop.getProperty("db.user");
            String password = prop.getProperty("db.pass");

            System.out.println("JDBC URL : " + url);
            System.out.println("Username : " + username);

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException | ClassNotFoundException e) {
                ColorPrint.printError(e.getMessage());
                //e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Connection to database with configuration as parameters.
     * @param url jdbc:mysql://localhost:3306/database
     * @param username the user
     * @param password the password
     * @throws NullPointerException if one of the arguments is null
     */
    private DBSingleton(String url, String username, String password) throws NullPointerException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            ColorPrint.printError(e.getMessage());
        }
    }

    /**
     * Get singleton object. If it does not exists, configuration is loaded from database.properties file.
     * @return singleton
     */
    public static DBSingleton getSingleton() {
        if (instance == null) {
            instance = new DBSingleton();
        }
        return instance;
    }

    /**
     * Get singleton with parameters passed as arguments.
     * @param url JDBC URL to a database server
     * @param username Database user
     * @param password The password
     * @return the singleton
     */
    public static DBSingleton getSingleton(String url, String username, String password) {
        if (instance == null)
        {
            instance = new DBSingleton(url, username, password);
        }
        return instance;
    }

    /**
     * Execute a SQL query
     * @param query SQL query
     * @return a ResultSet
     */
    public ResultSet querySelect(String query) {
        ResultSet result = null;
        if (connection != null) {
            if (query != null && !query.isEmpty()) {
                try {
                    Statement stmt = connection.createStatement();
                    result = stmt.executeQuery(query);

                } catch (SQLException e) {
                    ColorPrint.printError(e.getMessage());
                }
            }
        }
        return result;
    }

    public int queryUpdate(String query, Object... args) {
        int result = -1;
        String temp = null;

        if (connection != null) {
            if (query != null && !query.isEmpty()) {
                try {

                    temp = (args != null && args.length != 0) ? String.format(query, args) : query;

                    ColorPrint.printDebug("DEBUG update stmt : " + temp);

                    Statement stmt = connection.createStatement();

                    result = stmt.executeUpdate(temp);
                    stmt.close();
                } catch (SQLException e) {
                    ColorPrint.printError(e.getMessage());
                    return -1;
                }
            }
        }
        return result;
    }

    /**
     * Close connection to the database.
     */
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

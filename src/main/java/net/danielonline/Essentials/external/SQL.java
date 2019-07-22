package net.danielonline.Essentials.external;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {

    public Connection connection;
    private String host, database, username, password;
    private int port;
    public Statement statement;

    public void enable() {

        host = "116.203.95.196";
        port = 3306;
        database = "dockerconnect";
        username = "dockerconnect";
        password = "chiicken";

    }

    public void openConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            return;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host+ ":" + this.port + "/" + this.database, this.username, this.password);
        }
    }

}

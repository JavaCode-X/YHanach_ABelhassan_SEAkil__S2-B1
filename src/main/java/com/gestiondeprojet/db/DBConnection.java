package com.gestiondeprojet.db;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection = null;

    private DBConnection() {
        try {
        	Dotenv dotenv = Dotenv.configure().load();
        	String host = System.getenv("DB_HOST");
        	String port = System.getenv("DB_PORT");
        	String db = System.getenv("DB_NAME");
        	String user = System.getenv("DB_USER");
        	String password = System.getenv("DB_PASSWORD");
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?useSSL=false&serverTimezone=UTC";
        	connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
    	System.out.println("Working Directory = " + System.getProperty("user.dir"));
        DBConnection.getInstance().getConnection();
    }
}

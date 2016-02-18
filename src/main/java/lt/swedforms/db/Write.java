package lt.swedforms.db;//STEP 1. Import required packages

import java.sql.*;
import java.util.HashSet;

public class Write {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://sql2.freemysqlhosting.net/sql2107174";

    //  Database credentials
    static final String USER = "sql2107174";
    static final String PASS = "pT2!gA6*";

    public static boolean newUserRegistration(String mail, String password){
        Connection conn = null;
        Statement stmt = null;
        Boolean result = true;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Connected database successfully...");

            //STEP 3.5 check for dublicates

            stmt = conn.createStatement();
            String sql2 = "SELECT * FROM `users` WHERE 1";
            ResultSet rs = stmt.executeQuery(sql2);

            HashSet hs = new HashSet();

            if (rs.next() != false) {
                while (rs.next()) {
                    //Retrieve by column name
                    String id = rs.getString("mail");
                    hs.add(id);
                }
            } else {
                //System.out.println("----------Entry does not exist");
                result = true;

            }

            //STEP 4: Insert records

            if (hs.contains(mail)) {
                //System.out.println("----------Entry allrady exists");
                result = false;
            } else {
                //System.out.println("Inserting records into the table...");
                stmt = conn.createStatement();

                String sql = "INSERT INTO users (mail, password) VALUES (" + " '" +
                        mail + "' , " + " '" + password + "');";
                //System.out.println(sql);
                stmt.executeUpdate(sql);

                //System.out.println("Inserted records into the table...");
                result = true;
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            return result;//end finally try
        }//end try
        //System.out.println("DONE");
    }//end main

    public static void newRegistration(String firstname, String lastname, String phone, String mail, String address, String date, String time, String topic, String comment) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 3.5 check for dublicates

            stmt = conn.createStatement();
            String sql2 = "SELECT * FROM `Registrations` WHERE 1";
            ResultSet rs = stmt.executeQuery(sql2);

            HashSet hs = new HashSet();

            //STEP 4: Insert records

            System.out.println("Inserting records into the table...");
            stmt = conn.createStatement();

            String sql = "INSERT INTO Registrations (mail, Dates, Address, Topic, FirstName, LastName, Phone, Time, Comment) VALUES (" + " '" +
                    mail + "', '"+date+"', '"+address+"', '"+topic+"', '"+firstname+"', '"+lastname+"', '"+phone+"', '"+time+"', '"+comment+"' );";
            System.out.println(sql);
            stmt.executeUpdate(sql);

            System.out.println("----------New registration added");

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("DONE");
    }//end main

    public static void UpdateRandom(String email, String ip, String userIdentification) {
    }
}//end JDBCExample
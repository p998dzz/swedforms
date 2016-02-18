package lt.swedforms.db;//STEP 1. Import required packages

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class Read {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL =
            "jdbc:mysql://sql2.freemysqlhosting.net/sql2107174";

    //  Database credentials
    static final String USER = "sql2107174";
    static final String PASS = "pT2!gA6*";

    public static void main() {
        Connection conn = null;
        Statement stmt = null;
        HashSet hs = null;
        TreeSet ts = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection Sucesfull");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM `users` WHERE 1";
            ResultSet rs = stmt.executeQuery(sql);

            hs = new HashSet();

            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String id = rs.getString("mail");
                hs.add(id);
                String pass = rs.getString("password");

                //Display values
                System.out.println("----------ID: " + id);
                System.out.println("----------password: " + pass);

            }
            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
            System.out.println("fail1");
            System.out.println(se.getMessage());
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
                System.out.println("fail4");
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                System.out.println("fail3");
            }//end finally try
        }//end try
        System.out.println(hs);
    }//end main
}
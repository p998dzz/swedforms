package lt.swedforms.db;//STEP 1. Import required packages


import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class Check {
    // JDBC driver name and database URL
    // openshift address
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL =
            "jdbc:mysql://sql2.freemysqlhosting.net/sql2107174";

    //  Database credentials
    static final String USER = "sql2107174";
    static final String PASS = "pT2!gA6*";

    public static String checkPassword(String mail) {
        Connection conn = null;
        Statement stmt = null;
        HashSet hs = null;
        TreeSet ts = null;
        String pass = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to database...");
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Connection Sucesfull");

            //STEP 4: Execute a query
            //System.out.println("Creating statement...");
            stmt = (Statement) conn.createStatement();

            String sql = "SELECT * FROM `users` WHERE mail='" + mail + "';";
            ResultSet rs = stmt.executeQuery(sql);
            //System.out.println(rs.next());

            //STEP 5: Extract data from result set

            while (rs.next()) {

                pass = rs.getString("password");

                //System.out.println("----------"+pass);
            }

            rs.close();
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
            return pass;//return null;//end finally try
        }//end try
    }//end main

    public static String checkRand(String mail) {
        Connection conn = null;
        Statement stmt = null;
        HashSet hs = null;
        TreeSet ts = null;
        String pass = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to database...");
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Connection Sucesfull");

            //STEP 4: Execute a query
            //System.out.println("Creating statement...");
            stmt = (Statement) conn.createStatement();

            String sql = "SELECT * FROM `users` WHERE mail='" + mail + "';";
            ResultSet rs = stmt.executeQuery(sql);
            //System.out.println(rs.next());

            //STEP 5: Extract data from result set

            while (rs.next()) {

                pass = rs.getString("Rand");

                //System.out.println("----------"+pass);
            }

            rs.close();
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
            return pass;//return null;//end finally try
        }//end try
    }//end main
    public static ArrayList checkRegistrations(String mail) {
        Connection conn = null;
        Statement stmt = null;
        HashSet hs = null;
        TreeSet ts = null;
        ArrayList testArray = new ArrayList();

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to database...");
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Connection Sucesfull");

            //STEP 4: Execute a query
            //System.out.println("Creating statement...");
            stmt = (Statement) conn.createStatement();

            String sql = "SELECT * FROM `Registrations` WHERE mail='" + mail + "';";
            ResultSet rs = stmt.executeQuery(sql);
            //System.out.println(rs.next());

            //STEP 5: Extract data from result set

            testArray.clear();

            System.out.println("----------All registrations of "+mail+":");
            while (rs.next()) {

                String tempFirstname = rs.getString("FirstName");
                String tempLastname = rs.getString("LastName");
                String tempPhone = rs.getString("Phone");
                String tempAddress = rs.getString("Address");
                String tempDate = rs.getString("Dates");
                String tempTime = rs.getString("Time");
                String tempTopic = rs.getString("Topic");
                String tempCOmment = rs.getString("Comment");

                testArray.add(tempFirstname);
                testArray.add(tempLastname);
                testArray.add(tempPhone);
                testArray.add(tempAddress);
                testArray.add(tempDate);
                testArray.add(tempTime);
                testArray.add(tempTopic);
                testArray.add(tempCOmment);

            }

            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
            //e.getMessage();
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
            return testArray;//end finally try
        }
        //end try
    }//end main
}//end JDBCExample
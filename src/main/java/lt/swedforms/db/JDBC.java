package lt.swedforms.db;//STEP 1. Import required packages
import org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer;

import java.sql.*;



public class JDBC {

    public static void main(String[] args) throws SQLException {

/**
        //Read full usersdatabase
        Read testN = new Read();
        testN.main();
*/
        //Add new non-dublicate user to db
        Write testW = new Write();
        System.out.println(testW.newUserRegistration("mail@mail.com", "password"));

        //Write new registration for specific user (dublicates allowed)
        /**Write testW2 = new Write();
        testW2.newRegistration("firstname", "lastname", "phone", "mail@mail.com", "address", "date", "time", "topic", "comment");*/

        //Check all registrations of specific user
        Check testC2 = new Check();
        System.out.println(testC2.checkRegistrations("mail@mail.com"));


        Check testC = new Check();
        System.out.println(testC.checkPassword("mail@mail.com"));


    }
}
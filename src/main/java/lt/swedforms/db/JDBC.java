package lt.swedforms.db;//STEP 1. Import required packages

import org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer;

import java.sql.*;


public class JDBC {

    public static void main(String[] args) throws SQLException {


        //Add new non-dublicate user to db (String email, String password)
        Write testNEWUSER = new Write();
        System.out.println(testNEWUSER.newUserRegistration("mail@mail.com", "password"));

        //Return password of any user based on mail
        Check testCHECKPASSWORD = new Check();
        System.out.println(testCHECKPASSWORD.checkPassword("mail@mail.com"));

        //Write new random number to specific user (String mail, int rand)
        Write testR = new Write();
        System.out.println(testR.newRand("mail@mail.com", 751));

        //Return random number for specific user (String mail)
        Check testCHECKRAND = new Check();
        System.out.println(testCHECKRAND.checkRand("mail@mail.com"));

        //Write new full-registration for specific user (dublicates allowed, only Strings)
        Write testW2 = new Write();
        testW2.newRegistration("Vardenis", "Pavardenis", "+370112", "mail@mail.com", "Perkunkiemio 12", "2016-03-01", "15:00", "Turto draudimas", "Gali veluoti");

        //Return all registrations of specific user (String mail)
        Check testC2 = new Check();
        System.out.println(testC2.checkRegistrations("mail@mail.com"));

        /** TURI SPAUSDINTI:

         false                                       <<< toks vartotojas jau buvo / true jeigu ok
         password                                   <<< to vartotojo slaptazodis "password"
         true                                        <<< sekmingai iterpe nauja random
         751                                        <<< vartotojo rand skaicius
         ----------New registration added            <<< registracija itraukta
         ----------All registrations of mail@mail.com:
         [firstname, lastname, phone, address, date, time, topic, comment]      <<< visos vartotojo registracijos vienam masyve
         */

    }
}
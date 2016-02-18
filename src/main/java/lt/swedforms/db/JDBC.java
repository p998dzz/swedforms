package lt.swedforms.db;//STEP 1. Import required packages
public class JDBC {

    public static void main(String[] args)  {

/*
        //Read full usersdatabase
        Read testN = new Read();
        testN.main();
*/
        //Add new non-dublicate user to db
        Write testW = new Write();
        System.out.println(testW.newUserRegistration("subine2@gmail.com", "123456-9"));

        //Write new registration for specific user (dublicates allowed)
        //Write testW2 = new Write();
        //testW2.newRegistration("subine@gmail.com", "2016-04-02", "Antakalnio g. 45", "Subines draudimas");

        //Check all registrations of specific user
        Check testC2 = new Check();
        System.out.println(testC2.checkRegistrations("swedbank@gmail.com"));
        System.out.println(testW.newUserRegistration("testas", "testas"));


        Check testC = new Check();
        System.out.println(testC.checkPassword("subine2@gmail.com"));


    }
}
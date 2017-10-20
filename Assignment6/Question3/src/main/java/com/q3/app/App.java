package com.q3.app;

//Hi Harsha,
//
//Please note: I use Maven under intelliJ to build this project as I need to use the
//jackson dependency to write json file. So please test run the App in intelliJ. All
//the user data would be stored in file named user_data.json. Sorry for the inconvenience.



public class App 
{
    public static void main( String[] args )
    {
        ATM test = new ATM(100000, 2);
        test.init();
    }
}

/*
Awesome work Score 9.9 + extra credit 2; Total score 10
*/
package com.q3.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ATM {
    private double availableAmountInMachine;
    private double transactionFee;
    private Map<String, User> userData;
    private int count;
    private ObjectMapper mapper = new ObjectMapper();

    public ATM(double amount, double fee) {
        setAvailableAmountInMachine(amount);
        setTransactionFee(fee);
        setUserData(new HashMap<String, User>());
        count = 0;
        initReadFile();
    }

    public void initReadFile() {
        try {
            Map<String, User> userData = mapper.readValue(new File("user_data.json"), new TypeReference<HashMap<String, User>>() { } );
            setUserData(userData);
            for(String s : userData.keySet()) {
                count++;
            }
        } catch(IOException e) {
        }

    }

    public void jacksonWrite() {
        try {
            mapper.writeValue(new File("user_data.json"), userData);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        cleanConsole();
        System.out.println("Welcome to use ATM.");
        System.out.println("1. New User Sign Up");
        System.out.println("2. Existed User Login");

        String ans = getInput(new String[]{"1", "2"});
        if (ans.equals("1")) {
            createUser();
        }
        if (ans.equals("2")) {
            login();
        }


    }

    public void cleanConsole() {
        // NOTE: these two line clean the console. This works on
        //  almost all UNIX terminals and terminal emulators. The
        //   Windows cmd.exe does not interprete ANSI escape codes.
        // So please run this program in bash if you use Windows.
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public String getInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public String getInput(String[] arr) {
        boolean legalInput = true;
        String check = "";
        while (legalInput) {
            Scanner input = new Scanner(System.in);
            check = input.nextLine();
            for (String a : arr) {
                if (check.equals(a)) {
                    legalInput = false;
                }
            }
            if (legalInput) {
                System.out.println("Illegal input! Please try again:");
            }
        }

        return check;
    }


    public void createUser() {
        cleanConsole();
        System.out.println("Input your name:");
        String name = getInput();
        boolean passwordConfirmed = false;
        String password = "";
        while (!passwordConfirmed) {
            cleanConsole();
            System.out.println("Input your password:");
            password = getInput();
            System.out.println("Confirm your password:");
            if (password.equals(getInput())) {
                passwordConfirmed = true;
            } else {
                cleanConsole();
                System.out.println("Your input is not matched with last time, please input your password again.");
                pressKeyToContinue("set password");
            }
        }

        cleanConsole();
        System.out.println("Input your age: ");
        String age = getInput();

        cleanConsole();
        System.out.println("Input your phone number: ");
        String phoneNumber = getInput();

        cleanConsole();
        System.out.println("Input your address: ");
        String address = getInput();

        String accountNumber = "451720173223";
        String count = Integer.toString(this.count);
        this.count++;
        for (int i = 0; i < 4 - count.length(); i++) {
            count = "0".concat(count);
        }
        accountNumber = accountNumber.concat(count);

        cleanConsole();
        System.out.println("Your account number is " + accountNumber);
        System.out.println("How much would you like to deposit: ");
        Double balance = Double.parseDouble(getInput());
        ArrayList<String> transactions = new ArrayList<String>();
        String trans = "Make deposit $" + Double.toString(balance) + getTimeStamp();
        System.out.println(trans);
        transactions.add(trans);

        pressKeyToContinue("to continue.");


        User newUser = new User(name, age, address, phoneNumber, password, accountNumber, balance, transactions);

        userData.put(phoneNumber, newUser);
        jacksonWrite();

        loginSucceed(phoneNumber);

    }

    public void login() {
        cleanConsole();
        System.out.println("====== Login ======");
        System.out.println("Input your phone number: ");

        String phoneNumber = getInput();
        if (!userData.containsKey(phoneNumber)) {
            cleanConsole();
            System.out.println("Account doesn't exist.");
            System.out.println("1. Create new account");
            System.out.println("2. Back to login");
            String input = getInput(new String[]{"1", "2"});
            if (input.equals("1")) {
                createUser();
            } else {
                login();
            }
        }

        if (enterPassword(phoneNumber)) {
            loginSucceed(phoneNumber);
        }

    }

    public void loginSucceed(String phoneNumber ) {
        cleanConsole();
        System.out.println("1. Check balance;");
        System.out.println("2. Withdraw cash;");
        System.out.println("3. Deposit;");
        System.out.println("4. Recent transactions;");
        System.out.println("5. Change password;");
        System.out.println("6. Exit;");

        String ans = getInput(new String[]{"1", "2", "3", "4", "5", "6"});
        if (ans.equals("1")) {
            cleanConsole();
            System.out.println("Available balance: " + getBalance(phoneNumber));
            System.out.println("Enter any key to go back");
            getInput();
            loginSucceed(phoneNumber);
        } else if (ans.equals("2")) {
            cleanConsole();
            System.out.println("Input the amount you'd like to withdraw ($" + transactionFee +" transaction fee will be charged): ");
            String amount = getInput();
            String b = withdraw(phoneNumber, amount);
            pressKeyToContinue("main menu.");
            loginSucceed(phoneNumber);
        } else if (ans.equals("3")) {
            cleanConsole();
            System.out.println("Input the amount you'd like to deposit: ");
            String amount = getInput();
            String b = deposit(phoneNumber, amount);
            System.out.println("Deposit succeed! You current available balance: " + b);
            pressKeyToContinue("main menu.");
            loginSucceed(phoneNumber);
        } else if (ans.equals("4")) {
            cleanConsole();
            System.out.println("Below is your last 10 transactions: ");
            User temp = userData.get(phoneNumber);
            ArrayList<String> transactions = temp.getTransactions();
            if (transactions.size() == 0) {
                System.out.println("No recent transaction.");
            }
            int len = temp.getTransactions().size();
            int start = 0;
            if (len > 10) {
                start = len - 10;
            }
            for (int i = start; i < len; i++) {
                System.out.println(temp.getTransactions().get(i));
            }
            System.out.println("Enter any key to go back");
            getInput();
            loginSucceed(phoneNumber);

        } else if (ans.equals("5")) {
            resetPassword(phoneNumber);
        } else {
            init();
        }
    }

    public boolean enterPassword(String phoneNumber) {
        cleanConsole();
        System.out.println("Input your password: ");
        String pw = getInput();

        if (!pw.equals(userData.get(phoneNumber).getPassword())) {
            cleanConsole();
            System.out.println("Invalid password!");
            System.out.println("1. Try again;");
            System.out.println("2. Forgot password;");

            String ans = getInput(new String[]{"1", "2"});
            if (ans.equals("1")) {
                enterPassword(phoneNumber);
            } else {
                forgotPassword(phoneNumber);
            }

        }
        return true;
    }

    public void forgotPassword(String phoneNumber) {
        boolean validated = false;
        while (!validated) {
            System.out.println("Please input your name: ");
            String n = getInput();
            System.out.println("Please input your age: ");
            String a = getInput();
            User temp = userData.get(phoneNumber);
            if (n.equals(temp.getName()) && a.equals(temp.getAge())) {
                validated = true;
            } else {
                System.out.println("Your inputs don't match your name or age. Please try again later.");
                pressKeyToContinue("go back.");
                init();
            }
        }


    }

    public void resetPassword(String phoneNumber) {
        cleanConsole();
        System.out.println("Input your password: (Entern \"c\" to go back)");
        String ans = getInput();
        if (ans.equals("c")) {
            loginSucceed(phoneNumber);
        }
        String oldPassword = userData.get(phoneNumber).getPassword();
        if (!ans.equals(oldPassword)) {
            cleanConsole();
            System.out.println("Wrong password, press any key to re-enter the password");
            getInput();
            resetPassword(phoneNumber);
        }

        boolean passwordConfirmed = false;
        String password = "";
        while (!passwordConfirmed) {
            cleanConsole();
            System.out.println("Input your new password:");
            password = getInput();
            System.out.println("Confirm your password:");
            if (password.equals(getInput())) {
                passwordConfirmed = true;
            } else {
                cleanConsole();
                System.out.println("Your input is not matched with last time, please input your password again.");
            }
        }
        User temp = userData.get(phoneNumber);
        temp.setPassword(password);
        userData.put(phoneNumber, temp);
        jacksonWrite();
        cleanConsole();
        pressKeyToContinue("go back.");
        loginSucceed(phoneNumber);

    }

    public String getTimeStamp() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return " " + dateFormat.format(date);
    }

    public String getBalance(String phoneNumber) {
        Double b = userData.get(phoneNumber).getBalance();
        return Double.toString(b);
    }

    public String withdraw(String phoneNumber, String amount) {
        Double am = Double.parseDouble(amount);
        User temp = userData.get(phoneNumber);
        if (am + transactionFee > temp.getBalance()) {
            System.out.println("Not enough balance.");
            pressKeyToContinue("main menu.");
            loginSucceed(phoneNumber);
        } else if ( am + transactionFee > availableAmountInMachine ) {
            System.out.println("Sorry, the available funds on this ATM is not enough.");
            pressKeyToContinue("go back.");
            loginSucceed(phoneNumber);
        }

        Double b = temp.getBalance() - am - transactionFee;
        temp.setBalance(b);
        String transaction = "Withdraw $" + Double.toString(am)  + getTimeStamp();
        temp.addTransaction(transaction);
        userData.put(phoneNumber, temp);
        jacksonWrite();
        System.out.println("Withdraw succeed!");
        System.out.println(transaction);

        return Double.toString(b);
    }

    public String deposit(String phoneNumber, String amount) {
        Double am = Double.parseDouble(amount);
        User temp = userData.get(phoneNumber);
        Double b = temp.getBalance() + am;
        temp.setBalance(b);
        String transaction = "Make deposit $" + Double.toString(am)  + getTimeStamp();
        temp.addTransaction(transaction);
        userData.put(phoneNumber, temp);
        jacksonWrite();
        return Double.toString(b);
    }

    public void pressKeyToContinue(String what) {
        System.out.println("Enter any key to " + what);
        getInput();
    }

    public double getAvailableAmountInMachine() {
        return availableAmountInMachine;
    }

    public void setAvailableAmountInMachine(double availabelAmountInMachine) {
        this.availableAmountInMachine = availabelAmountInMachine;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public Map<String, User> getUserData() {
        return userData;
    }

    public void setUserData(Map<String, User> userData) {
        this.userData = userData;
    }
}

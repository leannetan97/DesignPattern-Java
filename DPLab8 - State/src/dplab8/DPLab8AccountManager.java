/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dplab8;

import java.util.Scanner;

/**
 *
 * @author Tan Lay Yan WIF160058
 */
class BusinessAccount {

    public static final double MIN_BALANCE = 2000.00;
    public static final double OVERDRAW_LIMIT = -1000.00;
    public static final double TRANS_FEE_NORMAL = 2.00;
    public static final double TRANS_FEE_OVERDRAW = 5.00;
    public static final String ERR_OVERDRAW_LIMIT_EXCEED = "Error: Transaction cannot be processed. Overdraw limit exceeded";

    private State objState;
    private final String accountNumber;
    private double balance;

    private State noTransactionFeeState;
    private State transactionFeeState;
    private State overDrawnState;

    public BusinessAccount(String accountNum) {
        accountNumber = accountNum;
        noTransactionFeeState = new NoTransactionFeeState(this);
        transactionFeeState = new TransactionFeeState(this);
        overDrawnState = new OverDrawnState(this);
        objState = noTransactionFeeState;
        balance = 0.0;
    }

    public void setState(State newState) {
        objState = newState;
    }

    public State getState() {
        return objState;
    }

    public State noTransactionFeeState() {
        return noTransactionFeeState;
    }

    public State transactionFeeState() {
        return transactionFeeState;
    }

    public State overDrawnState() {
        return overDrawnState;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean deposit(double amount) {
        objState.deposit(amount);
        return true;
    }

    public boolean withdraw(double amount) {
        objState.withdraw(amount);
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double newBalance) {
        balance = newBalance;
    }
}

interface State {

    public void withdraw(double amount);

    public void deposit(double amount);
}

class NoTransactionFeeState implements State {

    BusinessAccount businessAccount;

    NoTransactionFeeState(BusinessAccount businessAccount) {
        this.businessAccount = businessAccount;
    }

    @Override
    public void withdraw(double amount) {
        double newBalance = businessAccount.getBalance() - amount;
        if (newBalance < businessAccount.OVERDRAW_LIMIT) {
            System.out.println(businessAccount.ERR_OVERDRAW_LIMIT_EXCEED);
        } else {
            System.out.println("An amount " + amount + " is withdrawn.");
            businessAccount.setBalance(newBalance);
            if (newBalance < businessAccount.MIN_BALANCE && newBalance >= 0.0) {
                businessAccount.setState(businessAccount.transactionFeeState());
            } else if (newBalance < 0.0 && newBalance > businessAccount.OVERDRAW_LIMIT) {
                System.out.println("Attention: Your Account is Overdrawn.");
                businessAccount.setState(businessAccount.overDrawnState());
            }
        }
    }

    @Override
    public void deposit(double amount) {
        double newBalance = businessAccount.getBalance() + amount;
        businessAccount.setBalance(newBalance);
        System.out.println("An amount " + amount + " is deposited.");
    }

}

class TransactionFeeState implements State {

    BusinessAccount businessAccount;

    TransactionFeeState(BusinessAccount businessAccount) {
        this.businessAccount = businessAccount;
    }

    @Override
    public void withdraw(double amount) {
        double newBalance = businessAccount.getBalance() - amount;

        if (newBalance < businessAccount.OVERDRAW_LIMIT) {
            System.out.println("Error: Transaction cannot be processed. Overdraw limit exceeded.");
        } else {
            System.out.println("An amount " + amount + " is withdrawn.");
            businessAccount.setBalance(newBalance - businessAccount.TRANS_FEE_NORMAL);
            System.out.println("Transaction Fee ($2.0)was charged due to account status (less than minimum balance)");
            if (newBalance < 0.0 && newBalance > businessAccount.OVERDRAW_LIMIT) {
                System.out.println("Attention: Your Account is Overdrawn.");
                businessAccount.setState(businessAccount.overDrawnState());
            }
        }
    }

    @Override
    public void deposit(double amount) {
        double newBalance = businessAccount.getBalance() + amount;
        System.out.println("An amount " + amount + " is deposited.");
        businessAccount.setBalance(newBalance - businessAccount.TRANS_FEE_NORMAL);
        System.out.println("Transaction Fee  ($2.0)was charged due to account status (less than minimum balance)");
        if (newBalance > businessAccount.MIN_BALANCE) {
            businessAccount.setState(businessAccount.noTransactionFeeState());
        }
    }

}

class OverDrawnState implements State {

    BusinessAccount businessAccount;

    OverDrawnState(BusinessAccount businessAccount) {
        this.businessAccount = businessAccount;
    }

    @Override
    public void withdraw(double amount) {
        double newBalance = businessAccount.getBalance() - amount;

        if (newBalance < 0.0 && newBalance > businessAccount.OVERDRAW_LIMIT) {
            System.out.println("An amount " + amount + " is withdrawn.");
            businessAccount.setBalance(newBalance - businessAccount.TRANS_FEE_OVERDRAW);
            System.out.println("Transaction Fee  ($5.0)was charged due to account status(Overdrawn)");
        } else if (newBalance < businessAccount.OVERDRAW_LIMIT) {
            System.out.println("Error: Transaction cannot be processed. Overdraw limit exceeded.");
        }
    }

    @Override
    public void deposit(double amount) {
        double newBalance = businessAccount.getBalance() + amount;
        System.out.println("An amount " + amount + " is deposited.");
        businessAccount.setBalance(newBalance - businessAccount.TRANS_FEE_OVERDRAW);
        System.out.println("Transaction Fee  ($5.0)was charged due to account status(Overdrawn)");
        if (newBalance < businessAccount.MIN_BALANCE && newBalance >= 0.0) {
            businessAccount.setState(businessAccount.transactionFeeState());
        } else if (newBalance > businessAccount.MIN_BALANCE) {
            businessAccount.setState(businessAccount.noTransactionFeeState());
        }
    }

}

public class DPLab8AccountManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BusinessAccount acc = new BusinessAccount("1111 2222 3333 4444");
        while (true) {
            System.out.println("Enter 1 to display account, 2 to deposit, 3 to withdraw, 0 to exit");
            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    System.out.println("Account number = " + acc.getAccountNumber() + ", Balance = " + acc.getBalance());
                    break;
                case 2:
                    System.out.println("Enter amount to deposit: ");
                    double amountDP = in.nextDouble();
                    acc.deposit(amountDP);
                    System.out.println("Account number = " + acc.getAccountNumber() + ", Balance = " + acc.getBalance());
                    break;
                case 3:
                    System.out.println("Enter amount to withdrawn: ");
                    double amountWD = in.nextDouble();
                    acc.withdraw(amountWD);
                    System.out.println("Account number = " + acc.getAccountNumber() + ", Balance = " + acc.getBalance());
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            if(choice == 0) break;
        }
    }

}

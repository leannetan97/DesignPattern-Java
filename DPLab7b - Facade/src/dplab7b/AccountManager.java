/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dplab7b;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author WIF160058
 */
class Account {

    String firstName;
    String lastName;
    final String ACCOUNT_DATA_FILE = "AccountData.txt";

    public Account(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean isValid() {
        if (getLastName().trim().length() < 2) {
            return false;
        }
        return true;
    }

    public boolean save() {
        FileUtility futil = new FileUtility();
        String dataLine = getLastName() + "," + getFirstName();
        return futil.writeToFile(ACCOUNT_DATA_FILE, dataLine);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

class Address {

    private String address, city, state;
    final String ACCOUNT_DATA_FILE = "Address.txt";

    public Address(String address, String city, String state) {
        this.address = address;
        this.city = city;
        this.state = state;
    }

    public boolean isValid() {
        if (getState().trim().length() < 2) {
            return false;
        }
        return true;
    }

    public boolean save() {
        FileUtility futil = new FileUtility();
        String dataLine = getAddress() + "," + getCity() + "," + getState();
        return futil.writeToFile(ACCOUNT_DATA_FILE, dataLine);
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}

class CreditCard {
    private String cardType, cardNumber, cardExpDate;
    final String CC_DATA_FILE = "CC.txt";
    public static final String VISA = "Visa";
    public static final String MASTER = "Master";

    public CreditCard(String cardType, String cardNumber, String cardExpDate) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cardExpDate = cardExpDate;
    }

    public boolean isValid() {
        if (getCardType().equals(VISA) || getCardType().equals(MASTER)) {
            return (getCardNumber().trim().length() == 16);
        }
        return false;
    }

    public boolean save() {
        FileUtility futil = new FileUtility();
        String dataLine = getCardType() + "," + getCardNumber() + "," + getCardExpDate();
        return futil.writeToFile(CC_DATA_FILE, dataLine);
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardExpDate() {
        return cardExpDate;
    }
}

class FileUtility {

    public boolean writeToFile(String filename, String msg) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            bw.write(msg);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {

        }
        return true;
    }
}

class CustomerFacade {

    private Address address;
    private Account account;
    private CreditCard creditCard;

    public CustomerFacade(Address address, Account account, CreditCard creditCard) {
        this.address = address;
        this.account = account;
        this.creditCard = creditCard;
    }
    
    public void printDetails(){
        System.out.println("First name = " + account.getFirstName() +", Last name = " + account.getLastName());
        System.out.println("Address = " +address.getAddress() + ", City = " + address.getCity() + ", State = " + address.getState());
        System.out.println("Card type = " +creditCard.getCardType() + ", Card number = " + creditCard.getCardNumber() + ", Card expiry date = " + creditCard.getCardExpDate());
    
    }
    public void validateDetails(){
        boolean accountValid = account.isValid();
        boolean addressValid = address.isValid();
        boolean creditCardValid = creditCard.isValid();
        boolean[] list = {accountValid, addressValid, creditCardValid};
        String[] detailList = {"FirstName/LastName","Address/City/State","CreditCard Info"};
        
        for (int i = 0; i < list.length; i++) {
            if(list[i]){
                System.out.println("Valid " + detailList[i]);
            }else{
                System.out.println("Invalid " + detailList[i]);
            }
        }
        
        if(accountValid && addressValid && creditCardValid){
            saveDetails();
            System.out.println("==> Valid Customer Data: Data Saved Successfully");
        }else{
            System.out.println("==> Invalid Customer Data: Data Could Not Be Saved");
        }
        
        
    }
    private void saveDetails(){
        account.save();
        address.save();
        creditCard.save();
    }
}

public class AccountManager {

    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("First customer:");
        CustomerFacade c1 = new CustomerFacade(new Address("101 Jalan Bukit","Shah Alam","Selangor"),new Account("John", "Smith"), new CreditCard("Visa","1111222233334444","01/09/2020"));
        c1.printDetails();
        c1.validateDetails();
        
        System.out.println("\nSecond customer:");
        CustomerFacade c2 = new CustomerFacade(new Address("1 Jalan University","Kuala Lumpur","Wilayah Persekutuan"),new Account("Vijaya", "K"), new CreditCard("Master","9999888877776666","01/01/2022"));
        c2.printDetails();
        c2.validateDetails();
        
        System.out.println("\nThird customer:");
        CustomerFacade c3 = new CustomerFacade(new Address("35 Wisma Jaya"," Petaling Jaya","Selangor"),new Account("Aryati", "Ahmad"), new CreditCard("Master","555566667777","01/05/2023"));
        c3.printDetails();
        c3.validateDetails();
    }

}

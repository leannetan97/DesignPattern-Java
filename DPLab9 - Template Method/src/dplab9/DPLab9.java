/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dplab9;

import java.util.Calendar;

/**
 *
 * @author Tan Lay Yan WIF160058
 */
abstract class CreditCard {

    String cardType, cardNumber;
    int cardExpiryMonth, cardExpiryYear;

    CreditCard(String cardType, String cardNumber, int cardExpiryMonth, int cardExpiryYear) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.cardExpiryMonth = cardExpiryMonth;
        this.cardExpiryYear = cardExpiryYear;
    }

    final void validateCard() {
        boolean expirationDateValid = checkExpirationDate();
        boolean lengthValid = checkLength();
        boolean prefixValid = checkPrefix();
        boolean charactersValid = checkValidCharacters();
        boolean digitAlgorithhmValid = checkDigitAlgorithhm();
        boolean accountValid = isAccountInGoodStand();

        if (expirationDateValid && lengthValid && prefixValid && charactersValid && digitAlgorithhmValid && accountValid) {
            System.out.println("This credit card is valid");
        } else {
            System.out.println("This credit card is invalid");
        }
    }

    private boolean checkExpirationDate() {
        if ((cardExpiryYear < Calendar.getInstance().get(Calendar.YEAR)) || ((cardExpiryYear == Calendar.getInstance().get(Calendar.YEAR)) && (cardExpiryMonth <= Calendar.getInstance().get(Calendar.MONTH)))) {
            System.out.println(" Invalid Expiry Date");
            return false;
        }
        return true;
    }

    abstract boolean checkLength();

    abstract boolean checkPrefix();

    private boolean checkValidCharacters() {
        if (!cardNumber.chars().allMatch( Character::isDigit )) {
            System.out.println(" Invalid Characters");
            return false;
        }
        return true;
    }

    private boolean checkDigitAlgorithhm() {
        boolean result = true;

        int sum = 0;
        int multiplier = 1;
        int strLen = cardNumber.length();

        for (int i = 0; i < strLen; i++) {
            String digit = cardNumber.substring(strLen - i - 1, strLen - i);
            int currProduct = new Integer(digit).intValue() * multiplier;
            if (currProduct >= 10) {
                sum += (currProduct % 10) + 1;
            } else {
                sum += currProduct;
            }
            if (multiplier == 1) {
                multiplier++;
            } else {
                multiplier--;
            }
        }
        if ((sum % 10) != 0) {
            System.out.println(" Invalid CheckSum");
            result = false;
        }
        return result;
    }

    abstract boolean isAccountInGoodStand();
}

class Visa extends CreditCard {
    
    Visa(String cardType, String cardNumber, int cardExpiryMonth, int cardExpiryYear) {
        super(cardType,cardNumber,cardExpiryMonth,cardExpiryYear); 
        System.out.println("\nCard type = " + cardType + ", Card number = " + cardNumber + ", Expiry month = " + cardExpiryMonth + 11 + ", Expiry year = " + cardExpiryYear);
    }
    
    @Override
    boolean checkLength() {
        if(cardNumber.length() == 13 || cardNumber.length() == 16){
            return true;
        }
        System.out.println(" Invalid Length");
        return false;
    }

    @Override
    boolean checkPrefix() {
        if(cardNumber.charAt(0) != '4'){
            System.out.println(" Invalid Prefix");
            return false;
        }
        return true;
    }

    @Override
    boolean isAccountInGoodStand() {
        /* Make necessary API calls to perform other checks. */ 
        // Call Visa API
        return true;
    }

}

class MasterCard extends CreditCard {
    
    MasterCard(String cardType, String cardNumber, int cardExpiryMonth, int cardExpiryYear) {
        super(cardType,cardNumber,cardExpiryMonth,cardExpiryYear); 
        System.out.println("\nCard type = " + cardType + ", Card number = " + cardNumber + ", Expiry month = " + cardExpiryMonth + 11 + ", Expiry year = " + cardExpiryYear);
    }
    
    @Override
    boolean checkLength() {
        if(cardNumber.length() == 16){
            return true;
        }
        System.out.println(" Invalid Length");
        return false;
    }

    @Override
    boolean checkPrefix() {
        int cardNumberPrefix = Integer.parseInt(cardNumber.substring(0,2));
        if(cardNumberPrefix < 51 && cardNumberPrefix > 55){
            System.out.println(" Invalid Prefix");
            return false;
        }
        return true;
    }

    @Override
    boolean isAccountInGoodStand() {
        /* Make necessary API calls to perform other checks. */ 
        // Call MasterCard API
        return true;
    }
}
class DinersClub extends CreditCard {
    
    DinersClub(String cardType, String cardNumber, int cardExpiryMonth, int cardExpiryYear) {
        super(cardType,cardNumber,cardExpiryMonth,cardExpiryYear); 
        System.out.println("\nCard type = " + cardType + ", Card number = " + cardNumber + ", Expiry month = " + cardExpiryMonth + 11 + ", Expiry year = " + cardExpiryYear);
    }
    
    @Override
    boolean checkLength() {
        if(cardNumber.length() == 14){
            return true;
        }
        System.out.println(" Invalid Length");
        return false;
    }

    @Override
    boolean checkPrefix() {
        int cardNumberPrefix = Integer.parseInt(cardNumber.substring(0,2));
        if(cardNumberPrefix == 30 || cardNumberPrefix == 36 || cardNumberPrefix == 38){
            return true;
        }
        System.out.println(" Invalid Prefix");
        return false;
    }

    @Override
    boolean isAccountInGoodStand() {
        /* Make necessary API calls to perform other checks. */ 
        // Call DinersClub API
        return true;
    }
}



public class DPLab9 {

    public static void main(String[] args) {
        //Q1
//a) validatingCard()
//b) Steps: check expiration data, check valid characters and the check digit algorithm
//c) Steps: check length, check prefix, check account in good standing
        
        //Q2
        CreditCard visa1 = new Visa("VisaCard", "1234123412341234", 11, 2004);
        visa1.validateCard();
        CreditCard visa2 = new Visa("VisaCard", "1234567890123456", 11, 2020);
        visa2.validateCard();
        CreditCard visa3 = new Visa("VisaCard", "4234567890123456", 11, 2020);
        visa3.validateCard();
        CreditCard master1 = new MasterCard("MasterCard", "5448755330349315", 4, 2021);
        master1.validateCard();
        CreditCard diners1 = new DinersClub("DinersCard", "30263720264678", 5, 2025);
        diners1.validateCard();
    }

}



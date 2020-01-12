/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dplab7a;

/**
 *
 * @author WIF160058
 */
interface AddressValidator {

    public boolean isValidAddress(String inp_address, String inp_zip, String inp_state);
}

class USAddress implements AddressValidator {

    @Override
    public boolean isValidAddress(String inp_address, String inp_zip, String inp_state) {
        if (inp_address.trim().length() < 10) {
            return false;
        }
        if (inp_zip.trim().length() < 5) {
            return false;
        }
        if (inp_zip.trim().length() > 10) {
            return false;
        }
        if (inp_state.trim().length() != 2) {
            return false;
        }
        return true;
    }

}

class CAAddress {

    public boolean isValidCanadianAddr(String inp_address, String inp_pcode, String inp_prvnc) {
        if (inp_address.trim().length() < 15) {
            return false;
        }
        if (inp_pcode.trim().length() != 6) {
            return false;
        }
        if (inp_prvnc.trim().length() < 6) {
            return false;
        }
        return true;
    }
}


enum CustomerType {
    US, CA
};

class Customer {

    private final String name, address, zip, state;
    private final CustomerType type;

    public Customer(String name, String address, String zip, String state, CustomerType type) {
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.state = state;
        this.type = type;
    }

    public boolean isValidAddress() {
        AddressValidator validate = getValidator(type);
        return validate.isValidAddress(address, zip, state);
    }

    public AddressValidator getValidator(CustomerType custType) {
        AddressValidator validator = null;
        if (custType.equals(CustomerType.US)) {
            validator = new USAddress();
        } else if (custType.equals(CustomerType.CA)) {
            //Q2
//            validator = new ClassAdapter();
            //Q5
            validator = new Adapter(new CAAddress());
        }
        return validator;
    }

    public String getValidityInString() {
        if (isValidAddress()) {
            return "Valid customer data";
        }
        return "Invalid customer data";
    }
    public String getTypeInString() {
        if (type.equals(CustomerType.CA)) {
            return "Canada";
        }
        return "US";
    }
    
    //Q3
    public void display() {
        System.out.println("Customer Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Zip/PostalCode: " + zip);
        System.out.println("State/Province: " + state);
        System.out.println("Address Type: " + getTypeInString());
        System.out.println("Result: " + getValidityInString());
        System.out.println("");
    }
}

//Q3
class ClassAdapter extends CAAddress implements AddressValidator {
    
    @Override
    public boolean isValidAddress(String inp_address, String inp_zip, String inp_state) {
        return isValidCanadianAddr(inp_address, inp_zip, inp_state);
    }
    
}
//Q2
class Adapter implements AddressValidator {

    CAAddress c;

    public Adapter(CAAddress c) {
        this.c = c;
    }

    @Override
    public boolean isValidAddress(String inp_address, String inp_zip, String inp_state) {
        return c.isValidCanadianAddr(inp_address, inp_zip, inp_state);
    }

}
public class AddressClassAdapterTest {

    public static void main(String[] args) {
        Customer google = new Customer("Google", "1600 Amphitheatre Parkway", "94043", "CA", CustomerType.US);
        google.display();
        Customer google1 = new Customer("Google", "1600 Amphitheatre Parkway", "94043", "CA", CustomerType.CA);
        google1.display();
        //No 6 no changes need to be made
    }

}

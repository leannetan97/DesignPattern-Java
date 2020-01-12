/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpsingletonL3b;

/**
 *
 * @author Leanne
 */

class ChocolateBoiler{
    private static ChocolateBoiler uniqueInstance;
    private boolean empty;
    private boolean boiled;
    
    private ChocolateBoiler(){
        empty = true;
        boiled = false;
    }
    
    public static synchronized ChocolateBoiler getInstance(){
        if (uniqueInstance == null){
            System.out.println("New Boiler");
            uniqueInstance = new ChocolateBoiler();
        }else{
            System.out.println("Boiler exist, this is the boiler");
        }
        return uniqueInstance;
    }
    
    public void fill(){
        if(isEmpty()){
            empty = false;
            boiled = false;
            // fill in boiler with liquid
        }
    }
    
    public void drain(){
        if(!isEmpty() && isBoiled()){
            // drain it
            empty = true;
        }
    }
    
    public void boil(){
        if(!isEmpty() && !isBoiled()){
            // contents boiling
            boiled = true;
        }
    }
    
    public boolean isEmpty(){
        return empty;
    }
    
    public boolean isBoiled(){
        return boiled;
    }
    
}

class ChocolateController{
    
    public static void main(String[] args) {
        ChocolateBoiler boiler1 = ChocolateBoiler.getInstance();
        ChocolateBoiler boiler2 = ChocolateBoiler.getInstance();
        System.out.println(boiler1.isEmpty());
        boiler1.fill();
        System.out.println(boiler2.isEmpty());
        
         System.out.println(boiler1);
         System.out.println(boiler2);
    }
    
}

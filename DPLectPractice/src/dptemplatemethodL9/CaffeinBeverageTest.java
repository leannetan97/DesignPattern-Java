/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dptemplatemethodL9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Leanne
 */
abstract class CaffeinBeverage{
    final void prepareRecipe(){
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    private void boilWater() { // Need ask Dr.Su is it okay to put private?
        System.out.println("Boiling Water");
    }
    
    private void pourInCup(){ 
        System.out.println("Pouring into cup");
    }
    
    abstract void brew();
    abstract void addCondiments();
}

abstract class CaffeinBeverageWithHook{
    final void prepareRecipe(){
        boilWater();
        brew();
        pourInCup();
        if(customerWantsCondiments()){
            addCondiments();
        }
    }

    private void boilWater() { // Need ask Dr.Su is it okay to put private?
        System.out.println("Boiling Water");
    }
    
    private void pourInCup(){ 
        System.out.println("Pouring into cup");
    }
    
    abstract void brew();
    abstract void addCondiments();
    
    boolean customerWantsCondiments(){
        // Subclass and ignore and using these default or choose to overwrite the implementation
        return true;
    }
}
class Tea extends CaffeinBeverage{

    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding Lemon");
    }
}
class TeaWithHook extends CaffeinBeverageWithHook{

    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding Lemon");
    }
     @Override
    public boolean customerWantsCondiments(){
        String answer = getUserInput();
        return answer.toLowerCase().startsWith("y");
    }
    
    private String getUserInput(){
        String answer = null;
        System.out.println("Would you like lemon with your tea (y/n)?");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try{
            answer = in.readLine();
        }catch(IOException e){
            System.err.println("IO error trying to read your answer");
        }
        
        if(answer == null){
            return "no";
        }
        return answer;
        
    }
}
class Coffee extends CaffeinBeverage{

    @Override
    void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}

class CoffeeWithHook extends CaffeinBeverageWithHook{

    @Override
    void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
    
    @Override
    public boolean customerWantsCondiments(){
        String answer = getUserInput();
        return answer.toLowerCase().startsWith("y");
    }
    
    private String getUserInput(){
        String answer = null;
        System.out.println("Would you like milk and sugar with your coffee (y/n)?");
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try{
            answer = in.readLine();
        }catch(IOException e){
            System.err.println("IO error trying to read your answer");
        }
        
        if(answer == null){
            return "no";
        }
        return answer;
        
    }
    
}


public class CaffeinBeverageTest {
    public static void main(String[] args) {
        //With hook
        TeaWithHook teaHook = new TeaWithHook();
        CoffeeWithHook coffeeHook = new CoffeeWithHook();
        
        System.out.println("\nMaking tea...");
        teaHook.prepareRecipe();
        
        System.out.println("\nMaking coffee...");
        coffeeHook.prepareRecipe();
        
    }  
}

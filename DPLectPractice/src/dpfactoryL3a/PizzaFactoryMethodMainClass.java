/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpfactoryL3a;

/**
 *
 * @author Leanne
 */

public class PizzaFactoryMethodMainClass {
    public static void main(String[] args) {
        // Look at lecturer note, try do the 
    }
}


abstract class PizzaStoreForFactoryMethod{

    abstract Pizza createPizza(String type);
    Pizza orderPizza(String type){
        Pizza pizza;
        
        pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        System.out.println("We order a" + pizza.name);
        System.out.println(pizza.toString());
        return pizza;
    }
}
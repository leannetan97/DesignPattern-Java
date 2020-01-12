/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpfactoryL3a;

import java.util.ArrayList;

/**
 *
 * @author Leanne
 */
abstract class Pizza{
    String name;
    String dough;
    String sauce;
    ArrayList<String> toppings = new ArrayList<>();
    
    public void prepare(){
        System.out.println("Preparing " + name);
    }
    
    public void bake(){
        System.out.println("Baking " + name);
    }
    
    public void cut(){
        System.out.println("Cutting " + name);
    }
    
    public void box(){
        System.out.println("Boxing " + name);
    }
}

class CheesePizza extends Pizza{
    CheesePizza(){
        name = "Cheese Pizza";
        dough = "Regular Crust";
        sauce = "Marinara Sauce";
        toppings.add("Fresh Mozzarella");
        toppings.add("Parmesan");
    }
    
    @ Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(dough).append("\n");
        str.append(sauce).append("\n");
        str.append("------ Cheese Pizza -------\n");
        for (String e : toppings) {
            str.append(e).append("\n");
        }
        return str.toString();
    }
}

class VeggiePizza extends Pizza{
    public VeggiePizza(){
        name = "Veggie Pizza";
        dough = "Crust";
        sauce = "Marinara Sauce";
        toppings.add("Shredded Mozzarella");
        toppings.add("Grated Parmesan cheese");
        toppings.add("Dices onion");
        toppings.add("Sliced mushrooms");
        toppings.add("Sliced red pepper");
        toppings.add("Sliced black olives");
    }
    
    @ Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(dough).append("\n");
        str.append(sauce).append("\n");
        str.append("------ Veggie Pizza -------\n");
        for (String e : toppings) {
            str.append(e).append("\n");
        }
        return str.toString();
    }
}

class ClamPizza extends Pizza{
    public ClamPizza(){
        name = "Clam Pizza";
        dough = "Thin Crust";
        sauce = "White garlic sauce";
        toppings.add("Clams");
        toppings.add("Sliced Peperoni");
        toppings.add("Sliced onion");
        toppings.add("Grated Parmesan cheese");
    }
    
     @ Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(dough).append("\n");
        str.append(sauce).append("\n");
        str.append("------ Clam Pizza -------\n");
        for (String e : toppings) {
            str.append(e).append("\n");
        }
        return str.toString();
    }
}

class PepperoniPizza extends Pizza{
    public PepperoniPizza(){
        name = "Pepperoni Pizza";
        dough = "Crust";
        sauce = "Marinara sauce";
        toppings.add("Clams");
        toppings.add("Grated Parmesan cheese");
    }
     @ Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(dough).append("\n");
        str.append(sauce).append("\n");
        str.append("------ Pepperoni Pizza -------\n");
        for (String e : toppings) {
            str.append(e).append("\n");
        }
        return str.toString();
    }
}

class PizzaStore{
    private final SimplePizzaFactory pizzaFactory;
    
    public PizzaStore(SimplePizzaFactory pizzaFactory){
        this.pizzaFactory = pizzaFactory;
    }
    
    Pizza orderPizza(String type){
        Pizza pizza;
        
        pizza = pizzaFactory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        System.out.println("We order a" + pizza.name);
        System.out.println(pizza.toString());
        return pizza;
    }
}

class SimplePizzaFactory{
    
    public Pizza createPizza(String type){
        Pizza pizza = null;
        
        if (type.equals("cheese")){
            pizza = new CheesePizza();
        } else if (type.equals("pepperoni")){
            pizza = new PepperoniPizza();
        } else if (type.equals("clam")){
            pizza = new ClamPizza();
        } else if (type.equals("veggie")){
            pizza = new VeggiePizza();
        } 
        return pizza;
    }
}

public class PizzaSimpleFactoryMainClass {
    public static void main(String[] args) {
        PizzaStore store = new PizzaStore(new SimplePizzaFactory());
        store.orderPizza("cheese");
        store.orderPizza("veggie");
    }
}

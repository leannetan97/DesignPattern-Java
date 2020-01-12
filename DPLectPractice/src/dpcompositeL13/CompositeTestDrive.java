/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpcompositeL13;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Leanne
 */

abstract class MenuComponent {
    //Must have
    public void add(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }
    // Must have
    public MenuComponent getChild(int i){
        throw new UnsupportedOperationException();
    }
    
    public void remove(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }
    
    //Get Leaf details
    public String getName(){
        throw new UnsupportedOperationException();
    }
    
    //Get Leaf details
    public String getDescription(){
        throw new UnsupportedOperationException();
    }
    
    //Get Leaf details
    public double getPrice(){
        throw new UnsupportedOperationException();
    }
    
    //Get Leaf details
    public boolean isVegetarian(){
        throw new UnsupportedOperationException();
    }
    
    public void print(){
        throw new UnsupportedOperationException();
    }
}


class MenuItemLeaf extends MenuComponent{
    String name;
    String description;
    double price;
    boolean vegetarian;

    public MenuItemLeaf(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.vegetarian = vegetarian;
    }
    
    //Get Leaf details
    @Override
    public String getName(){
        return name;
    }
    
    //Get Leaf details
    @Override
    public String getDescription(){
        return description;
    }
    
    //Get Leaf details
    @Override
    public double getPrice(){
        return price;
    }
    
    //Get Leaf details
    @Override
    public boolean isVegetarian(){
        return vegetarian;
    }
    
    public void print(){
        System.out.print(" " + getName());
        if(isVegetarian()){
            System.out.print("(v)");
        } 
        System.out.println(", "+ getPrice());
        System.out.println("    -- " + getDescription());
    }
}

class MenuComposite extends MenuComponent{
    ArrayList<MenuComponent> menuComponents = new ArrayList<>();
    String name;
    String description;

    public MenuComposite(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    @Override
    public void add(MenuComponent menuComponent){
        menuComponents.add(menuComponent);
    }
    
    @Override
    public void remove(MenuComponent menuComponent){
        menuComponents.remove(menuComponent);
    }
    
    @Override
    public MenuComponent getChild(int i){
        return menuComponents.get(i);
    }
    
    @Override
    public String getName(){
        return name;
    }
    
    @Override
    public String getDescription(){
        return description;
    }
    
    @Override
    public void print(){
        // Information of this MenuComposite
        System.out.print("\n" + getName());
        System.out.println(", " + getDescription());
        System.out.println("-----------------");
        
        // Information of both MenuComposite and MenuItem
//        Iterator<MenuComponent> iterator = menuComponents.iterator();
//        while(iterator.hasNext()){
//            MenuComponent menuComponent = iterator.next();
//            menuComponent.print();
//        }
        
        for (int i = 0; i < menuComponents.size(); i++) {
            MenuComponent menuComponent = getChild(i);
            menuComponent.print();
        }
    }
}

class WaitressForComposite{
    MenuComponent allMenus;

    public WaitressForComposite(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }
    
    public void printMenu(){
        // Print from top level
        allMenus.print();
    }
}


public class CompositeTestDrive {
    public static void main(String[] args) {
        MenuComponent pancakeHouseMenu = new MenuComposite("Pancake House Menu", "Breakfast");
        MenuComponent dinerMenu = new MenuComposite("Diner Menu", "Lunch");
        MenuComponent cafeMenu = new MenuComposite("Cafe Menu", "Dinner");
        MenuComponent dessertMenu = new MenuComposite("Dessert Menu", "Dessert of course!");
    
        // Add menu items for pancakeHouse
        pancakeHouseMenu.add(new MenuItemLeaf("K&B's Pancake Breakfast", "Pancake with scrambled eggs, and toast", true, 2.99));
        pancakeHouseMenu.add(new MenuItemLeaf("Regular Pancake Breakfast", "Pancake with fried eggs, sausage", false, 2.99));
        pancakeHouseMenu.add(new MenuItemLeaf("Blueberry Pancake", "Pancake made with fresh blueberries", true, 3.49));
        pancakeHouseMenu.add(new MenuItemLeaf("Waffles", "Waffles,with your choice of blueberries or strawberries", true, 3.59));
        
        // Add menu items for Diner
        dinerMenu.add(new MenuItemLeaf("Vegetarian BLT", "(Fakin') Bacan with lettuce & tomato on whole wheat", true, 2.99));
        dinerMenu.add(new MenuItemLeaf("BLT", "Bacan with lettuce & tomato on whole wheat", false, 2.99));
        dinerMenu.add(new MenuItemLeaf("Soup of the day", "Soup of the day, with a side of potato salad", false, 3.29));
        dinerMenu.add(new MenuItemLeaf("Hotdog", "A hot dog, with saurkraut, relish, onions, topped with cheese", true, 3.05));
        dinerMenu.add(new MenuItemLeaf("Pasta", "Spaghetti with Marinara Sayce, and a slice of sourdough bread", true, 3.89));
        dinerMenu.add(dessertMenu);
        
        // Add menu items for dessertMenu
        dessertMenu.add(new MenuItemLeaf("Apple Pie", "Apple pie with flakey crust, topped with vanilla icecream", true, 1.59));
        
        // Add menu items for Cafe
        cafeMenu.add(new MenuItemLeaf("Vegetarian Burger and Air Fries", "Veggie Burger on a whole ehat bun, lettuce, tomato and fries", true,3.99));
        cafeMenu.add(new MenuItemLeaf("Soup of the day", "Soup of the day, with a side side salad", false, 2.99));
        cafeMenu.add(new MenuItemLeaf("Burrito", "A large burrito, with whole pinto beans, salsa, guacamole", false, 4.29));
        
        MenuComponent allMenus = new MenuComposite("All Menu","All menus combined");
        allMenus.add(pancakeHouseMenu);
        allMenus.add(dinerMenu);
        allMenus.add(cafeMenu);
        
        WaitressForComposite waitress = new WaitressForComposite(allMenus);
        waitress.printMenu();
    }
}

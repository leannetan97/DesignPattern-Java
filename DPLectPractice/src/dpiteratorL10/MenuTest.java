/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpiteratorL10;

import java.util.ArrayList;


/**
 *
 * @author Tan Lay Yan WIF160058
 */

interface Iterator{

    public boolean hasNext();

    public MenuItem next();
}

class MenuItem {

    String name;
    String description;
    boolean vegetarian;
    double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public double getPrice() {
        return price;
    }

}

class PancakeHouseMenuIterator implements Iterator{

    ArrayList<MenuItem> items;
    int position = 0;
    
    PancakeHouseMenuIterator(ArrayList<MenuItem> items){
        this.items = items;
    }
    
    @Override
    public boolean hasNext() {
        if(position >= items.size() || items.get(position) == null){
            return false;
        }else{
            return true;  
        }
    }

    @Override
    public MenuItem next() {
        MenuItem menuItem = items.get(position);
        position++;
        return menuItem;
    }
    
}

class DinnerMenuIterator implements Iterator{
    MenuItem[] itemsList;
    int position = 0;
    
    public DinnerMenuIterator(MenuItem[] itemsList){
        this.itemsList = itemsList;
    }
    
    @Override
    public boolean hasNext() {
        if(position >= itemsList.length  || itemsList[position] == null){
            return false;
        }
        return true;
    }

    @Override
    public MenuItem next() {
        MenuItem item = itemsList[position];
        position += 1;
        return item;
    }
    
   
    
}
class PancakeHouseMenu {

    ArrayList<MenuItem> menuItem;

    public PancakeHouseMenu() {
        menuItem = new ArrayList<>();
        addItem("K&B's Pancake Breakfast", "Pancake with scrambled eggs, and toast", true, 2.99);
        addItem("Regular Pancake Breakfast", "Pancake with fried eggs, sausage", false, 2.99);
        addItem("Blueberry Pancake", "Pancake made with fresh blueberries", true, 3.49);
        addItem("Waffles", "Waffles,with your choice of blueberries or strawberries", true, 3.59);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem item = new MenuItem(name, description, vegetarian, price);
        menuItem.add(item);
    }

    public Iterator createIterator() {
        return new PancakeHouseMenuIterator(menuItem);
    }

}



class DinnerMenu {

    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    public DinnerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];
        addItem("Vegetarian BLT", "(Fakin') Bacan with lettuce & tomato on whole wheat", true, 2.99);
        addItem("BLT", "Bacan with lettuce & tomato on whole wheat", false, 2.99);
        addItem("Soup of the day", "Soup of the day, with a side of potato salad", false, 3.29);
        addItem("Hotdog", "A hot dog, with saurkraut, relish, onions, topped with cheese", true, 3.05);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem item = new MenuItem(name, description, vegetarian, price);
        if (numberOfItems >= MAX_ITEMS) {
            System.out.println("Sorry, menu is full! Can't add item to menu");
        } else {
            menuItems[numberOfItems] = item;
            numberOfItems += 1;
        }

    }

    public Iterator createIterator() {
        return new DinnerMenuIterator(menuItems);
    }


}



class Waitress {
    PancakeHouseMenu pancakeHouseMenu;
    DinnerMenu dinnerMenu;
    
    public Waitress(PancakeHouseMenu pancakeHouseMenu,DinnerMenu dinnerMenu){
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinnerMenu = dinnerMenu;
    }
    
    public void printMenu(){
        Iterator pancakeIterator = pancakeHouseMenu.createIterator();
        Iterator dinnerIterator = dinnerMenu.createIterator();
        
        System.out.println("MENU\n----\nBREAKFAST");
        printMenu(pancakeIterator);
        System.out.println("LUNCH");
        printMenu(dinnerIterator);
    }
    
    public void printMenu(Iterator iterator){
        while(iterator.hasNext()){
            MenuItem item = (MenuItem)iterator.next();
            System.out.print(item.getName() + ", ");
            System.out.print(item.getPrice() + " -- ");
            System.out.println(item.getDescription());
        }
    }
    
}

public class MenuTest {

    public static void main(String[] args) {
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        DinnerMenu dinnerMenu = new DinnerMenu();
        
        Waitress waitress = new Waitress(pancakeHouseMenu, dinnerMenu);
        waitress.printMenu();
    }
}

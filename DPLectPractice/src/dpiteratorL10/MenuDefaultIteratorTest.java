/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpiteratorL10;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Tan Lay Yan WIF160058
 */
interface Menu {

    public Iterator<MenuItem> createIterator();
}

class DinnerMenuDefaultIterator implements Iterator {

    MenuItem[] itemsList;
    int position = 0;

    public DinnerMenuDefaultIterator(MenuItem[] itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public boolean hasNext() {
        if (position >= itemsList.length || itemsList[position] == null) {
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

    @Override
    public void remove() {
        if (position <= 0) {
            throw new IllegalStateException("You can't remove an item until you added one");
        }
        if (itemsList[position - 1] != null) {
            for (int i = position - 1; i < (itemsList.length - 1); i++) {
                itemsList[i] = itemsList[i + 1];
            }
            itemsList[itemsList.length - 1] = null;
        }
    }
}

class CafeMenuWithIterator implements Menu {

    HashMap<String, MenuItem> menuItems;

    public CafeMenuWithIterator() {
        menuItems = new HashMap<>();
        addItem("K&B's Pancake Breakfast", "Pancake with scrambled eggs, and toast", true, 2.99);
        addItem("Regular Pancake Breakfast", "Pancake with fried eggs, sausage", false, 2.99);
        addItem("Blueberry Pancake", "Pancake made with fresh blueberries", true, 3.49);
        addItem("Waffles", "Waffles,with your choice of blueberries or strawberries", true, 3.59);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem item = new MenuItem(name, description, vegetarian, price);
        menuItems.put(item.getName(),item);
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return menuItems.values().iterator();
    }

}

class AlternatingDinerMenuIterator implements Iterator {

    MenuItem[] itemsList;
    int position = 0;

    public AlternatingDinerMenuIterator(MenuItem[] itemsList) {
        this.itemsList = itemsList;
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK);
        if(day%2 == 0){
            position = 1;
        }else{
            position = 0;
        }
    }

    @Override
    public boolean hasNext() {
        if (position >= itemsList.length || itemsList[position] == null) {
            return false;
        }
        return true;
    }

    @Override
    public MenuItem next() {
        MenuItem item = itemsList[position];
        position += 2;
        return item;
    }

    @Override
    public void remove() {
        if (position <= 0) {
            throw new IllegalStateException("You can't remove an item until you added one");
        }
        if (itemsList[position - 1] != null) {
            for (int i = position - 1; i < (itemsList.length - 1); i++) {
                itemsList[i] = itemsList[i + 1];
            }
            itemsList[itemsList.length - 1] = null;
        }
    }
}

class PancakeHouseMenuWithIterator implements Menu {

    ArrayList<MenuItem> menuItem;

    public PancakeHouseMenuWithIterator() {
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

    @Override
    public Iterator<MenuItem> createIterator() {
        return menuItem.iterator();
    }

}

class DinnerMenuWithIterator implements Menu {

    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    public DinnerMenuWithIterator() {
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

    @Override
    public Iterator<MenuItem> createIterator() {
//        return new DinnerMenuDefaultIterator(menuItems);
        return new AlternatingDinerMenuIterator(menuItems);
    }

}

class WaitressForDefaultIterator {

    PancakeHouseMenuWithIterator pancakeHouseMenu;
    DinnerMenuWithIterator dinnerMenu;
    CafeMenuWithIterator cafeMenu;

    public WaitressForDefaultIterator(PancakeHouseMenuWithIterator pancakeHouseMenu, DinnerMenuWithIterator dinnerMenu,CafeMenuWithIterator cafeMenu ) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinnerMenu = dinnerMenu;
        this.cafeMenu = cafeMenu;
    }

    public void printMenu() {
        Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
        Iterator<MenuItem> dinnerIterator = dinnerMenu.createIterator();
        Iterator<MenuItem> cafeIterator = cafeMenu.createIterator();
        System.out.println("MENU\n----\nBREAKFAST");
        printMenu(pancakeIterator);
        System.out.println("LUNCH");
        printMenu(dinnerIterator);
        System.out.println("CAFE");
        printMenu(cafeIterator);
    }

    public void printMenu(Iterator<MenuItem> iterator) {
        while (iterator.hasNext()) {
            MenuItem item = iterator.next();
            System.out.print(item.getName() + ", ");
            System.out.print(item.getPrice() + " -- ");
            System.out.println(item.getDescription());
        }
    }
}

public class MenuDefaultIteratorTest {

    public static void main(String[] args) {
        PancakeHouseMenuWithIterator pancakeHouseMenu = new PancakeHouseMenuWithIterator();
        DinnerMenuWithIterator dinnerMenu = new DinnerMenuWithIterator();
        CafeMenuWithIterator cafeMenu = new CafeMenuWithIterator();
        
        WaitressForDefaultIterator waitress = new WaitressForDefaultIterator(pancakeHouseMenu, dinnerMenu, cafeMenu);
        waitress.printMenu();
    }
}

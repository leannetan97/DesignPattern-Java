/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dplab6;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author Tan Lay Yan WIF160058
 */
interface Command {

    public void execute();
}

class NoCommand implements Command {

    NoCommand() {

    }

    @Override
    public void execute() {

    }

}

class AddCommand implements Command {

    Item item;
    Category category;

    AddCommand(Item item, Category category) {
        this.item = item;
        this.category = category;
    }

    @Override
    public void execute() {
        item.addItemToCategory(category);
        category.addItem(item);
    }

}

class DeleteCommand implements Command {

    Item item;
    Category category;

    DeleteCommand(Item item, Category category) {
        this.item = item;
        this.category = category;
    }

    @Override
    public void execute() {
        item.deleteItemFromCategory(category);
        category.deleteItem(item);
    }
}

class MoveCommand implements Command {

    Item item;
    Category categoryOri, categoryNew;

    MoveCommand(Item item, Category categoryOri, Category categoryNew) {
        this.item = item;
        this.categoryOri = categoryOri;
        this.categoryNew = categoryNew;
    }

    @Override
    public void execute() {
        item.move(categoryOri, categoryNew);
        categoryOri.deleteItem(item);
        categoryNew.addItem(item);
    }

}

class Item {

    String name;
    HashMap<Category, Item> categoryList = new HashMap<>();

    Item(String name) {
        this.name = name;
    }

    public void addItemToCategory(Category c) {
        categoryList.put(c, this);
        System.out.println("Item '" + name + "' has been added to the '" + c.name + "' Category");
    }

    public void deleteItemFromCategory(Category c) {
        categoryList.remove(c, this);
        System.out.println("Item '" + name + "' has been deleted from the '" + c.name + "' Category");
    }

    public void move(Category ori, Category newC) {
        deleteItemFromCategory(ori);
        addItemToCategory(newC);
    }

    public void displayItemsBelongsTo() {
        String s = "";
        for (Entry<Category, Item> entry : categoryList.entrySet()) {
            if (entry.getValue().equals(this)) {
                s += entry.getKey().name + " ";
            }
        }
        System.out.println("Item '" + name + "' belongs to these categories: [ " + s + "]");
    }
}

class Category {

    HashMap<Item, Category> itemList = new HashMap<>();
    String name;

    Category(String name) {
        this.name = name;
    }

    public void addItem(Item i) {
        itemList.put(i, this);
    }

    public void deleteItem(Item i) {
        itemList.remove(i, this);
    }

}

class ItemManager {

    Command command;

    ItemManager() {
        this.command = new NoCommand();
    }

    public void process() {
        this.command.execute();
    }

    public void setCommand(Command command) {
        this.command = command;
    }

}

public class CommandTest {

    public static void main(String[] args) {
        //Q2
        System.out.println("Lab 6 No2");
        Item a = new Item("Duet");
        Category cd = new Category("CD");
        Category newC = new Category("New Release");
        AddCommand addA2CD = new AddCommand(a, cd);
        AddCommand addA2New = new AddCommand(a, newC);
        ItemManager manager = new ItemManager();
        manager.setCommand(addA2CD);
        manager.process();
        manager.setCommand(addA2New);
        manager.process();
        a.displayItemsBelongsTo();

        DeleteCommand deleteA2New = new DeleteCommand(a, newC);
        manager.setCommand(deleteA2New);
        manager.process();
        a.displayItemsBelongsTo();

        //Q3
        System.out.println("Lab 6 No3");
        Item b = new Item("A Beautiful Mind");
        Category cd1 = new Category("CD");
        Category book = new Category("Book");
        AddCommand addMind2CD = new AddCommand(b,cd1);
        MoveCommand moveCD2Book = new MoveCommand(b, cd1, book);

        ItemManager manager2 = new ItemManager();
        manager2.setCommand(addMind2CD);
        manager2.process();
        b.displayItemsBelongsTo();
        manager2.setCommand(moveCD2Book);
        manager2.process();
        b.displayItemsBelongsTo();
    }

}

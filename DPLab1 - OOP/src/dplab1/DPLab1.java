/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dplab1;

/**
 *
 * @author Tan Lay Yan WIF160058
 */

// DPLab1 Q1 
interface Edible{
    
    public String howToEat();
}

abstract class Animal{
    public abstract String sound();
}

class Tiger extends Animal{
    
    @Override
    public String sound(){
        return "Tiger: RROOAARR";
    }
}

abstract class Fruit implements Edible{
}

class Chicken extends Animal implements Edible{

    @Override
    public String howToEat() {
        return "Chicken: Fry it";
    }
    
    @Override
    public String sound(){
        return "Chicken: cock-a-doodle-doo";
    }
}


class Orange extends Fruit{

    @Override
    public String howToEat() {
        return "Orange: Cut into quarter";
    }
}

class Apple extends Fruit{

    @Override
    public String howToEat() {
        return "Apple: Make apple cider";
    }
}


// DPLab1 Q2
public class DPLab1 {

// TestEdible
    
    public static void main(String[] args) {
        Object[] objects = {new Tiger(), new Chicken(), new Apple()};
        for (int i = 0; i < objects.length; i++) {
            if(objects[i] instanceof Edible){
                System.out.println(((Edible) objects[i]).howToEat());
            }
            if(objects[i] instanceof Animal){
                System.out.println(((Animal) objects[i]).sound());
            }
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpstrategyL1a;

import java.util.Scanner;

/**
 *
 * @author Leanne
 */


interface FlyBehavior{
    public void fly();
}

class FlyWithWings implements FlyBehavior{
    @Override
    public void fly(){
        System.out.println("I'm flying!!");
    }
}

class FlyNoWay implements FlyBehavior{

    @Override
    public void fly() {
        System.out.println("I can't fly");
    }
    
}

interface QuackBehavior{
    public void quack();
}

class Quack implements QuackBehavior{
    
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}

class MuteQuack implements QuackBehavior{

    @Override
    public void quack() {
        System.out.println("<< Silence >>");
    }
    
}

class Squeak implements QuackBehavior{

    @Override
    public void quack() {
        System.out.println("Squeak");
    }    
}

abstract class Duck{
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;
    
    public void swim(){
        System.out.println("I'm swimming");
    }
    
    public void performFly(){
        flyBehavior.fly();
    }
    
    public void performQuack(){
        quackBehavior.quack();
    }
    
    public void setFlyBehavior(FlyBehavior flyBehavior){
        this.flyBehavior =  flyBehavior;
    }
    
    public void setQuackBehavior(QuackBehavior quackBehavior){
        this.quackBehavior =  quackBehavior;
    }
    public abstract void display();
}

class MallardDuck extends Duck{
    MallardDuck(){
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }
    
    @Override
    public void display() {
        System.out.println("I'm a reak Mallard duck");
    }    
}

class RedheadDuck extends Duck{
    RedheadDuck(){
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }
    
    @Override
    public void display() {
        System.out.println("I'm a reak Redhead duck");
    }    
}

class RubberDuck extends Duck{
    RubberDuck(){
        flyBehavior = new FlyNoWay();
        quackBehavior = new Squeak();
    }
    
    @Override
    public void display() {
        System.out.println("I'm a Rubber duck");
    }    
}

class DecoyDuck extends Duck{
    DecoyDuck(){
        flyBehavior = new FlyNoWay();
        quackBehavior = new MuteQuack();
    }
    
    @Override
    public void display() {
        System.out.println("I'm a Decoy duck");
    }    
}

public class Lect2DPStrategy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // MallardDuck
        Duck mallardDuck = new MallardDuck();
        mallardDuck.display();
        mallardDuck.swim();
        mallardDuck.performQuack();
        mallardDuck.performFly();
        mallardDuck.setFlyBehavior(new FlyNoWay());
        mallardDuck.performFly();
        
        System.out.println("Please choose 1 of the duck [Enter 1-4], Exit please click 5");
        System.out.println("1 - Mallard Duck");
        System.out.println("2 - Redhead Duck");
        System.out.println("3 - Rubber Duck");
        System.out.println("4 - Decoy Duck");
        System.out.println("5 - Exit");
        
        Scanner in = new Scanner(System.in);
        int option = in.nextInt();
        
        while(true){
            Duck duck;
            if(option == 5){
                  break;
            }
            switch(option){
                case 1:
                    duck = new MallardDuck();
                    duck.display();
                    duck.swim();
                    duck.performQuack();
                    duck.performFly();
                    duck.setFlyBehavior(new FlyNoWay());
                    duck.performFly();
                    break;
                case 2:
                    duck = new RedheadDuck();
                    duck.display();
                    duck.swim();
                    duck.performQuack();
                    duck.performFly();
                    duck.setFlyBehavior(new FlyNoWay());
                    duck.performFly();
                    break;
                case 3:    
                    duck = new RubberDuck();
                    duck.display();
                    duck.swim();
                    duck.performQuack();
                    duck.performFly();
                    break;
                case 4:    
                    duck = new DecoyDuck();
                    duck.display();
                    duck.swim();
                    duck.performQuack();
                    duck.performFly();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
            option = in.nextInt();
        }         
        
    }
    
}
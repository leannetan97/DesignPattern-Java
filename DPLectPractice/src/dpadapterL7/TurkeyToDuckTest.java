/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpadapterL7;

/**
 *
 * @author WIF160058
 */
interface Duck {

    public void quack();

    public void fly();
}

class MallardDuck implements Duck{

    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }
    
}

interface Turkey {

    public void gobble();

    public void fly();
}

class WildTurkey implements Turkey{

    @Override
    public void gobble() {
        System.out.println("Gobble Gobble");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying a short distance");
    }
    
}

class TurkeyAdapter implements Duck{
    Turkey turkey;
    
    TurkeyAdapter(Turkey turkey){
        this.turkey = turkey;
    }
    
    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
    
}

public class TurkeyToDuckTest {

    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();
        WildTurkey turkey = new WildTurkey();
        
        System.out.println("Duck says:");
        testDuck(duck);
        
        System.out.println("\nTurkey says:");
        turkey.gobble();
        turkey.fly();
        
        System.out.println("\nDuck type turkey says:");
        TurkeyAdapter turkeyAdapter = new TurkeyAdapter(turkey);
        testDuck(turkeyAdapter);
    }
    static void testDuck(Duck d){
        d.quack();
        d.fly();
    }
}

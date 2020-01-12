/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dplab2;

/**
 *
 * @author Tan Lay Yan WIF160058
 */
interface MoveStyle{
    public void move();
}

class Driving implements MoveStyle{

    @Override
    public void move() {
        System.out.println("Now I'm driving.");
    }
    
}

class Flying implements MoveStyle{

    @Override
    public void move() {
        System.out.println("Now I'm flying.");
    }
    
}

abstract class Vehicle{
    MoveStyle moveStyle;
    
    public void setMoveStyle(MoveStyle moveStyle){
        this.moveStyle = moveStyle;
    }
    
    public abstract void display();
}

class StreetRacer extends Vehicle{
    
    StreetRacer(){
        System.out.print("I am a StreetRacer. ");
        moveStyle = new Driving();
        // or
//        setMoveStyle(new Driving());
    }
    
    @Override
    public void display() {
        moveStyle.move();
    }
    
}

class FormulaOne extends Vehicle{

    FormulaOne(){
        System.out.print("I am a FormulaOne. ");
        moveStyle = new Driving();
    }
    
    @Override
    public void display() {
        moveStyle.move();
    }
    
}

class AirCraft extends Vehicle{

    AirCraft(){
        System.out.print("I am an AirCraft. ");
        moveStyle = new Flying();
    }
    
    @Override
    public void display() {
        moveStyle.move();
    }
    
}

public class DPLab2 {

    /**
     * @param args the command line arguments
     */
    
    
//    KEYWORD !!!! WHEN TO USE STRATEGY ?
       
    
    
    public static void main(String[] args) {
        //Q3
        Vehicle v;
        for (int i = 0; i < 3; i++) {
            switch(i){
                case 0:
                    v = new StreetRacer();
                    v.display();
                    break;
                case 1:
                    v = new FormulaOne();
                    v.display();
                    break;
                case 2:
                    v = new AirCraft();
                    v.display();
                    //Q4
                    System.out.print("\tI am arriving at the runway. ");
                    v.setMoveStyle(new Driving());
                    v.display();
                    break;
                default:
                    break;
            }
            
        }
    }
    
}

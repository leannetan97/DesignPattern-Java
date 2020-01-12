/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpdecoratorL4;

/**
 *
 * @author Leanne
 */

abstract class Beverage{
    public enum Size{TALL, GRANDE, VENTI}
    String description = "Unknown Beverage";
    Size size = Size.TALL;
    
    public String getDescription(){
        return description;
    }
    
    public void setSize(Size size){
        this.size = size; 
    }
    
    public Size getSize(){
        return size; 
    }
    
    public abstract double cost();
}

class HouseBlend extends Beverage{
    
    HouseBlend(){
        description = getSize().toString() +" House Blend";
    }
    
    HouseBlend(Size size){
        super.setSize(size);
        description = getSize().toString() +" House Blend";
    }
    
    
    @Override
    public double cost() {
        return 0.89;
    }
    
}

class DarkRoast extends Beverage{
    
    DarkRoast(){
        description = getSize().toString() +" Dark Roast Coffee";
    }
    DarkRoast(Size size){
        super.setSize(size);
        description = getSize().toString() +" Dark Roast Coffee";
    }
    @Override
    public double cost() {
        return 0.99;
    }
    
}

class Espresso extends Beverage{
    
    Espresso(){
        description = getSize().toString() +" Espresso";
    }
    
    Espresso(Size size){
        super.setSize(size);
        description = getSize().toString() +" Espresso";
    }
    
    @Override
    public double cost() {
        return 1.99;
    }
    
}

class Decaf extends Beverage{
    
    Decaf(){
        description = getSize().toString() +" Decaf";
    }
    
    Decaf(Size size){
        super.setSize(size);
        description = getSize().toString() +" Decaf";
    }
    
    @Override
    public double cost() {
        return 1.05;
    }
    
}



abstract class CondimentDecorator extends Beverage{
    Beverage beverage;
    
    @Override
    public abstract String getDescription();

    // VERY IMPORTANT
    @Override
    public Size getSize(){
        return beverage.getSize();
    }
}

class Milk extends CondimentDecorator{
    
    Milk(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Milk";
    }

    @Override
    public double cost() {
        double charges = 0.10;
        if(null != beverage.getSize()){
            switch (beverage.getSize()) {
            case TALL:
                charges = 0.10;
                break;
            case GRANDE:
                charges = 0.15;
                break;
            case VENTI:
                charges = 0.20;
                break;
            default:
                break;
        }
    }
        return beverage.cost() + charges;
    }
}

class Mocha extends CondimentDecorator{
    Mocha(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        double charges = 0.20;
        switch (beverage.getSize()){
            case TALL:
                charges = 0.20;
                break;
            case GRANDE:
                charges = 0.25;
                break;
            case VENTI:
                charges = 0.30;
                break;
            default:
                break;
        }
        return beverage.cost() + charges;
    }
}

class Soy extends CondimentDecorator{
    Soy(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    @Override
    public double cost() {
        double charges = 0.15;
        switch (beverage.getSize()){
            case TALL:
                charges = 0.1;
                break;
            case GRANDE:
                charges = 0.2;
                break;
            case VENTI:
                charges = 0.25;
                break;
            default:
                break;
        }
        return beverage.cost() + charges;
    }
}

class Whip extends CondimentDecorator{
    Whip(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    @Override
    public double cost() {
        double charges = 0.10;
        switch (beverage.getSize()){
            case TALL:
                charges = 0.10;
                break;
            case GRANDE:
                charges = 0.15;
                break;
            case VENTI:
                charges = 0.20;
                break;
            default:
                break;
        }
        return beverage.cost() + charges;
    }
}


public class DecoratorMain{
    public static void main(String[] args) {
        // Only care about the final type is Beverage
        Beverage b1 = new Espresso();
        b1 = new Milk(b1);
        System.out.println(b1.getDescription() + " $" + b1.cost());
        Beverage b2 = new DarkRoast(Beverage.Size.GRANDE);
        b2 = new Whip(new Mocha(new Mocha(b2)));
        System.out.println(b2.getDescription() + " $" + b2.cost());
        Beverage b3 = new HouseBlend(Beverage.Size.VENTI);
        b3 = new Whip(new Mocha(new Soy(b3)));
        System.out.println(b3.getDescription() + " $" + String.format("%.2f", b3.cost()));
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpstateL8;

import java.util.Random;

/**
 *
 * @author Leanne
 */
interface State{
    public void insertQuarter();
    
    public void ejectQuarter();
    
    public void turnCrank();
    
    public void dispense();
}

class NoQuarterState implements State{
    GumballMachine gumballMachine;
    
    public NoQuarterState(GumballMachine gumballMachine){
        this.gumballMachine = gumballMachine;
    }
    
    @Override
    public void insertQuarter() {
        System.out.println("You inserted a quater");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("You haven't inserted a quarter.");
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned but there's no quarter");
    }

    @Override
    public void dispense() {
        System.out.println("You need to pay first");
    }    
}

class HasQuarterState implements State{
    Random randomWinner = new Random(System.currentTimeMillis());
    GumballMachine gumballMachine;
    
    public HasQuarterState(GumballMachine gumballMachine){
        this.gumballMachine = gumballMachine;
    }
    
    @Override
    public void insertQuarter() {
        System.out.println("You can't insert another quater");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Quater returned");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned...");
        int winner = randomWinner.nextInt(10);
        if((winner == 0) && (gumballMachine.getCount()>1)){
            gumballMachine.setState(gumballMachine.getWinnerState());
        }else{
            gumballMachine.setState(gumballMachine.getSoldState());
        }
    }

    @Override
    public void dispense() {
        System.out.println("No gumball dispensed");
    }
    
}

class SoldState implements State{
    GumballMachine gumballMachine;
    
    public SoldState(GumballMachine gumballMachine){
        this.gumballMachine = gumballMachine;
    }
    
    @Override
    public void insertQuarter() {
        System.out.println("Please wait, we're already giving you a gumball");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Sorry, you already turned the crank");
    }

    @Override
    public void turnCrank() {
        System.out.println("Turning twice doesn't get you another gumball");
    }

    @Override
    public void dispense() {
        System.out.println("Dispensing....");
        gumballMachine.releaseBall();
        if(gumballMachine.getCount() == 0){
            System.out.println("Oops, out of gumballs!");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }else{
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        }
    }
}



class SoldOutState implements State{
    GumballMachine gumballMachine;
    
    public SoldOutState(GumballMachine gumballMachine){
        this.gumballMachine = gumballMachine;
    }
    
    @Override
    public void insertQuarter() {
        System.out.println("You can't insert a quarter, the machine is sold out.");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("You can't eject, you haven't inserted a quarter yet.");
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned but there are no gumballs");
    }

    @Override
    public void dispense() {
        System.out.println("No gumball dispensed");
    }
}

class WinnerState implements State{
    GumballMachine gumballMachine;
    
    public WinnerState(GumballMachine gumballMachine){
        this.gumballMachine = gumballMachine;
    }
    
    @Override
    public void insertQuarter() {
        System.out.println("Please wait, we're already giving you a gumball");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Sorry, you already turned the crank");
    }

    @Override
    public void turnCrank() {
        System.out.println("Turning twice doesn't get you another gumball");
    }

    @Override
    public void dispense() {
        System.out.println("Despensing...");
        gumballMachine.releaseBall();
        if(gumballMachine.getCount() == 0){
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }else{
            System.out.println("Despensing...");
            gumballMachine.releaseBall();
            System.out.println("You are a winner ! You got 2 gumballs for your quarter");
            if(gumballMachine.getCount() > 0){
                gumballMachine.setState(gumballMachine.getNoQuarterState());
            }else{
                System.out.println("Opps, out of gumballs");
                gumballMachine.setState(gumballMachine.getSoldOutState());
            }
        }
        System.out.println("No gumball dispensed");
    }
}


class GumballMachine{
    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;
    State winnerState;
    
    State state;
    int count = 0;
    
    public GumballMachine(int numberGumballs){
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        winnerState = new WinnerState(this);
        
        this.count = numberGumballs;
        if(numberGumballs > 0){
            state = noQuarterState;
        }else{
            state = soldOutState;
        }
    }
    
    public void insertQuarter(){
        state.insertQuarter();
    }
    
    public void ejectQuarter(){
        state.ejectQuarter();
    }
    
    public void turnCrank(){
        state.turnCrank();
        state.dispense();
    }
    
    void setState(State state){
        this.state = state;
    }
    
    void releaseBall(){
        System.out.println("A gumball comes rolling out the slot...");
        if(count != 0){
            count = count - 1;
        }
    }
    
    int getCount(){
        return count;
    }

    State getSoldOutState() {
        return soldOutState;
    }

    State getNoQuarterState() {
        return noQuarterState;
    }

    State getHasQuarterState() {
        return hasQuarterState;
    }

    State getSoldState() {
        return soldState;
    }
    
    State getWinnerState(){
        return winnerState;
    }
    
    @Override
    public String toString(){
        return "Java-enable Standing Gumball Model #2004\nInventory: " + count + " gumballs\nMachine is waiting for quarter\n"; 
    }
    
}

public class GumballMachineTest {
    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(5);
        System.out.println(gumballMachine.toString());
        
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        System.out.println(gumballMachine.toString());
        
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        System.out.println(gumballMachine.toString());
        
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        
        System.out.println(gumballMachine.toString());
        
    }
}

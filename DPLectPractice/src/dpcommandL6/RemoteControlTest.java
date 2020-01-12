/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpcommandL6;

/**
 *
 * @author Leanne
 */
interface Command {

    public void execute();

    public void undo();
}

class NoCommand implements Command {

    @Override
    public void execute() {
    }

    @Override
    public void undo() {
    }
}

class GarageDoor {

    public void up() {
        System.out.println("Door Up");
    }

    public void down() {
        System.out.println("Door Down");
    }

    public void stop() {
        System.out.println("Door Stop");
    }

    public void lightOn() {
        System.out.println("Door light on");
    }

    public void lightOff() {
        System.out.println("Door Off");
    }
}

class GarageDoorOpenCommand implements Command {

    GarageDoor door;

    GarageDoorOpenCommand(GarageDoor door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.up();
    }

    @Override
    public void undo() {
        door.down();
    }
}

class GarageDoorOffCommand implements Command {

    GarageDoor door;

    GarageDoorOffCommand(GarageDoor door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.up();
    }

    @Override
    public void undo() {
        door.down();
    }
}

class Light {

    String name;

    Light(String name) {
        this.name = name;
    }

    public void on() {
        System.out.println("Light On");
    }

    public void off() {
        System.out.println("Light Off");
    }
}

class LightOnCommand implements Command {

    Light light;

    LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

class LightOffCommand implements Command {

    Light light;

    LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}

class CeilingFan {

    public static final int HIGH = 3, MEDIUM = 2, LOW = 1, OFF = 0;
    String location;
    int speed;

    public CeilingFan(String location) {
        this.location = location;
        speed = OFF;
    }
    
    public void on(){
        System.out.println("Fan on");
    }

    public void high() {
        speed = HIGH;
    }

    public void medium() {
        speed = MEDIUM;
    }

    public void low() {
        speed = LOW;
    }

    public void off() {
        speed = OFF;
    }

    public int getSpeed() {
        return speed;
    }
}

class CeilingFanOnCommand implements Command{
    CeilingFan ceilingFan;
    
    CeilingFanOnCommand(CeilingFan ceilingFan){
        this.ceilingFan = ceilingFan;
    }
    @Override
    public void execute() {
        ceilingFan.on();
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
class CeilingFanHighCommand implements Command {

    CeilingFan ceilingFan;
    int prevSpeed;

    CeilingFanHighCommand(CeilingFan ceilingFan) {
        ceilingFan = this.ceilingFan;
    }

    @Override
    public void execute() {
        prevSpeed = ceilingFan.getSpeed();
        ceilingFan.high();
    }

    @Override
    public void undo() {
        if (prevSpeed == CeilingFan.HIGH) {
            ceilingFan.high();
        } else if (prevSpeed == CeilingFan.MEDIUM) {
            ceilingFan.medium();
        } else if (prevSpeed == CeilingFan.LOW) {
            ceilingFan.low();
        } else if (prevSpeed == CeilingFan.OFF) {
            ceilingFan.off();
        }
    }
}

class Stereo {

    public void on() {
        System.out.println("Stero On");
    }

    public void off() {
        System.out.println("Stero Off");
    }

    public void setCD() {
        System.out.println("Stero setCD");
    }

    public void setDVD() {
        System.out.println("Stero setDVD");
    }

    public void setRadio() {
        System.out.println("Stero setRadio");
    }

    public void setVolumn() {
        System.out.println("Stero setVolumn");
    }
}

class StereoOnWithCDCommand implements Command {

    Stereo stereo;

    StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setCD();
    }

    @Override
    public void undo() {
        stereo.off();
    }
}

class StereoOnWithRadioCommand implements Command {

    Stereo stereo;

    StereoOnWithRadioCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setRadio();
    }

    @Override
    public void undo() {
        stereo.on();
    }
}

class StereoSetVolumeCommand implements Command {

    Stereo stereo;

    StereoSetVolumeCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.setVolumn();
    }

    @Override
    public void undo() {
        stereo.off();
    }
}

class StereoOnWithDVDCommand implements Command {

    Stereo stereo;

    StereoOnWithDVDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setDVD();
    }

    @Override
    public void undo() {
        stereo.off();
    }

    class StereoOffCommand implements Command {

        Stereo stereo;

        StereoOffCommand(Stereo stereo) {
            this.stereo = stereo;
        }

        @Override
        public void execute() {
            stereo.off();
        }

        @Override
        public void undo() {
            stereo.on();
        }
    }
}

class SimpleRemoteControl {

    Command slot;

    public void setCommand(Command command) {
        slot = command;
    }

    public void buttonWasPressed() {
        slot.execute();
    }
}

class RemoteControl {

    Command[] onCommands;
    Command[] offCommands;
    Command undoCommand;

    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];

        Command noCommand = new NoCommand();
        for (int i = 0; i < onCommands.length; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
        // Use no comment because easy for us, we are not required to check when every button is pressed
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        // We no need the check the if because we did noCommand class
//        if(onCommands[slot] != null){
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
//        }   
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
        undoCommand = onCommands[slot];
    }

    public void undoButtonWasPushed(int slot) {
        undoCommand.undo();
    }

    @Override
    public String toString() {
        StringBuffer stringBuff = new StringBuffer();
        stringBuff.append("\n-----Remote Control-----\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuff.append("[slot" + i + "] " + onCommands[i].getClass().getName() + "  " + offCommands[i].getClass().getName() + "\n");
        }
        return stringBuff.toString();
    }
}

public class RemoteControlTest {

    public static void main(String[] args) {
        // For simple remote Control
        SimpleRemoteControl remote = new SimpleRemoteControl();
        GarageDoorOpenCommand doorOnCommand0 = new GarageDoorOpenCommand(new GarageDoor());
        LightOnCommand lightOnCommand = new LightOnCommand(new Light("Living Room"));
        remote.setCommand(doorOnCommand0);
        remote.buttonWasPressed();
        remote.setCommand(lightOnCommand);
        remote.buttonWasPressed();

        // For RemoteControl
        RemoteControl remoteControl = new RemoteControl();
        Light l = new Light("Living room Light");
        LightOnCommand livingRoomLightOn = new LightOnCommand(l);
        LightOffCommand livingRoomLightOff = new LightOffCommand(l);
        Light k = new Light("Living room Light");
        LightOnCommand kitchenLightOn = new LightOnCommand(k);
        LightOffCommand kitchenLightOff = new LightOffCommand(k);

        GarageDoor g = new GarageDoor();
        GarageDoorOpenCommand doorOnCommand = new GarageDoorOpenCommand(g);
        GarageDoorOffCommand doorOffCommand = new GarageDoorOffCommand(g);
        Stereo s = new Stereo();
        StereoOnWithCDCommand streoOnWithCD = new StereoOnWithCDCommand(s);
        
        CeilingFan f = new CeilingFan("Living room");
        
//        CeilingFanOnCommand cellingFanOn = new CeilingFanOnCommand(
//        ("Kitchen"));
        
    }
}


// Look Back lecture note

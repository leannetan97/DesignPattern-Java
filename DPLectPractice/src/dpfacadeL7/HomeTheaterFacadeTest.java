/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dpfacadeL7;

/**
 *
 * @author WIF160058
 */
class Amplifier {

    public void on() {
        System.out.println("Top-O-Line Amplifier on");
    }

    public void setDvd(DvdPlayer dvd) {
        System.out.println("Top-O-Line Amplifier setting DVD player to Top-O-Line DVD Player");
    }

    public void setSurroundSound() {
        System.out.println("Top-O-Line Amplifier surround sound on (5 speaker, 1 subwoofer)");
    }

    public void setVolume(int n) {
        System.out.println("Top-O-Line Amplifier set volumn to " + n);
    }

    public void off() {
        System.out.println("Top-O-Line Amplifier off");
    }
}

class Tuner {

}

class DvdPlayer {

    private String movie;

    public void on() {
        System.out.println("Top-O-Line Dvd Player on");
    }

    public void play(String movie) {
        this.movie = movie;
        System.out.println("Top-O-Line Dvd Player playing \"" + movie + "\"");
    }

    public void stop() {
        System.out.println("Top-O-Line Dvd Player stopped playing \"" + movie + "\"");
    }

    public void eject() {
        System.out.println("Top-O-Line Dvd Player eject");
    }

    public void off() {
        System.out.println("Top-O-Line Dvd Player off");
    }

}

class CdPlayer {

}

class Projector {

    public void on() {
        System.out.println("Top-O-Line Projector on");
    }

    public void wideScreenMode() {
        System.out.println("Top-O-Line Projector in widescreen mode (16x9 aspect ratio)");
    }

    public void off() {
        System.out.println("Top-O-Line Projector off");
    }
}

class TheaterLights {

    public void dim(int n) {
        System.out.println("Theater Ceiling Lights dimming to " + n + " %");
    }

    public void on() {
        System.out.println("Theater Ceiling Lights on");
    }
}

class Screen {
    
    public void up() {
        System.out.println("Theater Screen going up");
    }
    public void down() {
        System.out.println("Theater Screen going down");
    }
}

class PopcornPopper {

    public void on() {
        System.out.println("PopcornPopper on");
    }

    public void off() {
        System.out.println("PopcornPopper off");
    }

    public void pop() {
        System.out.println("PopcornPopper pop");
    }
}

class HomeTheaterFacade{
    Amplifier amp;
    Tuner tuner;
    DvdPlayer dvd;
    CdPlayer cd;
    Projector projector;
    TheaterLights lights;
    Screen screen;
    PopcornPopper popper;

    public HomeTheaterFacade(Amplifier amp, Tuner tuner, DvdPlayer dvd, CdPlayer cd, Projector projector, TheaterLights lights, Screen screen, PopcornPopper popper) {
        this.amp = amp;
        this.tuner = tuner;
        this.dvd = dvd;
        this.cd = cd;
        this.projector = projector;
        this.lights = lights;
        this.screen = screen;
        this.popper = popper;
    }
    
    public void watchMovie(String movie){
        System.out.println("Get ready to watch a movie...");
        popper.on();
        popper.pop();
        lights.dim(10);
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setDvd(dvd);
        amp.setSurroundSound();
        amp.setVolume(5);
        dvd.on();
        dvd.play(movie);
    }
    
    public void endMovie(){
        System.out.println("Shutting movie theater down...");
        popper.off();
        lights.on();
        screen.up();
        projector.off();
        amp.off();
        dvd.stop();
        dvd.eject();
        dvd.off();
    }
}


public class HomeTheaterFacadeTest {

    public static void main(String[] args) {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(new Amplifier(), new Tuner(), new DvdPlayer(),new CdPlayer(), new Projector(), new TheaterLights(), new Screen(), new PopcornPopper());
        homeTheater.watchMovie("Raiders of the Lost Ark");
        homeTheater.endMovie();
    }
}

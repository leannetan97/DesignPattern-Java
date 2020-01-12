/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dplab4aii;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

/**
 *
 * @author Tan Lay Yan WIF160058
 */

// Lab 4aii - Factory Method



interface Logger{
    public void log(String msg);
}

class ConsoleLogger implements Logger{
    Date date = new Date();
    @Override
    public void log(String msg) {
        System.out.println(new Timestamp(date.getTime()) +"  "+ msg);
    }
}

class FileLogger implements Logger{
    Date date = new Date();
    @Override
    public void log(String msg) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("log.txt", true))) {
            bw.write(new Timestamp(date.getTime()) + " " +msg);
            bw.newLine();
            bw.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}

class LoggerFactory{
    public Logger getLogger(){
        return isFileLoggingOn()? new FileLogger() : new ConsoleLogger();
    }
    
    public boolean isFileLoggingOn(){
        Properties p = new Properties();
        try{
            p.load(ClassLoader.getSystemResourceAsStream("Logger.properties.txt"));
            String fileLoggingValue = p.getProperty("FileLogging");
            return fileLoggingValue.equalsIgnoreCase("ON");
        } catch (IOException e){
            return false;
        }
    }
}

public class LoggerTestFactoryMethod{
    
     public static void main(String[] args) {
        LoggerFactory loggerFac = new LoggerFactory();
        loggerFac.getLogger().log("hello, how are you ?");
    }  
}





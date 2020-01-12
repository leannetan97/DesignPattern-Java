/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dplab4b;

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

// Lab 4b - Singleton
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

class FileLoggerSingleton implements Logger{
    
    Date date = new Date();
    
    private static FileLoggerSingleton uniqueInstance;
    
    private FileLoggerSingleton(){
        
    }
    
    public static synchronized Logger getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new FileLoggerSingleton();
        }
        return uniqueInstance;
    }
    
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
        return isFileLoggingOn()? FileLoggerSingleton.getInstance() : new ConsoleLogger();
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

public class LoggerTestSingleton{
    
     public static void main(String[] args) {
        LoggerFactory loggerFac = new LoggerFactory();
        loggerFac.getLogger().log("hello, how are you ?");
    }  
}


// Question Ans
// Q3 -> Instead of create instantiate a new FileLogger obj, we should getInstance of the class.
// Q4 -> No, we are not required to change the client class, because just need to change the LoggerFactory.

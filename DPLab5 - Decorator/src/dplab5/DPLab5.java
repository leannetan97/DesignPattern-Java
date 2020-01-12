package dplab5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;

/**
 *
 * @author Tan Lay Yan WIF160058
 */
interface Logger {

    public void log(String msg);
}

class ConsoleLogger implements Logger {

    @Override
    public void log(String msg) {
        System.out.println(msg);
    }
}

class FileLogger implements Logger {
    
    @Override
    public void log(String msg) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("log.txt", true))) {
            bw.write(msg);
            bw.newLine();
            bw.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}

abstract class LoggerDecorator implements Logger {

    Logger logger;

    @Override
    public abstract void log(String msg);
    
}

class HTMLLogger extends LoggerDecorator {

    HTMLLogger(Logger logger) {
        this.logger = logger;
    }
    
    @Override
    public void log(String msg) {
//        logger.log("<HTML><BODY><b>"+msg+"</b></BODY></HTML>");
        logger.log(makeHTML(msg));
    }
    
    public String makeHTML(String data) {
        return "<HTML><BODY><b>" + data + "</b></BODY></HTML>";
    }
    
}

class EncryptLogger extends LoggerDecorator {

    EncryptLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String msg) {
        logger.log(encrypt(msg));
    }
    
    public String encrypt(String data) {
        StringBuilder msgStr = new StringBuilder();
        msgStr.append(data);
        msgStr.insert(0, msgStr.charAt(data.length() - 1));
        msgStr.deleteCharAt(msgStr.length() - 1);
        return msgStr.toString();
    }
    
}

class LoggerFactory {

    public Logger getLogger() {
        return isFileLoggingOn() ? new FileLogger() : new ConsoleLogger();
    }
    
    public boolean isFileLoggingOn() {
        Properties p = new Properties();
        try {
            p.load(ClassLoader.getSystemResourceAsStream("Logger.properties.txt"));
            String fileLoggingValue = p.getProperty("FileLogging");
            return fileLoggingValue.equalsIgnoreCase("ON");
        } catch (IOException e) {
            return false;
        }
    }
}

public class DPLab5 {
    
    public static void main(String[] args) {
        Logger logger = new LoggerFactory().getLogger();
        new HTMLLogger(logger).log(new Timestamp(System.currentTimeMillis()) + " hello, how are you?");
        new EncryptLogger(logger).log(new Timestamp(System.currentTimeMillis()) + " hello, how are you?");

        // Q4
        new EncryptLogger(new HTMLLogger(logger)).log("Good bye");
    }
    
}

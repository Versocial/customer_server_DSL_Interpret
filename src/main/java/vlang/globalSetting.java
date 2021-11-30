package vlang;

import jdk.dynalink.beans.StaticClass;

import java.io.IOException;
import java.util.logging.*;

public abstract class globalSetting {

    private static String logName= "vlang_interpreter";
    private static String logPath="log.txt";
    private static int logLimit=950;


    public final static Logger log=Logger.getLogger(logName);

    public static void init() throws IOException {
        FileHandler fileHandler= new FileHandler(logPath);
        fileHandler.setFormatter(new SimpleFormatter());
        log.addHandler(fileHandler);
        log.addHandler(new ConsoleHandler());
        log.setFilter((logRecord)->{return logRecord.getLevel().intValue()>logLimit;});
    }
}

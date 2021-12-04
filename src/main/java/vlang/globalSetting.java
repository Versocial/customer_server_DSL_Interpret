package vlang;

import vlang.io.easyIo.easyInputMedia;
import vlang.io.easyIo.easyNLP;
import vlang.io.easyIo.easyOutputMedia;
import vlang.io.easyIo.easyRowInput;
import vlang.io.media.inputMedia;
import vlang.io.media.outputMedia;
import vlang.io.nlp.NLP;
import vlang.io.rowInput;

import java.io.IOException;
import java.util.logging.*;

public abstract class globalSetting {

    private static final String logName= "vlang_interpreter";
    private static final String logPath= "log.txt";
    private static final int logLimit=950;

    public static final inputMedia inMedia= new easyInputMedia();
    public static final outputMedia outMedia=new easyOutputMedia();
    public static final NLP NLprocessor=new easyNLP();

    public final static Logger log=Logger.getLogger(logName);

    public static void init() throws IOException {
        FileHandler fileHandler= new FileHandler(logPath);
        fileHandler.setFormatter(new SimpleFormatter());
        log.addHandler(fileHandler);
        //log.addHandler(new ConsoleHandler());
        log.setFilter((logRecord)->{return true;});
        log.info(" log start..");
    }
}

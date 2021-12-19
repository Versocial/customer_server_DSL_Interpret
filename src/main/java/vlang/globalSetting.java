package vlang;

import vlang.interpreter.parser;
import vlang.interpreter.parsers.jsonParser;
import vlang.io.easyIo.easyInputMedia;
import vlang.io.easyIo.easyNLP;
import vlang.io.easyIo.easyOutputMedia;
import vlang.io.media.inputMedia;
import vlang.io.media.outputMedia;
import vlang.io.nlp.NLP;

import java.io.IOException;
import java.util.logging.*;

/**
 * 全局设置
 */
public abstract class globalSetting {
    /**
     * 全局信息是否已经初始化（是否执行过该类的init方法）
     */
    private static boolean initiated=false;
    /**
     * log名
     */
    private static final String logName= "vlang_interpreter";
    /**
     * 保存log文件的路径
     */
    private static final String logPath= "log.txt";

    /**
     * 输入媒体，配置后提供给{@link vlang.interpreter.globalInfo}中设置{@link vlang.io.input}类实例使用
     */
    public static final inputMedia inMedia= new easyInputMedia();
    /**
     * 输出媒体，配置后提供给{@link vlang.interpreter.globalInfo}中设置{@link vlang.io.output}类实例使用
     */
    public static final outputMedia outMedia=new easyOutputMedia();
    /**
     * 自然语言处理器，配置后提供给{@link vlang.interpreter.globalInfo}中设置{@link vlang.io.input}类实例使用
     */
    public static final NLP NLprocessor=new easyNLP();
    /**
     * 语法分析器，配置后在{@link vlang.Main}中使用
     */
    public static final parser parser=new jsonParser();
    /**
     * 日志，配置后在各个类的方法中调用
     */
    public final static Logger log=Logger.getLogger(logName);

    /**
     * 初始化，主要是关于log
     */
    public static void init()  {
        if(initiated)
            return;
        FileHandler fileHandler= null;
        try {
            fileHandler = new FileHandler(logPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileHandler.setFormatter(new SimpleFormatter());
        log.addHandler(fileHandler);
        //log.addHandler(new ConsoleHandler());
        log.setFilter((logRecord)->{return true;});
        log.info(" log start..");
        initiated=true;
    }
}

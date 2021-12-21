package vlang;

import vlang.interpreter.GlobalInfo;
import vlang.interpreter.Parser;
import vlang.interpreter.parsers.JsonParser;
import vlang.io.Input;
import vlang.io.Output;
import vlang.io.easyIo.EasyInputMedia;
import vlang.io.easyIo.EasyNLP;
import vlang.io.easyIo.EasyOutputMedia;
import vlang.io.media.InputMedia;
import vlang.io.media.OutputMedia;
import vlang.io.nlp.NLP;

import java.io.IOException;
import java.util.logging.*;

/**
 * 全局设置
 */
public abstract class GlobalSetting {
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
     * 输入媒体，配置后提供给{@link GlobalInfo}中设置{@link Input}类实例使用
     */
    public static final InputMedia inMedia= new EasyInputMedia();
    /**
     * 输出媒体，配置后提供给{@link GlobalInfo}中设置{@link Output}类实例使用
     */
    public static final OutputMedia outMedia=new EasyOutputMedia();
    /**
     * 自然语言处理器，配置后提供给{@link GlobalInfo}中设置{@link Input}类实例使用
     */
    public static final NLP NLprocessor=new EasyNLP();
    /**
     * 语法分析器，配置后在{@link vlang.Main}中使用
     */
    public static final Parser parser=new JsonParser();
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
        assert fileHandler != null;
        fileHandler.setFormatter(new SimpleFormatter());
        log.addHandler(fileHandler);
        //log.addHandler(new ConsoleHandler());
        log.setFilter((logRecord)-> true);
        log.info(" log start..");
        initiated=true;
    }
}

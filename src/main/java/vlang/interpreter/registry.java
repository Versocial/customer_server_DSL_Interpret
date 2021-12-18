package vlang.interpreter;

import vlang.interpreter.functions.askManualService;
import vlang.interpreter.functions.listen;
import vlang.interpreter.functions.speak;
import vlang.interpreter.functions.exit;

import java.util.HashMap;

/**
 * 注册，主要包含一些用在语法分析和执行模块（{@link parser}接口的实现、{@link executor}类中）使用到的字符串常量。
 */
public abstract class registry {

    /**
     * 中间脚本中的入口标记： {@value}
     */
    public final static String entry="@entry";
    /**
     * 中间脚本中的函数标记： {@value}
     */
    public final static String function="function";
    /**
     * 中间脚本中的函数参数标记： {@value} （用于各函数）
     */
    public final static String param="param";
    /**
     * 中间脚本中的goTo标记： {@value} （用于步骤跳转）
     */
    public final static String goTo="goto";
    /**
     * 执行中使用的继续执行当前步骤标记： {@value}
     */
    public final static String goOn="goon";
    /**
     * 执行中的出口标记： {@value}
     */
    public final static String exit="exit";
    /**
     * 中间脚本的时间标记： {@value} （用于listen函数）
     */
    public final static String silenceLimt="time";
    /**
     * 中间脚本的默认跳转步骤标记： {@value} （用于listen函数）
     */
    public final static String listenDefault="$@Default";
    /**
     * 中间脚本的沉默时跳转步骤标记： {@value} （用于listen函数）
     */
    public final static String listenSilence="$@Silence";
    /**
     * 执行中的输入失败标记： {@value} （用于listen函数）
     */
    public final static String listenFailure="$@failure";
    /**
     * 执行中的语法分析标记： {@value} （用于listen函数）
     */
    public final static String nlpFailure="$@nlpFailure";

    /**
     * 注册的函数 key为函数名，value为对应的默认构造函数生成的函数实例
     */
    public final static HashMap<String,?extends function> func=new HashMap<>(){{
               put(listen.name,new listen());
               put(askManualService.name,new askManualService());
               put(vlang.interpreter.functions.exit.name,new exit());
               put(speak.name,new speak());
    }};
}

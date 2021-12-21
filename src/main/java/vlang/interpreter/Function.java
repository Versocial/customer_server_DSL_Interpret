package vlang.interpreter;

import org.json.JSONObject;
import vlang.interpreter.exefactorys.ExeFactoryByJson;

import java.util.ArrayList;

/**
 * 函数类，其实例为{@link Step}的一个步骤。
 */
public abstract class Function {
    /**
     * 执行
     * @param globalInfo 全局信息，例如用户的个人信息等。
     * @return 返回的字符串表示下一个进行的步骤 {@link Step}或表示继续进行当前步骤。
     */
    public abstract String exe(GlobalInfo globalInfo);

    /**
     * 通过JsonObject 构建一个 function实例
     * @param jsonObject 构建function实例使用的JSONObject实例，在由可被vlang执行器执行的json脚本构造vlang执行器executor时使用。
     *                   <br>参见{@link ExeFactoryByJson}的createBy方法。
     * @return 构建出的function实例。
     */
    public abstract Function buildByJson(JSONObject jsonObject);

    /**
     * 通过一个字符串数组构造 可以构建该function 的JSONObject，在vlang语法分析器将源文件转化为json格式的可被执行脚本时使用。
     * <br>参见{@link vlang.interpreter.parsers.rowJsonParser.rowJsonParser}的parse方法。
     * @param input 字符串数组
     * @return 构建出的JSONObject实例。
     */
    public abstract JSONObject buildJson(ArrayList<String> input);

    /**
     * 检查构建出的JsonObject是否没有错误。
     * <br>例如，检查listen函数各Branch跳转到的Step是否已定义。
     * @param func 由源文件构建出的JSONObject中的表示该函数的部分。
     * @param executor 由源文件构建出的整个JSONObject。
     * @return 若有错误返回true，否则返回false。
     */
    public abstract boolean hasErrorByJson(JSONObject func, JSONObject executor);

    /**
     * 该函数是否可能作为一个步骤{@link Step}的最后一个函数。
     * @return 若可能返回true，否则返回false。
     */
    public abstract boolean canBeEndFunction();

    /**
     * 该函数是否可能作为一个步骤的中间或开头函数（非最后一个函数）。
     * @return 若可能返回true，否则返回false。
     */
    public abstract boolean canBeNotEndFunction();
}

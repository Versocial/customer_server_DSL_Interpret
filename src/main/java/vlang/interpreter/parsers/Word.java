package vlang.interpreter.parsers;

import vlang.interpreter.Function;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提供给{@link Function} 的各种实现在由源文件构造JSONObject的buildByJson方法中将给到的字符串序列转化为word（token）序列的功能。
 */
public class Word {

    /**
     * word的类型
     */
    public enum Type {
        id, num, string, unknowns, branch, var
    }

    /**
     * word的类型对应的正则表达式
     */
    private static final HashMap<String, Type> patterns = new HashMap<>() {{
        put("^[0-9]+$", Type.num);
        put("^\\$[A-Za-z]+$", Type.var);
        put("^[A-Za-z]+$", Type.id);
        put("^\"[A-Za-z0-9\\s\u4E00-\u9FA5!?.,]*\"$", Type.string);
        put("^Silence$|^Branch$|^Default$", Type.branch);
    }};

    /**
     * 该word的类型
     */
    private Type type;
    /**
     * 该word的字符串表示
     */
    private String info;

    /**
     * 构造函数，由源文件中识别到的字符串构造word
     *
     * @param info 该word在源文件中对应的字符串
     */
    public Word(String info) {
        this.info = info;
        this.type = Type.unknowns;
        //判断word应有的类型
        for (String pattern : patterns.keySet()) {
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(info);
            if (m.find()) {
                this.type = patterns.get(pattern);
            }
        }
        //字符串去掉收尾引号
        if (type == Type.string)
            this.info = info.substring(1, info.length() - 1);
        //变量去掉开头的‘$’
        if (type == Type.var)
            this.info = info.substring(1);
    }

    /**
     * 获取该word的类型
     *
     * @return 该word的类型
     */
    public Type getType() {
        return type;
    }

    /**
     * 获取该word的字符串表示
     *
     * @return 该word的字符串表示
     */
    public String getInfo() {
        return info;
    }

    /**
     * 重设该word的字符串表示
     *
     * @param info 该word的字符串表示
     */
    public void setInfo(String info) {
        this.info = info;
    }
}

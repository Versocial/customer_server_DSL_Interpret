package vlang.io.easyIo;

import vlang.io.rowInput;

/**
 * 该类是{@link rowInput}的一个简单实现类，类型固定为“String”的实现类。
 */
public class easyRowInput implements rowInput {
    /**
     * 存储原始输入的字符串
     */
    private String information;

    /**
     * 构造函数
     * @param information 原始输入的字符串
     */
    public easyRowInput(String information) {
        this.information = information;
    }

    /**
     * 以字符串格式获取原始输入
     * @return 原始输入的字符串
     */
    @Override
    public String getString() {
        return information;
    }

    /**
     * 类型总是为字符串
     * @return 返回值“String”标明是字符串类型
     */
    @Override
    public String getType() {
        return "String";
    }
}

package vlang.interpreter;

import vlang.globalSetting;
import vlang.io.input;
import vlang.io.output;

/**
 * 全局信息，待扩展
 * <br>可以包含 用户信息：id、名称、账单等。
 * <br>可以包含 公共信息：日期、人工客服热线号码等。
 * <br>可以包含输入媒体、输出媒体的设置。
 */
public class globalInfo {
    /**
     * 用户信息
     */
    clientInfo clientInfo;
    /**
     * 设置给{@link executor}的输入类实例
     */
    input in;
    /**
     * 设置给{@link executor}的输出类实例
     */
    output out;

    /**
     * 构造函数，配置时默认用{@link globalSetting}中的输入输出媒体和自然语言处理模块
     */
    public globalInfo(){
        clientInfo= new clientInfo();
        in = new input(globalSetting.NLprocessor,globalSetting.inMedia);
        out =new output(globalSetting.outMedia);
    }

    /**
     * 用户信息
     */
    static class clientInfo {
        String ID;
        String name;
        String bill;
        String birth;
    }

    /**
     * 输出
     * @return 输出，即该类的成员out
     */
    public output getOut(){
        return this.out;
    }

    /**
     * 输入
     * @return 输出，即该类的成员in
     */
    public input getIn() {
        return in;
    }
}

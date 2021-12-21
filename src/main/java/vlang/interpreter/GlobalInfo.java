package vlang.interpreter;

import vlang.GlobalSetting;
import vlang.io.Input;
import vlang.io.Output;

/**
 * 全局信息，待扩展
 * <br>可以包含 用户信息：id、名称、账单等。
 * <br>可以包含 公共信息：日期、人工客服热线号码等。
 * <br>可以包含输入媒体、输出媒体的设置。
 */
public class GlobalInfo {
    /**
     * 用户信息
     */
    ClientInfo clientInfo;
    /**
     * 设置给{@link Executor}的输入类实例
     */
    Input in;
    /**
     * 设置给{@link Executor}的输出类实例
     */
    Output out;

    /**
     * 构造函数，配置时默认用{@link GlobalSetting}中的输入输出媒体和自然语言处理模块.
     */
    public GlobalInfo(int clientID) {
        clientInfo = new ClientInfo(clientID);
        in = new Input(GlobalSetting.NLprocessor, GlobalSetting.inMedia);
        out = new Output(GlobalSetting.outMedia);
    }


    /**
     * 输出
     *
     * @return 输出，即该类的成员out
     */
    public Output getOut() {
        return this.out;
    }

    /**
     * 输入
     *
     * @return 输出，即该类的成员in
     */
    public Input getIn() {
        return in;
    }

    /**
     * 用户信息
     *
     * @return 用户信息，该类的成员clientInfo
     */
    public ClientInfo clientInfo() {
        return clientInfo;
    }

    /**
     * 在执行器执行开始时使用
     */
    public void start() {
        in.open();
        out.open();
    }

    /**
     * 在执行器执行结束时使用
     */
    public void finish() {
        in.close();
        out.close();
    }
}

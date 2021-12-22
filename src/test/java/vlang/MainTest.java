package vlang;

import org.junit.jupiter.api.Test;
import vlang.interpreter.ClientInfo;
import vlang.interpreter.Executor;
import vlang.interpreter.GlobalInfo;
import vlang.interpreter.exefactorys.ExeFactoryByJson;
import vlang.io.Input;
import vlang.io.Output;
import vlang.io.media.InputMedia;
import vlang.test.testInputMedia;

class MainTest extends Main {
    InputMedia testInputMedia = new testInputMedia("src\\test\\resources\\input.txt");
    GlobalInfo globalInfo = new GlobalInfo(new ClientInfo(100), new Input(GlobalSetting.NLprocessor, testInputMedia), new Output(GlobalSetting.outMedia));

    @Test
    void testMain() {
        //执行
        Executor e = new ExeFactoryByJson().createBy("src\\test\\resources\\out.json");
        Thread exe = new Thread(e.runner(globalInfo));
        exe.start();
        try {
            exe.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        GlobalSetting.log.info("finish a dialog.");
    }
}
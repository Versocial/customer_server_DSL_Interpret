package vlang;

import org.junit.jupiter.api.Test;
import vlang.interpreter.ClientInfo;
import vlang.interpreter.Executor;
import vlang.interpreter.GlobalInfo;
import vlang.interpreter.exefactorys.ExeFactoryByJson;
import vlang.interpreter.parsers.JsonParser;
import vlang.io.Input;
import vlang.io.Output;
import vlang.io.media.InputMedia;
import vlang.test.testInputMedia;

import java.util.ArrayList;

class MainTest extends Main {
    InputMedia testInputMedia = new testInputMedia("src\\test\\resources\\input.txt");
    GlobalInfo globalInfo = new GlobalInfo(new ClientInfo(100), new Input(GlobalSetting.NLprocessor, testInputMedia), new Output(GlobalSetting.outMedia));
    /**
     * 待测试脚本路径
     */
    private ArrayList<String> Paths = new ArrayList<>() {{
        add("test.txt");
    }};

    @Test
    void testMain() {
        for (String path : Paths) {
            JsonParser jsonParser = new JsonParser();
            jsonParser.parse("src\\test\\resources\\" + path, "src\\test\\resources\\out.json");
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
}
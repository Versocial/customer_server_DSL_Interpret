package vlang.interpreter.functions;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import vlang.interpreter.GlobalInfo;
import vlang.interpreter.parsers.Word;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SpeakTest {
    private Speak speak = new Speak();
    private ArrayList<String> input = new ArrayList<>() {{
        add("\"欢迎\"");
        add("$name");
        add("\"先生，您的账单为 \"");
        add("$bill");
        add("\"欢迎您再次光临！\"");
    }};
    private GlobalInfo globalInfo = new GlobalInfo(100);

    @Test
    void exe() {
        globalInfo.start();
        ((Speak) speak.buildByJson(speak.buildJson(input))).exe(globalInfo);
        globalInfo.finish();
    }

    @Test
    void buildByJson() {
        Speak test = (Speak) speak.buildByJson(speak.buildJson(input));
        for (Word word : test.toSpeak)
            System.out.println("[" + word.getType() + ":" + word.getInfo() + "]");
        System.out.println(test);
    }

    @Test
    void buildJson() {
        JSONObject jsonObject = speak.buildJson(input);
        System.out.println(jsonObject);
    }

}
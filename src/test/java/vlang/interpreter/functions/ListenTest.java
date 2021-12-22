package vlang.interpreter.functions;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import vlang.interpreter.GlobalInfo;
import vlang.interpreter.parsers.Word;
import vlang.io.vlangIOException;

import java.util.ArrayList;

class ListenTest {
    private Listen listen = new Listen();
    private ArrayList<String> input = new ArrayList<>() {{
        add("5");
        add("Branch");
        add("\"挂了\"");
        add("thanks");
        add("Branch");
        add("\"账单\"");
        add("bill");
        add("Default");
        add("exit");
        add("Silence");
        add("silence");
    }};
    private GlobalInfo globalInfo = new GlobalInfo(100);

    @Test
    void exe() throws vlangIOException {
        globalInfo.start();
        String ans = ((Listen) listen.buildByJson(listen.buildJson(input))).exe(globalInfo);
        System.out.println(ans);
        globalInfo.finish();
    }

    @Test
    void buildByJson() {
        Listen test = (Listen) listen.buildByJson(listen.buildJson(input));
        for (String k : test.targets.keySet())
            System.out.println("[" + k + ":" + test.targets.get(k) + "]");
        System.out.println(test);
    }

    @Test
    void buildJson() {
        JSONObject jsonObject = listen.buildJson(input);
        System.out.println(jsonObject);
    }


}
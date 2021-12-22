package vlang.interpreter;

import vlang.interpreter.exefactorys.ExeFactoryByJson;
import vlang.interpreter.parsers.Word;

import static org.junit.jupiter.api.Assertions.*;

class ExecutorTest {
    public static void main(String[] args) {
        Executor executor = new ExeFactoryByJson().createBy("src\\test\\resources\\out.json");
        System.out.println("The Entry: " + executor.entryStep);
        for (String key : executor.steps.keySet())
            System.out.println("[" + key + ":" + executor.steps.get(key) + "]");
        System.out.println(executor.steps);
    }
}
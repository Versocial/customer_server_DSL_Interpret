package vlang.interpreter.exefactorys;

import org.json.JSONArray;
import org.json.JSONObject;
import vlang.GlobalSetting;
import vlang.interpreter.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 该类的功能是：读取待执行脚本，生成对应的执行器。
 */
public class ExeFactoryByJson implements ExecutorFactory {
    /**
     * @param path {@link Parser}分析后输出的中间脚本的路径。
     * @return 生成的执行器
     */
    @Override
    public Executor createBy(String path) {
        //打开中间脚本
        Scanner scanner = null;
        if (!new File(path).isFile())
            GlobalSetting.log.warning("Can not open file: " + path);
        else
            GlobalSetting.log.info("Open file Successfully:" + path);

        try {
            scanner = new Scanner(new File(path), StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
        GlobalSetting.log.info("starting interpreter by " + path + "..");
        Executor newExecutor = new Executor();
        //读出脚本中所有内容并转为JSONObject格式
        StringBuilder str = new StringBuilder();
        while (true) {
            assert scanner != null;
            if (!scanner.hasNextLine()) break;
            str.append(scanner.nextLine());
        }
        JSONObject jsonObject = new JSONObject(str.toString());

        for (String key : jsonObject.keySet()) {
            if (key.equals(Registry.entry))//设置入口步骤（Step）
                newExecutor.setEntryStep(jsonObject.getString(key));
            else {
                Step step = new Step(key);
                JSONArray jsonArray = jsonObject.getJSONArray(key);
                for (int i = 0; i < jsonArray.length(); i++) {//生成每个步骤的各个函数
                    JSONObject json = (JSONObject) jsonArray.get(i);
                    step.addFunction(Registry.func.get(json.getString(Registry.function)).buildByJson(json));
                }
                newExecutor.addStep(step);
            }
        }

        return newExecutor;
    }
}

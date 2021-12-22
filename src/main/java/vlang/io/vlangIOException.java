package vlang.io;


/**
 * 用于在output或input出现故障时引发Exception。
 * <br>例如用于{@link vlang.interpreter.Executor}的exe方法中：
 * <br>当input/output初始化失败时报错并直接退出exe方法。
 */
public class vlangIOException extends Exception {

    public vlangIOException() {
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

}

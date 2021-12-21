package vlang.interpreter;

/**
 * 该类用于构造vlang执行器
 */
public interface ExecutorFactory
{
    /**
     * 构造vlang执行器
     * @param path {@link Parser}分析后输出的中间脚本的路径。
     * @return 构造出的vlang执行器
     */
    Executor createBy(String path);
}

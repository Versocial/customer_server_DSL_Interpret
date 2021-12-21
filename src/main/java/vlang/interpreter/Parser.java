package vlang.interpreter;

/**
 * 语法分析器，将vlang源文件转化为vlang执行器可执行的脚本。
 */
public interface Parser {
    /**
     * 分析函数
     * @param inPath 输入的源文件路径
     * @param outPath 输出的可被执行器执行的脚本路径
     */
    public boolean parse(String inPath,String outPath) ;

}

package vlang.interpreter;

public interface interpreterFactory
{
    executor createBy(String path);
}

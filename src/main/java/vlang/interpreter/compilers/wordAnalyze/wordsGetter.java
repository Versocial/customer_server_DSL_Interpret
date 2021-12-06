package vlang.interpreter.compilers.wordAnalyze;

public interface wordsGetter {
    public word get();
    public void unget(word w);
}

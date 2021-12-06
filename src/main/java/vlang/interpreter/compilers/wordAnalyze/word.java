package vlang.interpreter.compilers.wordAnalyze;

public class word {
    enum Type{
        id,num,str,key,symbol,unkown
    }
    Type type;
    String info;
    public word(Type type,String info){
        this.info=info;
        this.type=type;
    }
}

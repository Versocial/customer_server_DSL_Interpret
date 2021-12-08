package vlang.interpreter.parsers;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class word {

    public enum Type{
        id,num, string, unknowns,branch
    }
    private static final HashMap<String,Type> patterns=new HashMap<>(){{
        put("[0-9]+",Type.num);
        put("[A-Za-z]+",Type.id);
        put("\"[\u4E00-\u9FA5^\"\\w\\d!?.,]*\"",Type.string);
        put("Silence|Branch|Default",Type.branch);
    }};

    private Type type;
    private String info;

    public word(String info){
        this.info=info;
        this.type=Type.unknowns;
        for(String pattern:patterns.keySet()){
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(info);
            if(m.find()){
                this.type=patterns.get(pattern);
            }
        }
        if(type==Type.string)
            this.info=info.substring(1,info.length()-1);
    }

    public Type getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }
}

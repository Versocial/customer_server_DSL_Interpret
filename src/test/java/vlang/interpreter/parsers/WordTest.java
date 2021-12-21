package vlang.interpreter.parsers;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {
    private Word.Type testType;
    private static final String alpha="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final HashMap<String ,Word.Type> testTable=new HashMap<>(){{
       put("”这个是一个未知的类型”", Word.Type.unknowns);
       put("\"字符串a1bTwoc三\"", Word.Type.string);
       put("ThanksStep", Word.Type.id);
       put("12365", Word.Type.num);
       put("$name", Word.Type.var);
       put("Branch", Word.Type.branch);
       put("Silence", Word.Type.branch);
       put("Default", Word.Type.branch);
       put("\"字符串包含所有字母 "+alpha+" 和符号 \t!?.,\"", Word.Type.string);
    }};

    @Test
    void getType() {
        for( String k :testTable.keySet()){
            Word testWord= new Word(k);
            if (testWord.getType()==testTable.get(k))
                System.out.println("Tested String :"+k);
            else
                System.out.println("Tested String :"+k+" Fail as "+testWord.getType());
        }
    }


}
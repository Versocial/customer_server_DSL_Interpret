package vlang.io;

import vlang.io.easyIo.easyInputMedia;
import vlang.io.easyIo.easyNLP;
import vlang.io.media.inputMedia;
import vlang.io.nlp.NLP;

import java.io.IOException;
import java.util.ArrayList;

public class input {
    NLP NLProcessor=new easyNLP();
    inputMedia media=new easyInputMedia();
    public analyzedInput gets(int listenTime, ArrayList<String> targets) {
        analyzedInput ans=new analyzedInput( "Default");
        try {
            ans= NLProcessor.analyzeResult(media.gets(5),targets);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

}

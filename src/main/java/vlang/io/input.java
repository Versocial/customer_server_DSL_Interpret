package vlang.io;

import vlang.globalSetting;
import vlang.interpreter.registry;
import vlang.io.easyIo.easyInputMedia;
import vlang.io.easyIo.easyNLP;
import vlang.io.media.inputMedia;
import vlang.io.nlp.NLP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class input {
    NLP NLProcessor;
    inputMedia media;

    public input(NLP NLProcessor, inputMedia media){
        this.NLProcessor=NLProcessor;
        this.media=media;
    }

    public analyzedInput gets(int listenTime, Set<String> targets) {
        analyzedInput ans=new analyzedInput(registry.listenDefault);
        try {
            ans= NLProcessor.analyzeResult(media.gets(5),targets);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

}

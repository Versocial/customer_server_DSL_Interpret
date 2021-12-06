package vlang.interpreter.compilers.wordAnalyze;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class easyWordsGetter implements wordsGetter{
    private Queue<word> queue=new LinkedList<>();
    Scanner scanner;

    public  easyWordsGetter(Scanner scanner){
        this.scanner=scanner;
    }

    @Override
    public word get() {
        if(queue.isEmpty()) {
            word w= new word(word.Type.unkown,"..");

            return w;
        }
        else
            return queue.poll();
    }

    @Override
    public void unget(word w) {
        queue.add(w);
    }
}

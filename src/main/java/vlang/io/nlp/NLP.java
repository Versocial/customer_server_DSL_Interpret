package vlang.io.nlp;

import vlang.io.analyzedInput;
import vlang.io.rowInput;

import java.util.ArrayList;

public interface NLP<rowInputClass extends rowInput > {
    public abstract analyzedInput analyzeResult(rowInputClass input, ArrayList<String> targets);
}

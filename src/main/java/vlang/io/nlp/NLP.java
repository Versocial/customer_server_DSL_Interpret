package vlang.io.nlp;

import vlang.io.analyzedInput;
import vlang.io.rowInput;

import java.util.ArrayList;
import java.util.Set;

public interface NLP<rowInputClass extends rowInput > {
    public abstract analyzedInput analyzeResult(rowInputClass input, Set<String> targets);
}

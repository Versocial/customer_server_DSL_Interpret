package vlang.interpreter;

import java.io.IOException;

public interface parser {
    public void parse(String inPath,String outPath) throws IOException;
}

package vlang.io.media;

import vlang.io.rowInput;

import java.io.IOException;

public interface inputMedia<rowInputClass extends rowInput> {
    public rowInputClass gets(int silenceTime) throws IOException;
}

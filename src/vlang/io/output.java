package vlang.io;

import vlang.io.media.outputMedia;

public abstract class output {
    vlang.io.media.outputMedia outputMedia;
    public void puts(String info){
        outputMedia.puts(info);
    };
}

package vlang.io;
import  vlang.io.media.outputMedia;
import vlang.globalSetting;

public class output {
    outputMedia outputMedia;
    public output(outputMedia outputMedia){
        this.outputMedia=outputMedia;
    }

    public void puts(String info){
        outputMedia.puts(info);
    };
}

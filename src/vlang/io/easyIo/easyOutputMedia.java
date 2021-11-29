package vlang.io.easyIo;

import vlang.io.media.outputMedia;

public class easyOutputMedia implements outputMedia {
    @Override
    public void puts(String output) {
        System.out.println(output);
    }
}

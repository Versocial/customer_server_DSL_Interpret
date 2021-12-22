
package vlang.test;

import vlang.io.RowInput;
import vlang.io.easyIo.EasyRowInput;
import vlang.io.media.InputMedia;
import vlang.io.vlangIOException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class testInputMedia implements InputMedia {
    Scanner scanner;
    String path = null;

    public testInputMedia(String path) {
        this.path = path;
    }

    @Override
    public RowInput gets(long silenceTime) {
        return new EasyRowInput(scanner.nextLine());
    }

    @Override
    public void open() throws vlangIOException {
        if (path == null)
            scanner = new Scanner(System.in);
        else {
            try {
                scanner = new Scanner(new BufferedInputStream(new FileInputStream(path)));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new vlangIOException();
            }
        }
    }

    @Override
    public void close() {
        scanner.close();
    }
}

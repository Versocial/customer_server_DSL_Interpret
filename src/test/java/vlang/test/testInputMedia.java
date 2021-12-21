package vlang.test;

import vlang.io.RowInput;
import vlang.io.easyIo.EasyRowInput;
import vlang.io.media.InputMedia;

import java.util.Scanner;

public class testInputMedia implements InputMedia {
    Scanner scanner;

    @Override
    public RowInput gets(long silenceTime) {
        return new EasyRowInput(scanner.nextLine());
    }

    @Override
    public void open() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void close() {
        scanner.close();
    }
}

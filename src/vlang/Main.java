package vlang;

import vlang.io.easyIo.easyInputMedia;
import vlang.io.input;

import java.util.Timer;

import java.util.TimerTask;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        easyInputMedia in = new easyInputMedia();
        System.out.println("ans="+in.gets(5).getString());
    }
}
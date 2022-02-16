/**
 * Name: Jiaming Liu
 * Period: 2
 **/

import java.io.*;
import java.util.*;


class KeyboardListener {
    private static final int[] ARROW_KEYCODES = {65, 66, 67, 68};

    private static boolean isArrow(int[] keycodes, int lastIdx) {
        return keycodes[(lastIdx + 1) % 3] == 27 && keycodes[(lastIdx + 2) % 3] == 91 && Arrays.stream(ARROW_KEYCODES).anyMatch(i -> i == keycodes[lastIdx]);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
        Runtime.getRuntime().exec(cmd).waitFor();
        Console console = System.console();
        Reader reader = console.reader();
        int[] inputs = new int[3];  // arrow keys requires 3 slots
        int idx = 0;
        while (true) {
            inputs[idx] = reader.read();
            if (isArrow(inputs, idx)) {
                System.out.println("ARROW!");
            }
            idx = (idx + 1) % 3;
        }
//        cmd = new String[]{"/bin/sh", "-c", "stty sane </dev/tty"};
//        Runtime.getRuntime().exec(cmd).waitFor();
//        System.out.println(Arrays.toString(inputs));
    }
}
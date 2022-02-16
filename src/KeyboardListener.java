/**
 * Name: Jiaming Liu
 * Period: 2
 **/

import java.io.*;
import java.util.*;


public class KeyboardListener {
    public static boolean isListening = true;

    public static void main(String[] args) throws IOException, InterruptedException {
        String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
        Runtime.getRuntime().exec(cmd).waitFor();
        Console console = System.console();
        Reader reader = console.reader();
        int[] inputs = new int[3];  // arrow keys requires 3 slots
        int idx = 0;
        while (isListening) {
            inputs[idx] = reader.read();
            if (Arrow.isArrow(inputs, idx)) {

                /*
                OKAY THIS IS WHERE THE MAGIC HAPPENS
                GameScreen.arrowPressed(Arrow.convert(inputs[idx]));
                the "Arrow.convert" returns an ENUM type of "arrow"
                 */

            }
            idx = (idx + 1) % 3;
        }
//        cmd = new String[]{"/bin/sh", "-c", "stty sane </dev/tty"};
//        Runtime.getRuntime().exec(cmd).waitFor();
//        System.out.println(Arrays.toString(inputs));
    }
}


enum Arrow {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    INVALID;

    private static final int[] ARROW_KEYCODES = {65, 66, 67, 68};

    public static boolean isArrow(int[] keycodes, int lastIdx) {
        return keycodes[(lastIdx + 1) % 3] == 27 && keycodes[(lastIdx + 2) % 3] == 91 && Arrays.stream(ARROW_KEYCODES).anyMatch(i -> i == keycodes[lastIdx]);
    }

    public static Arrow convert(int lastKeycode) {
        return switch (lastKeycode) {
            case 65 -> UP;
            case 66 -> DOWN;
            case 67 -> RIGHT;
            case 68 -> LEFT;
            default -> INVALID;
        };
    }
}
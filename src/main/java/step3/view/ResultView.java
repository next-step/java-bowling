package step3.view;

import java.util.List;
import step3.domain.Frame;
import step3.domain.Frames;

public class ResultView {

    public static void printHeader() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    public static void printResult(Frames frames, Frame frame) {
        frames.getFrames()
            .stream()
            .map(frame1 -> frame1.getSymbol())
            .forEach(s -> System.out.print("|  " + s + " |"));
        System.out.println();
    }

    public static void printUserName(String userName) {
        System.out.println("|  PJS |");
    }
}

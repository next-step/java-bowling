package bowling;

import java.util.List;

public class ResultView {

    public static void printResult(UserName name, Frames frames) {
        printRoundTemplate();
        printUserName(name);
        printFrames(frames);
    }

    private static void printRoundTemplate() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private static void printUserName(UserName name) {
        System.out.print("|");
        System.out.printf("%5s ",name.getName());
    }

    private static void printFrames(Frames frames) {
        for (Frame frame : frames.getValues()) {
            List<Pin> scores = frame.getScores();
            for (Pin score : scores) {
                System.out.printf("|  %-3s ", score.getFalledPins());
            }
        }

        int emptyFrame = FinalFrame.FINAL_FRAME_NUMBER + 1 - frames.size();
        for (int i = 0; i < emptyFrame; i++) {
            System.out.print("|      ");
        }
        System.out.println();
    }
}

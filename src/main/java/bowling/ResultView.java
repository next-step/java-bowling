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
            if (frame.isFinalFrame()) {
                System.out.printf("|  %-3s ", printFinalFrame(frame));
                return;
            }
            System.out.printf("|  %-3s ", printNormalFrame(frame));
        }

        int emptyFrame = FinalFrame.FINAL_FRAME_NUMBER + 1 - frames.size();
        for (int i = 0; i < emptyFrame; i++) {
            System.out.print("|      ");
        }
        System.out.println();
    }

    private static String printNormalFrame(Frame frame) {
        List<Pin> scores = frame.getScores();
        StringBuilder output = new StringBuilder();

        if (frame.isFinished()) {
            if (scores.size() == Score.FIRST_ROUND) {
                return printPinStatus(Score.FIRST_ROUND, scores.get(0));
            }
            output.append(printPinStatus(scores.size(), scores.get(0)));
            output.append("|");
            output.append(printPinStatus(scores.size(), scores.get(1)));
            return output.toString();
        }
        return printPinStatus(scores.size(), scores.get(0));
    }

    public static String printFinalFrame(Frame frame) {
        List<Pin> scores = frame.getScores();
        StringBuilder output = new StringBuilder();

        if (frame.isFinished()) {

        }
    }

    private static String printPinStatus(int scoreSize, Pin pin) {
        if (scoreSize == Score.FIRST_ROUND && pin.isMax()) {
            return "X";
        }
        if (pin.isGutter()) {
            return "-";
        }
        if (pin.isMiss()) {
            return String.valueOf(pin.getFalledPins());
        }
        return "/";
    }
}

package bowling;

import java.util.List;

public class ResultView {

    public static void printResult(String name, List<Frame> frames) {
        printRoundTemplate();
        printUserName(name);
        printFrames(frames);
    }

    public static void printResult(String name, Frames frames) {
        printRoundTemplate();
        printUserName(name);
        printFrames(frames);
    }

    private static void printRoundTemplate() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    private static void printUserName(String name) {
        System.out.print("|");
        System.out.printf("%5s ",name);
    }

    private static void printFrames(List<Frame> frames) {
        for (Frame frame : frames) {
            System.out.printf("|  %-3s ", printFrame(frame));
        }
        int emptyFrameSize = 11 - frames.size();
        for (int i = 0; i < emptyFrameSize; i++) {
            System.out.print("|      ");
        }
        System.out.println();
    }

    private static void printFrames(Frames frames) {
        for (Frame frame : frames.getFrames()) {
            System.out.printf("|  %-3s ", printFrame(frame));
        }
        int emptyFrameSize = 11 - frames.size();
        for (int i = 0; i < emptyFrameSize; i++) {
            System.out.print("|      ");
        }
        System.out.println();
    }

    private static String printFrame(Frame frame) {
        // 문제점: -|1   -|-  불가
        if (frame.firstPins() == 10) {
            return "X";
        }

        if (frame.secondPins() == 10) {
            return frame.firstPins() + "|/";
        }

        if ((!frame.isFinished() && frame.firstPins() == 0) || (frame.isFinished() && frame.secondPins() == 0)) {
            return "-";
        }

        if (frame.isFinished() && !frame.isFinalFrame()) {
            return frame.firstPins() + "|" + frame.secondPins();
        }

        if (frame.isFinished() && frame.isFinalFrame()) {
            return frame.firstPins() + "|" + frame.secondPins() + "|" + frame.getScore();
        }

        return String.valueOf(frame.getScore());
    }
}

package bowling.view;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ResultView {
    private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final int FRAME_MAX_SIZE = 10;
    private static final int EACH_STRING_MAX_SIZE = 6;
    private static final String SEPARATOR = "|";
    private static final String BLANK = " ";

    public static void printScoreBoard(String playerName, Frames frames, List<Integer> scores) {
        System.out.println();
        System.out.println(HEADER);

        System.out.print(SEPARATOR);
        printText(playerName);
        printFrames(frames.getFrames());
        printRemainDivider(frames.size());
        System.out.print(SEPARATOR);
        printText("");
        printScores(scores);
        printRemainDivider(scores.size());
    }

    private static void printFrames(List<Frame> frames) {
        frames.forEach(frame -> printText(frame.getFallenPins()));
    }

    public static void empty(String name) {
        printScoreBoard(name, new Frames(), new ArrayList<>());
    }

    private static void printRemainDivider(int size) {
        for (int i = size; i< FRAME_MAX_SIZE; i++) {
            printText("");
        }
        System.out.println();
    }

    private static void printText(String str) {
        System.out.print(getCenterAligned(str, EACH_STRING_MAX_SIZE));
        System.out.print(SEPARATOR);
    }

    private static void printScores(List<Integer> scores) {
        scores.forEach(score -> printText(String.valueOf(score)));
    }

    private static String getCenterAligned(String str, int size) {
        StringBuilder sb = new StringBuilder(size);
        appendPad(sb, (size - str.length()) / 2);
        sb.append(str);
        appendPad(sb, (size - sb.length()));
        return sb.toString();
    }

    private static void appendPad(StringBuilder sb, int range) {
        IntStream.range(0, range)
                .forEach(index -> sb.append(BLANK));
    }

}

package bowling.view;

import java.util.stream.Collectors;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.domain.Score;

public class ResultView {
    private static final String SEPARATOR = "|";
    private static final String BLANK = "   ";

    public static void printBoard(Frames frames, Player player) {
        System.out.print(SEPARATOR + "  NAME  " + SEPARATOR);
        System.out.print(getFrameName(frames) + SEPARATOR);
        System.out.println();
        System.out.print(SEPARATOR + String.format("%7s ", player.toString()));
        System.out.print(getMark(frames));
        System.out.print(SEPARATOR);
        System.out.println();
        System.out.print(SEPARATOR + "        " + SEPARATOR);
        System.out.print(getScore(frames) + SEPARATOR);
        System.out.println();
        System.out.println();

    }

    private static String getScore(Frames frames) {

        return frames.frames().stream().map(frame -> {
            String score = frame.getScore() == -1 ? " " : String.valueOf(frame.getScore());
            return BLANK + String.format("%-5s", score);
        }).collect(Collectors.joining(SEPARATOR));
    }

    private static String getFrameName(Frames frames) {
        return frames.frames()
                .stream()
                .map(frame -> BLANK + String.format("%02d", frame.frameNumber()) + BLANK)
                .collect(Collectors.joining(SEPARATOR));
    }

    private static String getMark(Frames frames) {
        return frames.frames().stream().map(frame -> getMarkPerFrame(frame)).collect(Collectors.joining());
    }

    private static String getMarkPerFrame(Frame frame) {
        StringBuilder sb = new StringBuilder();
        sb.append(SEPARATOR);
        sb.append(BLANK);
        for (int i = 0; i < frame.playCount(); i++) {
            int hitSize = frame.pinStatus().pinSize(i);
            int previousHitSize = i == 0 ? 0 : frame.pinStatus().pinSize(i - 1);
            int firstHitSize = i == 2 ? frame.pinStatus().pinSize(i - 2) : 0;
            sb.append(makeMark(hitSize, previousHitSize, firstHitSize, i));
        }
        return String.format("%-9s", sb.toString());
    }

    private static StringBuilder makeMark(int hitSize, int previousHitSize, int firstHitSize, int i) {
        StringBuilder sb = new StringBuilder();

        String mark = Score.score(hitSize, previousHitSize, firstHitSize, i + 1).mark();
        String sbMark = mark.equals("-1") ? String.valueOf(hitSize) : mark;
        if (i > 0) {
            sb.append(SEPARATOR);
        }
        sb.append(sbMark);

        return sb;
    }

}

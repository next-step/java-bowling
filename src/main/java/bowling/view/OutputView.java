package bowling.view;

import bowling.domain.Player;

import java.util.List;
import java.util.stream.IntStream;

public class OutputView {
    private static final String OUTPUT_FRAME_FORMAT = "  %02d  |";
    private static final String OUTPUT_SCORE_FORMAT = "  %-3s |";
    private static final String NAME_FRAME_STRING = "| NAME |";
    private static final int FRAME_NUMBER_START = 1;
    private static final int FRAME_NUMBER_END = 10;

    public static void outputFrames() {
        System.out.print(NAME_FRAME_STRING);
        IntStream.rangeClosed(FRAME_NUMBER_START, FRAME_NUMBER_END)
                 .forEach(i -> outputFrame(i));
        System.out.print(System.lineSeparator());
    }

    private static void outputFrame(int frameNumber) {
        System.out.printf(OUTPUT_FRAME_FORMAT, frameNumber);
    }

    public static void outputScores(Player player, List<String> scores) {
        outputFrames();
        System.out.printf("|  %3s |", player);
        for (int i = 0; i < FRAME_NUMBER_END; i++) {
            outputScore(scores.size() > i ? scores.get(i) : "");
        }
        System.out.print(System.lineSeparator());
    }

    private static void outputScore(String score) {
        System.out.printf(OUTPUT_SCORE_FORMAT, score);
    }

}
package bowling.view;

import java.util.List;
import java.util.stream.IntStream;

public class ResultView {
    private static final String OUTPUT_FRAME_FORMAT = "  %02d  |";
    private static final String OUTPUT_SCORE_FORMAT = "  %-3s |";
    private static final int FRAME_NUMBER_START = 1;
    private static final int FRAME_NUMBER_END = 10;

    private void outputFrames() {
        System.out.print("| NAME |");
        IntStream.rangeClosed(FRAME_NUMBER_START, FRAME_NUMBER_END)
            .forEach(this::outputNFrame);
        System.out.print(System.lineSeparator());
    }

    private void outputNFrame(int frameNumber) {
        System.out.printf(OUTPUT_FRAME_FORMAT, frameNumber);
    }

    public void outputScores(String playerName, List<String> scores) {
        outputFrames();
        System.out.printf("|  %3s |", playerName);
        scores.forEach(this::outputScore);
        System.out.print(System.lineSeparator());
    }

    private void outputScore(String score) {
        System.out.printf(OUTPUT_SCORE_FORMAT, score);
    }

}

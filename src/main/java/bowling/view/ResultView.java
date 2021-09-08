package bowling.view;

import java.util.List;
import java.util.stream.IntStream;

public class ResultView {
    private static final String OUTPUT_FRAME_FORMAT = "  %02d  |";
    private static final String OUTPUT_EMPTY_FRAME = "|      |";
    private static final String OUTPUT_EMPTY_HALF_FRAME = "      |";
    private static final String OUTPUT_PLAYER_NAME_FORMAT = "|  %3s |";
    private static final String OUTPUT_SCORE_FORMAT = "  %-3s |";
    private static final String OUTPUT_LAST_SCORE_FORMAT = " %-5s|";
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
        System.out.printf(OUTPUT_PLAYER_NAME_FORMAT, playerName);
        if (scores.size() != FRAME_NUMBER_END) {
            scores.forEach(this::outputScore);
            IntStream.range(scores.size(), FRAME_NUMBER_END)
                .forEach(i -> outputEmptyHalfFrame());
        }

        if (scores.size() == FRAME_NUMBER_END) {
            IntStream.range(FRAME_NUMBER_START - 1, FRAME_NUMBER_END - 1)
                .forEach(i -> outputScore(scores.get(i)));
            System.out.printf(OUTPUT_LAST_SCORE_FORMAT, scores.get(FRAME_NUMBER_END - 1));
        }
        System.out.print(System.lineSeparator());
    }

    private void outputScore(String score) {
        System.out.printf(OUTPUT_SCORE_FORMAT, score);
    }

    public void outputCumulativeScores(List<Integer> cumulativeScores) {
        System.out.print(OUTPUT_EMPTY_FRAME);
        cumulativeScores.forEach(this::outputCumulativeScore);
        IntStream.range(cumulativeScores.size(), FRAME_NUMBER_END)
            .forEach(i -> outputEmptyHalfFrame());
        System.out.print(System.lineSeparator());
    }

    private void outputCumulativeScore(Integer cumulativeScore) {
        System.out.printf(OUTPUT_SCORE_FORMAT, cumulativeScore);
    }

    private void outputEmptyHalfFrame() {
        System.out.print(OUTPUT_EMPTY_HALF_FRAME);
    }
}

package bowling.view;

import bowling.domain.Players;
import bowling.domain.TotalFrames;

import java.util.List;
import java.util.stream.IntStream;

public class OutputView {

    private static final String NAME_FRAME_STRING = "| NAME |";
    private static final String OUTPUT_FRAME_FORMAT = "  %02d  |";
    private static final String OUTPUT_SCORE_FORMAT = "  %-3s |";
    private static final String OUTPUT_NAME_FORMAT = "|  %3s |";
    private static final String OUTPUT_EMPTY_FORMAT = "|      |";
    private static final String OUTPUT_EMPTY_FORMAT_HALF = "      |";
    private static final String OUTPUT_LAST_SCORE_FORMAT = " %-5s|";
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

    public static void outputScores(Players players, TotalFrames totalFrame) {
        List<String> playerNames = players.names();
        List<List<String>> scoresOfPlayers = players.results(totalFrame);
        List<List<Integer>> totalScoresOfPlayers = players.scoreBoard().calculatedScoresOfPlayers();

        outputFrames();
        for (int i = 0; i < playerNames.size(); i++) {
            System.out.printf(OUTPUT_NAME_FORMAT, playerNames.get(i));
            outputScore(scoresOfPlayers.get(i));
            outputCalculatedScores(totalScoresOfPlayers.get(i));
        }
        System.out.print(System.lineSeparator());

    }

    private static void outputScore(List<String> scores) {
        if (scores.size() != FRAME_NUMBER_END) {
            scores.forEach(score -> System.out.printf(OUTPUT_SCORE_FORMAT, score));
            IntStream.range(scores.size(), FRAME_NUMBER_END)
                     .forEach(i -> outputEmptyScoreFrame());
        }
        if (scores.size() == FRAME_NUMBER_END) {
            IntStream.range(FRAME_NUMBER_START - 1, FRAME_NUMBER_END - 1)
                     .forEach(i -> System.out.printf(OUTPUT_SCORE_FORMAT, scores.get(i)));
            System.out.printf(OUTPUT_LAST_SCORE_FORMAT, scores.get(FRAME_NUMBER_END - 1));
        }
        System.out.print(System.lineSeparator());
    }

    public static void outputCalculatedScores(List<Integer> calculatedScores) {
        System.out.print(OUTPUT_EMPTY_FORMAT);
        calculatedScores.forEach(score -> outputCalculatedScoreFrame(score));
        IntStream.range(calculatedScores.size(), FRAME_NUMBER_END)
                 .forEach(i -> outputEmptyScoreFrame());
        System.out.print(System.lineSeparator());
    }

    private static void outputCalculatedScoreFrame(Integer calculatedScore) {
        System.out.printf(OUTPUT_SCORE_FORMAT, calculatedScore);
    }

    private static void outputEmptyScoreFrame() {
        System.out.print(OUTPUT_EMPTY_FORMAT_HALF);
    }
}
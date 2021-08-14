package bowling.view;

import bowling.domain.dto.ScoreData;
import bowling.domain.dto.BowlingGameResult;
import bowling.domain.dto.StateData;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {
    public static final int START_COUNT = 1;
    public static final int END_COUNT = 10;
    private static final int FRAME_STATE_LIMIT = 3;
    private static final String NAME_FORMAT = "| %4s |";
    private static final String FRAME_COUNT_FORMAT = "  %02d  |";
    private static final String FRAME_STATE_FORMAT = "  %-4s|";
    private static final String FRAME_SCORE_FORMAT = "  %-4d|";
    private static final String EXTRA_CHANCE_FORMAT = " %-5s|";
    private static final String EXTRA_CHANCE_MARK = "|";
    private static final String EMPTY_STRING = "";

    private ResultView() {
    }

    public static void printBowlingScore(BowlingGameResult result) {
        printHeader();
        printGameResult(result);
    }

    private static void printHeader() {
        print(String.format(NAME_FORMAT, "NAME"));
        IntStream.rangeClosed(START_COUNT, END_COUNT)
                .forEach(count -> print(String.format(FRAME_COUNT_FORMAT, count)));
        printNextLine();
    }

    private static void printGameResult(BowlingGameResult result) {
        printBowlingFrame(result);
        printScore(result);
    }

    private static void printBowlingFrame(BowlingGameResult result) {
        print(String.format(NAME_FORMAT, result.getPlayerName()));
        printFrame(result);
        printLeftFrame(result);
        printNextLine();
    }

    private static void printFrame(BowlingGameResult result) {
        result.getStates()
                .stream()
                .map(ResultView::convert)
                .forEach(value -> print(getState(value)));
    }

    private static void printLeftFrame(BowlingGameResult result) {
        IntStream.range(result.getFrameCount(), END_COUNT)
                .forEach(value -> print(String.format(FRAME_STATE_FORMAT, EMPTY_STRING)));
    }

    private static void printScore(BowlingGameResult result) {
        print(String.format(NAME_FORMAT, EMPTY_STRING));
        printComputedScore(result);
        printLeftComputedScore(result);
        printNextLine();
    }

    private static void printComputedScore(BowlingGameResult result) {
        AtomicInteger score = new AtomicInteger(0);
        result.getScores()
                .stream()
                .map(ScoreData::getValue)
                .map(score::addAndGet)
                .forEach(value -> print(String.format(FRAME_SCORE_FORMAT, value)));
    }

    private static void printLeftComputedScore(BowlingGameResult result) {
        IntStream.range(result.getScores().size(), END_COUNT)
                .forEach(value -> print(String.format(FRAME_STATE_FORMAT, EMPTY_STRING)));
    }

    private static String getState(String state) {
        return String.format(getFormat(state), state);
    }

    private static String getFormat(String state) {
        return state.length() > FRAME_STATE_LIMIT ? EXTRA_CHANCE_FORMAT : FRAME_STATE_FORMAT;
    }

    private static String convert(StateData data) {
        return data.getStateData()
                .stream()
                .map(ResultStateFormat::getValue)
                .collect(Collectors.joining(EXTRA_CHANCE_MARK));
    }

    private static void print(String statement) {
        System.out.print(statement);
    }

    private static void printNextLine() {
        print(System.lineSeparator());
    }
}

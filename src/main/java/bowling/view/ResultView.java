package bowling.view;

import bowling.domain.frame.CommonFrame;
import bowling.domain.dto.BowlingPlayResultData;
import bowling.domain.dto.StateDatas;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.domain.frame.CommonFrame.END_COUNT;
import static bowling.domain.frame.CommonFrame.START_COUNT;

public class ResultView {
    private static final String NAME_FORMAT = "| %4s |";
    private static final String FRAME_COUNT_FORMAT = "  %02d  |";
    private static final String FRAME_FORMAT = "  %-4s|";
    private static final String EXTRA_CHANCE_FORMAT = " %-5s|";
    private static final String EXTRA_CHANCE_MARK = "|";
    private static final int FRAME_STATE_LIMIT = 3;

    private ResultView() {
    }

    public static void printBowlingScore(BowlingPlayResultData resultData) {
        printHeader();
        printBowlingFrame(resultData);
    }

    private static void printHeader() {
        print(String.format(NAME_FORMAT, "NAME"));

        IntStream.rangeClosed(START_COUNT, END_COUNT)
                .forEach(count -> print(String.format(FRAME_COUNT_FORMAT, count)));

        printNextLine();
    }

    private static void printBowlingFrame(BowlingPlayResultData resultData) {
        print(String.format(NAME_FORMAT, resultData.getPlayerName()));
        printFrame(resultData);
        printLeftFrame(resultData);

        printNextLine();
    }

    private static void printFrame(BowlingPlayResultData resultData) {
        resultData.getStates()
                .stream()
                .map(ResultView::makeRecordValue)
                .forEach(value -> print(getStateString(value)));
    }

    private static void printLeftFrame(BowlingPlayResultData resultData) {
        IntStream.range(resultData.getFrameCount(), CommonFrame.END_COUNT)
                .forEach(value -> print(String.format(FRAME_FORMAT, "")));
    }

    private static String makeRecordValue(StateDatas stateDatas) {
        return stateDatas.getStateDatas()
                .stream()
                .map(ResultStateFormat::getValue)
                .collect(Collectors.joining(EXTRA_CHANCE_MARK));
    }

    private static String getStateString(String state) {
        return String.format(getFormat(state), state);
    }

    private static String getFormat(String state) {
        if (state.length() > FRAME_STATE_LIMIT) {
            return EXTRA_CHANCE_FORMAT;
        }

        return FRAME_FORMAT;
    }

    private static void print(String statement) {
        System.out.print(statement);
    }

    private static void printNextLine() {
        print(System.lineSeparator());
    }
}

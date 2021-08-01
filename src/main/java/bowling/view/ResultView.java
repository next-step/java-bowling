package bowling.view;

import bowling.dto.BowlingPlayerDto;
import bowling.dto.StateDtos;
import bowling.view.state.StateFormat;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {
    private static final String NAME_FORMAT = "| %4s |";
    private static final String FRAME_NUMBER_FORMAT = "  %02d  |";
    private static final String FRAME_STATE_FORMAT = "  %-4s|";
    private static final String EXTRA_FRAME_STATE_FORMAT = " %-5s|";
    private static final int NORMAL_FRAME_STATE_LIMIT = 3;
    private static final String EXTRA_FRAME_JOIN_SYMBOL = "|";
    private static final String EMPTY_STRING = "";

    private ResultView() {}

    public static void printScoreBoard(BowlingPlayerDto bowlingPlayerDto) {
        printHeader();
        printPlayer(bowlingPlayerDto);
    }

    private static void printHeader() {
        printStatement(String.format(NAME_FORMAT, "NAME"));

        IntStream.rangeClosed(1, 10)
                .forEach(number -> printStatement(String.format(FRAME_NUMBER_FORMAT, number)));

        newLine();
    }

    private static void printPlayer(BowlingPlayerDto bowlingPlayerDto) {
        printStatement(String.format(NAME_FORMAT, bowlingPlayerDto.getName()));
        printPlayedFrame(bowlingPlayerDto);
        printRemainFrame(bowlingPlayerDto);

        newLine();
    }

    private static void printRemainFrame(BowlingPlayerDto bowlingPlayerDto) {
        IntStream.range(bowlingPlayerDto.getCurrentFrameNumber(), 10)
                .forEach(noStr -> printStatement(String.format(FRAME_STATE_FORMAT, EMPTY_STRING)));
    }

    private static void printPlayedFrame(BowlingPlayerDto bowlingPlayerDto) {
        bowlingPlayerDto.getStates()
                .stream()
                .map(ResultView::convertToString)
                .forEach(state -> printStatement(toFrameState(state)));
    }

    private static String toFrameState(String state) {
        return String.format(state.length() > NORMAL_FRAME_STATE_LIMIT ? EXTRA_FRAME_STATE_FORMAT : FRAME_STATE_FORMAT, state);
    }

    private static String convertToString(StateDtos stateDtos) {
        return stateDtos.getStateDtos()
                .stream()
                .map(StateFormat::convert)
                .collect(Collectors.joining(EXTRA_FRAME_JOIN_SYMBOL));
    }

    private static void printStatement(String statement) {
        System.out.print(statement);
    }

    private static void newLine() {
        System.out.println();
    }
}

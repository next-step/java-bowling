package bowling.view;

import bowling.dto.ResultDto;
import bowling.dto.StateDtos;

import static bowling.frame.Frames.FIRST_FRAME_OF_BOWLING_GAME;
import static bowling.frame.Frames.LIMIT_FRAME_OF_BOWLING_GAME;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.rangeClosed;

public class ResultView {
    private static final String EMPTY_STRING = "";
    private static final String FORMAT_HEADER_NAME = "| NAME |";
    private static final String FORMAT_NAME = "| %4s |";
    private static final String FORMAT_HEADER_FRAME_COUNT = "  %02d  |";
    private static final String FORMAT_NORMAL_FRAME_STATE = "  %-4s|";
    private static final String FORMAT_LAST_FRAME_STATE = " %-5s|";
    private static final String FRAME_JOINING_SYMBOL = "|";

    private static final int NORMAL_FRAME_SYMBOL_LENGTH = 3;

    private ResultView() {}

    public static void scoreBoard(final ResultDto resultDto) {
        showHeader();
        showScoreOfPlayer(resultDto);
    }

    private static void showHeader() {
        print(FORMAT_HEADER_NAME);
        showHeaderFrame();
        newLine();
    }

    private static void showHeaderFrame() {
        rangeClosed(FIRST_FRAME_OF_BOWLING_GAME, LIMIT_FRAME_OF_BOWLING_GAME)
                .forEach(frameCount -> print(String.format(FORMAT_HEADER_FRAME_COUNT, frameCount)));
    }

    private static void showScoreOfPlayer(final ResultDto resultDto) {
        print(String.format(FORMAT_NAME, resultDto.getName()));
        showProgressFrames(resultDto);
        showRemainingFrames(resultDto);
        newLine();
    }

    private static void showProgressFrames(final ResultDto resultDto) {
        resultDto.getStates()
                .stream()
                .map(ResultView::convertToSymbol)
                .map(ResultView::toFormatting)
                .forEach(ResultView::print);
    }

    private static String convertToSymbol(final StateDtos stateDtos) {
        return stateDtos.getStates()
                .stream()
                .map(StateView::convertToViewFormat)
                .collect(joining(FRAME_JOINING_SYMBOL));
    }

    private static String toFormatting(final String state) {
        return String.format(getFormat(state), state);
    }

    private static String getFormat(final String state) {
        if (state.length() > NORMAL_FRAME_SYMBOL_LENGTH) {
            return FORMAT_LAST_FRAME_STATE;
        }
        return FORMAT_NORMAL_FRAME_STATE;
    }

    private static void showRemainingFrames(final ResultDto resultDto) {
        int bound = LIMIT_FRAME_OF_BOWLING_GAME;
        for (int count = resultDto.size(); count < bound; count++) {
            print(String.format(FORMAT_NORMAL_FRAME_STATE, EMPTY_STRING));
        }
    }

    private static void print(final String message) {
        System.out.print(message);
    }

    public static void newLine() {
        System.out.println();
    }
}

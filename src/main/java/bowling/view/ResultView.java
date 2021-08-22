package bowling.view;

import bowling.dto.ResultDto;
import bowling.dto.StateDtos;

import static bowling.frame.Frames.FIRST_FRAME_OF_BOWLING_GAME;
import static bowling.frame.Frames.LIMIT_FRAME_OF_BOWLING_GAME;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

public class ResultView {
    private static final String EMPTY_STRING = "";
    private static final String FORMAT_HEADER_NAME = "| NAME |";
    private static final String FORMAT_NAME = "| %4s |";
    private static final String FORMAT_HEADER_FRAME_COUNT = "  %02d  |";
    private static final String FORMAT_FRAME_STATE = "  %-4s|";

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
        resultDto.getStates().stream()
                .flatMap(stateDtos -> stateDtos.getStates().stream())
                .map(StateView::convert)
                .forEach(scoreString -> print(String.format(FORMAT_FRAME_STATE, scoreString)));
    }

    private static void showRemainingFrames(final ResultDto resultDto) {
        range(resultDto.size(), LIMIT_FRAME_OF_BOWLING_GAME)
                .forEach(frameCount -> print(String.format(FORMAT_FRAME_STATE, EMPTY_STRING)));
    }

    private static void print(final String message) {
        System.out.print(message);
    }

    public static void newLine() {
        System.out.println();
    }
}

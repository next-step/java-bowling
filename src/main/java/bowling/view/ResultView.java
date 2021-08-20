package bowling.view;

import static bowling.frame.Frames.FIRST_FRAME_OF_BOWLING_GAME;
import static bowling.frame.Frames.LIMIT_FRAME_OF_BOWLING_GAME;
import static java.util.stream.IntStream.rangeClosed;

public class ResultView {
    private static final String FORMAT_HEADER_NAME = "| NAME |";
    private static final String FORMAT_NAME = "| %4s |";
    private static final String FORMAT_HEADER_FRAME_COUNT = "  %02d  |";
    private static final String FORMAT_FRAME_STATE = "  %-4s|";

    public static void scoreBoard(final String name) {
        showHeader();
        showScoreBoard(name);
    }

    private static void showHeader() {
        print(FORMAT_HEADER_NAME);

        rangeClosed(FIRST_FRAME_OF_BOWLING_GAME, LIMIT_FRAME_OF_BOWLING_GAME)
                .forEach(frameCount -> print(String.format(FORMAT_HEADER_FRAME_COUNT, frameCount)));

        newLine();
    }

    private static void showScoreBoard(final String name) {
        print(String.format(FORMAT_NAME, name));

        rangeClosed(FIRST_FRAME_OF_BOWLING_GAME, LIMIT_FRAME_OF_BOWLING_GAME)
                .forEach(frameCount -> print(String.format(FORMAT_FRAME_STATE, "")));

        newLine();
    }

    private static void print(final String message) {
        System.out.print(message);
    }

    public static void newLine() {
        System.out.println();
    }
}

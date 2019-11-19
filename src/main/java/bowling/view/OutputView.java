package bowling.view;

import bowling.domain.Ball;
import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    private static final String DASH_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String DASH_BOARD_FORMAT = "|%s|";
    private static final String DASH_BOARD_SEPARATOR = "|";
    private static final int FRAME_FORMAT_LENGTH = 4;
    private static final String FRAME_FORMAT_FIVE_LENGTH = " %-5s";
    private static final String FRAME_FORMAT_FOUR_LENGTH = "  %-4s";
    private static final String RESULT_DELIMITER = "|";
    private static final String SPARE_TEXT = "/";
    private static final String ZERO_TEXT = "-";
    private static final String STRIKE_TEXT = "X";
    private static final String EMPTY_TEXT = "";
    private static final int SECOND_BALL_INDEX = 1;

    public static void printDashBoard(Player player) {
        printHeader();
        printPlayer(player);
    }

    private static void printPlayer(Player player) {
        List<String> result = new ArrayList<>();
        result.add(frameFormat(player.getName()));
        for (int i = 0; i < Frames.LAST_FRAME; i++) {
            Frame frame = player.frameByIndex(i);
            result.add(frameFormat(frameResult(frame)));
        }

        System.out.println(String.format(DASH_BOARD_FORMAT, String.join(DASH_BOARD_SEPARATOR, result)));
    }

    private static String frameResult(Frame frame) {
        List<Ball> balls = frame.unmodifiableBalls();
        return IntStream.range(0, balls.size())
                .mapToObj(i -> getBallText(frame, balls, i))
                .filter(result -> !EMPTY_TEXT.equals(result))
                .collect(Collectors.joining(RESULT_DELIMITER));
    }

    private static String getBallText(Frame frame, List<Ball> balls, int i) {
        if (i == SECOND_BALL_INDEX && frame.isSpare()) {
            return SPARE_TEXT;
        }
        return convertBallText(balls.get(i));
    }

    private static String convertBallText(Ball ball) {
        if (ball.getPin() == Ball.ALL_PIN_COUNT) {
            return STRIKE_TEXT;
        }

        if (ball.getPin() == Ball.ZERO_PIN_COUNT) {
            return ZERO_TEXT;
        }

        if (ball.getPin() == Ball.DEFAULT_PIN) {
            return EMPTY_TEXT;
        }

        return String.valueOf(ball.getPin());
    }

    private static void printHeader() {
        System.out.println(DASH_BOARD_HEADER);
    }

    private static String frameFormat(String result) {
        String format = result.length() > FRAME_FORMAT_LENGTH ?
                FRAME_FORMAT_FIVE_LENGTH : FRAME_FORMAT_FOUR_LENGTH;
        return String.format(format, result);
    }
}

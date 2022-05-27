package bowling.view;

import static java.util.stream.Collectors.joining;

import bowling.domain.Frame;
import bowling.domain.FrameNumber;
import java.util.List;
import java.util.stream.IntStream;

public class OutputView {
    private static final String BOWLING_SCORE_BOARD_FRAME = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String BOWLING_PLAYER_NAME = "|  %3s |";
    private static final String BOWLING_SCORE_BOARD_STATUS = "  %-3s |";
    private static final String BOWLING_SCORE_BOARD_EMPTY_STATUS = "      |";
    private static final String LINE_BREAK = "\n";

    private static final StringBuilder STRING_BUILDER = new StringBuilder();

    public static void printBowlingScoreBoard(String playerName, List<Frame> frames) {
        initialStringBuilder();
        STRING_BUILDER.append(BOWLING_SCORE_BOARD_FRAME);
        STRING_BUILDER.append(LINE_BREAK);

        printBowlingPlayerName(playerName);
        printBowlingScoreStatus(frames);

        System.out.println(STRING_BUILDER);
    }

    private static void printBowlingScoreStatus(List<Frame> frames) {
        STRING_BUILDER.append(frames.stream()
                .map(frame -> String.format(BOWLING_SCORE_BOARD_STATUS, frame.toString()))
                .collect(joining()));

        STRING_BUILDER.append(IntStream.rangeClosed(1, FrameNumber.MAX_FRAME_NUMBER - frames.size())
                .mapToObj(index -> BOWLING_SCORE_BOARD_EMPTY_STATUS)
                .collect(joining()));
    }

    private static void printBowlingPlayerName(String playerName) {
        STRING_BUILDER.append(String.format(BOWLING_PLAYER_NAME, playerName));
    }

    private static void initialStringBuilder() {
        STRING_BUILDER.setLength(0);
    }
}

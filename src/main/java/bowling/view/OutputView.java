package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.FrameIndex;
import bowling.domain.frame.Frame;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public final class OutputView {
    private static final String SCORE_HEAD_FORMAT = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String PLAYER_NAME_FORMAT = "|  %s |";
    private static final String RESULT_FRAME_FORMAT = "  %-3s |";
    private static final String EMPTY_FRAME_FORMAT = "      |";

    private OutputView() {
    }

    public static void printCurrentStatus(BowlingGame bowlingGame) {
        System.out.print(currentStatus(bowlingGame));
    }

    private static String currentStatus(BowlingGame bowlingGame) {
        return String.format("%s%n%s%s%n", SCORE_HEAD_FORMAT, playerName(bowlingGame.getPlayerName()), body(bowlingGame.getFrames()));
    }

    private static String playerName(String playerName) {
        return String.format(PLAYER_NAME_FORMAT, playerName);
    }

    private static String body(List<Frame> frames) {
        return resultBody(frames) + baseBody(frames);
    }

    private static String baseBody(List<Frame> frames) {
        return IntStream.rangeClosed(1, FrameIndex.MAX_INDEX - frames.size())
                .mapToObj(i -> EMPTY_FRAME_FORMAT)
                .collect(joining());
    }

    private static String resultBody(List<Frame> frames) {
        return frames.stream()
                .map(frame -> String.format(RESULT_FRAME_FORMAT, frame.symbol()))
                .collect(joining());
    }
}

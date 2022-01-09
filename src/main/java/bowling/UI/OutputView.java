package bowling.UI;

import bowling.domain.BowlingGame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameIndex;
import bowling.domain.frame.Score;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class OutputView {

    private static final String SCORE_HEAD_FORMAT = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";

    private static final String PLAYER_NAME_FORMAT = "|  %s |";

    private static final String RESULT_FRAME_FORMAT = "  %-3s |";
    private static final String EMPTY_FRAME_FORMAT = "      |";

    private static final String LEFT_PADDING_SCORE_FORMAT = "|      |";
    private static final String RESULT_SCORE_FORMAT = "  %-3s |";
    private static final String EMPTY_SCORE_FORMAT = "      |";

    private OutputView() {}

    public static void printCurrentStatus(BowlingGame bowlingGame) {
        System.out.print(currentStatus(bowlingGame));
    }

    private static String currentStatus(BowlingGame bowlingGame) {
        return String.format("%s%n%s%s%n%s%n", SCORE_HEAD_FORMAT, playerName(bowlingGame.getPlayerName()), body(bowlingGame.getFrames()), scoreBody(bowlingGame.getFrames()));
    }

    private static String playerName(String playerName) {
        return String.format(PLAYER_NAME_FORMAT, playerName);
    }

    private static String body(List<Frame> frames) {
        return resultBody(frames) + baseBody(frames);
    }

    private static String resultBody(List<Frame> frames) {
        return frames.stream()
                .map(frame -> String.format(RESULT_FRAME_FORMAT, frame.symbol()))
                .collect(joining());
    }

    private static String baseBody(List<Frame> frames) {
        return IntStream.rangeClosed(1, FrameIndex.MAX_INDEX - frames.size())
                .mapToObj(i -> EMPTY_FRAME_FORMAT)
                .collect(joining());
    }

    private static String scoreBody(List<Frame> frames) {
        return String.format("%s%s%s", LEFT_PADDING_SCORE_FORMAT, totalScoreBody(frames), baseBody(frames));
    }

    private static String totalScoreBody(List<Frame> frames) {
        return frames.stream()
                .map(frame -> currentTotalScoreBody(frames, frame))
                .collect(joining());
    }

    private static String currentTotalScoreBody(List<Frame> frames, Frame frame) {
        if (frame.score() == Score.UN_SCORE_STATE) {
            return EMPTY_SCORE_FORMAT;
        }
        return totalScore(frames, frames.indexOf(frame) + 1);
    }

    private static String totalScore(List<Frame> frames, int limit) {
        return String.format(RESULT_SCORE_FORMAT, totalScoreByLimit(frames, limit));
    }

    private static int totalScoreByLimit(List<Frame> frames, int limit) {
        return frames.stream()
                .limit(limit)
                .mapToInt(Frame::score)
                .sum();
    }
}

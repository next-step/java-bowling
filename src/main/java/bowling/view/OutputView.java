package bowling.view;

import bowling.domain.BowlResult;
import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.score.Score;
import bowling.score.Scores;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class OutputView {
    private static final String NAME_TEXT = "| NAME |";
    private static final String PLAYER_NAME_TEXT = "|  %s |";
    private static final String FRAME_NUMBER_TEXT = "  %02d  |";
    private static final String FRAME_RESULT_TEXT = "  %s|";
    private static final String SCORE_DELIMITER = "|";
    private static final String TEXT_BLANK = " ";

    public static void printFrame(Frames frames) {
        printFrameTop();
        printFrameResult(frames);
    }

    private static void printFrameTop() {
        String topText = IntStream.range(Frame.FIRST_FRAME, Frame.LAST_FRAME + 1)
                .mapToObj(frameNumber -> String.format(FRAME_NUMBER_TEXT, frameNumber))
                .collect(Collectors.joining());

        System.out.println(NAME_TEXT + topText);
    }

    private static void printFrameResult(Frames frames) {
        String playerName = String.format(PLAYER_NAME_TEXT, frames.getPlayerName());

        String playerResult = IntStream.range(Frame.FIRST_FRAME, Frame.LAST_FRAME + 1)
                .mapToObj(frameNumber -> makeScoreExpression(frames, frameNumber))
                .map(OutputView::attachBlank)
                .map(result -> String.format(FRAME_RESULT_TEXT, result))
                .collect(Collectors.joining());

        System.out.println(playerName + playerResult);
    }

    private static String attachBlank(String result) {
        String blank = IntStream.range(0, 4 - result.length())
                .mapToObj(i -> TEXT_BLANK)
                .collect(Collectors.joining());

        return result + blank;
    }

    private static String makeScoreExpression(Frames frames, int frameNumber) {
        List<Score> scores = frames.getResult(frameNumber);

        return IntStream.range(0, scores.size())
                .mapToObj(index -> scores.get(index) == null
                        ? null
                        : getScoreResult(scores, index))
                .filter(Objects::nonNull)
                .collect(Collectors.joining(SCORE_DELIMITER));
    }

    private static String getScoreResult(List<Score> scores, int index) {
        if (scores.get(index).isStrike()) {
            return BowlResult.STRIKE.getExpression();
        }

        if (scores.get(index).isGutter()) {
            return BowlResult.GUTTER.getExpression();
        }

        if (index == 1 && Scores.isSpare(scores)) {
            return BowlResult.SPARE.getExpression();
        }

        return scores.get(index).toString();
    }
}

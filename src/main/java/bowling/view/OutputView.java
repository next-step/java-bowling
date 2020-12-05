package bowling.view;

import bowling.domain.BowlResult;
import bowling.domain.Bowling;
import bowling.domain.frame.Frame;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

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
    private static final String NEW_LINE = System.lineSeparator();

    public static void printFrame(Bowling bowling) {
        printFrameTop();
        printFrameResult(bowling);
    }

    private static void printFrameTop() {
        String topText = IntStream.range(Frame.FIRST_FRAME, Frame.LAST_FRAME + 1)
                .mapToObj(frameNumber -> String.format(FRAME_NUMBER_TEXT, frameNumber))
                .collect(Collectors.joining());

        System.out.println(NAME_TEXT + topText);
    }

    private static void printFrameResult(Bowling bowling) {
        String playerName = String.format(PLAYER_NAME_TEXT, bowling.getPlayerName());

        String playerResult = IntStream.range(Frame.FIRST_FRAME, Frame.LAST_FRAME + 1)
                .mapToObj(frameNumber -> makeScoreExpression(bowling, frameNumber))
                .map(OutputView::attachBlank)
                .map(result -> String.format(FRAME_RESULT_TEXT, result))
                .collect(Collectors.joining());

        System.out.println(playerName + playerResult + NEW_LINE);
    }

    private static String attachBlank(String result) {
        String blank = IntStream.range(0, 4 - result.length())
                .mapToObj(i -> TEXT_BLANK)
                .collect(Collectors.joining());

        return result + blank;
    }

    private static String makeScoreExpression(Bowling bowling, int frameNumber) {
        List<Score> scores = bowling.getResult(frameNumber);

        return IntStream.range(0, scores.size())
                .mapToObj(index -> getScoreResult(scores, index))
                .filter(Objects::nonNull)
                .collect(Collectors.joining(SCORE_DELIMITER));
    }

    private static String getScoreResult(List<Score> scores, int index) {
        return scores.get(index) == null
                ? null
                : getScoreExpression(scores, index);
    }

    private static String getScoreExpression(List<Score> scores, int index) {
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

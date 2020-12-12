package bowling.view;

import bowling.domain.ScoreExpression;
import bowling.domain.Bowling;
import bowling.domain.score.Score;
import bowling.domain.score.Scores;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static bowling.domain.frame.Frame.FIRST_FRAME;
import static bowling.domain.frame.Frame.LAST_FRAME;

public final class OutputView {
    private static final String NAME_TEXT = "| NAME |";
    private static final String SCORE_TEXT = "|      |";
    private static final String PLAYER_NAME_TEXT = "|  %s |";
    private static final String FRAME_NUMBER_TEXT = "  %02d  |";
    private static final String FRAME_STRING_TEXT = "  %s|";
    private static final String SCORE_DELIMITER = "|";
    private static final String TEXT_BLANK = " ";
    private static final String NEW_LINE = System.lineSeparator();

    public static void printFrame(Bowling bowling) {
        printFrameTop();
        printFrameResult(bowling);
        printFrameScore(bowling);
    }

    private static void printFrameTop() {
        String topText = IntStream.range(FIRST_FRAME, LAST_FRAME + 1)
                .mapToObj(frameNumber -> String.format(FRAME_NUMBER_TEXT, frameNumber))
                .collect(Collectors.joining());

        System.out.println(NAME_TEXT + topText);
    }

    private static void printFrameResult(Bowling bowling) {
        String playerName = String.format(PLAYER_NAME_TEXT, bowling.getPlayerName());

        String playerResult = IntStream.range(FIRST_FRAME, LAST_FRAME + 1)
                .mapToObj(frameNumber -> makeScoreExpression(bowling, frameNumber))
                .map(OutputView::attachBlank)
                .map(result -> String.format(FRAME_STRING_TEXT, result))
                .collect(Collectors.joining());

        System.out.println(playerName + playerResult);
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
            return ScoreExpression.STRIKE.getExpression();
        }

        if (scores.get(index).isGutter()) {
            return ScoreExpression.GUTTER.getExpression();
        }

        if (index == 1 && Scores.isSpare(scores)) {
            return ScoreExpression.SPARE.getExpression();
        }

        return scores.get(index).toString();
    }

    private static void printFrameScore(Bowling bowling) {
        List<Integer> accumulatedScore = getAccumulatedScore(bowling);

        printScore(accumulatedScore);
        printBlankScore(LAST_FRAME - accumulatedScore.size());
    }

    private static List<Integer> getAccumulatedScore(Bowling bowling) {
        List<Integer> scoresByFrame = getScoresByFrame(bowling);

        return IntStream.range(FIRST_FRAME, scoresByFrame.size() + 1)
                .mapToObj(frameNumber -> getAccumulatedScore(scoresByFrame, frameNumber))
                .collect(Collectors.toList());
    }

    private static List<Integer> getScoresByFrame(Bowling bowling) {
        return IntStream.range(FIRST_FRAME, LAST_FRAME + 1)
                .mapToObj(bowling::getTotalScore)
                .filter(totalScore -> !Score.INVALID_SCORE.equals(totalScore))
                .map(score -> Integer.valueOf(score.toString()))
                .collect(Collectors.toList());
    }

    private static int getAccumulatedScore(List<Integer> scores, int frameNumber) {
        return scores.stream()
                .limit(frameNumber)
                .reduce(0, Integer::sum);
    }

    private static void printScore(List<Integer> accumulatedScore) {
        String accumulatedScoreString = accumulatedScore.stream()
                .map(Object::toString)
                .map(OutputView::attachBlank)
                .map(score -> String.format(FRAME_STRING_TEXT, score))
                .collect(Collectors.joining(""));

        System.out.print(SCORE_TEXT + accumulatedScoreString);
    }

    private static void printBlankScore(int blankFrameCount) {
        String blankScore = IntStream.range(0, blankFrameCount)
                .mapToObj(count -> attachBlank(TEXT_BLANK))
                .map(OutputView::attachBlank)
                .map(score -> String.format(FRAME_STRING_TEXT, score))
                .collect(Collectors.joining());

        System.out.println(blankScore + NEW_LINE);
    }

}

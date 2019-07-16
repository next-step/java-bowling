package View;

import domain.Bowling;
import domain.Frame;

import java.util.Optional;
import java.util.stream.IntStream;

import static domain.Bowling.TOTAL_FRAME_COUNT;
import static domain.Frame.NO_MORE_NEXT;
import static domain.Frame.ONE;

public class ScoreResultFormatter implements Formatter<Bowling> {
    private int previouslyTotalScore;

    @Override
    public String format(Bowling bowling) {
        StringBuilder builder = new StringBuilder();
        IntStream.rangeClosed(ONE, TOTAL_FRAME_COUNT)
                .boxed()
                .map(frameNumber -> getFrameScore(bowling, frameNumber))
                .forEach(builder::append);
        return builder.toString();
    }

    private String getFrameScore(Bowling bowling, int frameNumber) {
        final String BLANK_FRAME = "    ";
        final String SCORE_CONNECTOR = " | ";

        Optional<Frame> maybeTargetFrame = Optional.ofNullable(bowling.getFrame(frameNumber));
        if (maybeTargetFrame.isPresent()) {
            Frame targetFrame = maybeTargetFrame.get();
            int score = targetFrame.getScore();
            return calculateFrameScore(score, targetFrame.isFrameEnd()) + SCORE_CONNECTOR;
        }
        return BLANK_FRAME + SCORE_CONNECTOR;
    }

    private String calculateFrameScore(int score, boolean isFrameEnd) {
        String scoreResult = "";
        if (isFrameEnd && (score != NO_MORE_NEXT)) {
            scoreResult = Integer.toString(score + previouslyTotalScore);
            previouslyTotalScore += score;
        }
        return String.format("%-4s", scoreResult);
    }
}

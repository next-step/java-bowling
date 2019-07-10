package View;

import domain.BowlingGame;
import domain.FinalFrame;
import domain.NormalFrame;

import java.util.stream.IntStream;

import static domain.BowlingGame.NORMAL_FRAME_COUNT;
import static domain.BowlingGame.TOTAL_FRAME_COUNT;

public class ScoreResultFormatter implements Formatter<BowlingGame> {
    private int previousScore;

    @Override
    public String format(BowlingGame bowlingGame) {
        previousScore = 0;
        StringBuilder builder = new StringBuilder();

        IntStream.range(0, TOTAL_FRAME_COUNT)
                .boxed()
                .map(count -> getNormalFrameScore(bowlingGame, count))
                .forEach(builder::append);
        return builder.toString();
    }

    public String getNormalFrameScore(BowlingGame bowlingGame, int frameNumber) {
        final String BLANK_FRAME = "    ";
        final String SCORE_CONNECTOR = " | ";

        FinalFrame finalFrame = bowlingGame.getFinalFrame();
        if (frameNumber > bowlingGame.lastPosition() + (finalFrame.isStart() ? 1 : 0)) {
            return BLANK_FRAME + SCORE_CONNECTOR;
        }

        if (frameNumber < NORMAL_FRAME_COUNT) {
            NormalFrame frame = bowlingGame.getNormalFrame(frameNumber);
            int score = frame.frameScore(bowlingGame.getFinalFrame());
            return calculateFrameScore(score) + SCORE_CONNECTOR;
        }

        int score = finalFrame.sumScore();
        return calculateFrameScore(score) + SCORE_CONNECTOR;
    }

    private String calculateFrameScore(int score) {
        String scoreResult = (score == -1 ? "" : Integer.toString(score + previousScore));
        previousScore += (score == -1 ? 0 : score);
        return String.format("%-4s", scoreResult);
    }
}

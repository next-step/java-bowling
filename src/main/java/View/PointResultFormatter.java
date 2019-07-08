package View;

import domain.BowlingGame;
import domain.FinalFrame;
import domain.NormalFrame;

import java.util.stream.IntStream;

import static domain.BowlingGame.NORMAL_FRAME_COUNT;
import static domain.BowlingGame.TOTAL_FRAME_COUNT;

public class PointResultFormatter implements Formatter<BowlingGame> {
    private int previousPoint;

    @Override
    public String format(BowlingGame bowlingGame) {
        previousPoint = 0;
        StringBuilder builder = new StringBuilder();

        IntStream.range(0, TOTAL_FRAME_COUNT)
                .boxed()
                .map(count -> getNormalFramePoint(bowlingGame, count))
                .forEach(builder::append);
        return builder.toString();
    }

    public String getNormalFramePoint(BowlingGame bowlingGame, int frameNumber) {
        final String BLANK_FRAME = "    ";
        final String SCORE_CONNECTOR = " | ";

        FinalFrame finalFrame = bowlingGame.getFinalFrame();
        if (frameNumber > bowlingGame.lastPosition() + (finalFrame.isStart() ? 1 : 0)) {
            return BLANK_FRAME + SCORE_CONNECTOR;
        }

        if (frameNumber < NORMAL_FRAME_COUNT) {
            NormalFrame frame = bowlingGame.getNormalFrame(frameNumber);
            int score = frame.framePoint(bowlingGame.getFinalFrame());
            String scoreResult = score == -1 ? "" : Integer.toString(score + previousPoint);
            previousPoint += (score == -1 ? 0 : score);
            return String.format("%-4s", scoreResult) + SCORE_CONNECTOR;
        }

        int score = finalFrame.sumScore();
        String scoreResult = Integer.toString(score + previousPoint);
        previousPoint += score;
        return String.format("%-4s", scoreResult) + SCORE_CONNECTOR;
    }
}

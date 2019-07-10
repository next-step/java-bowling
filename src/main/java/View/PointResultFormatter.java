package View;

import domain.BowlingGame;
import domain.NormalFrame;

import java.util.stream.IntStream;

import static domain.BowlingGame.NORMAL_FRAME_COUNT;
import static domain.NormalScore.ZERO;

public class PointResultFormatter implements Formatter<BowlingGame> {

    @Override
    public String format(BowlingGame bowlingGame) {
        StringBuilder builder = new StringBuilder();
        IntStream.range(ZERO, NORMAL_FRAME_COUNT)
                .boxed()
                .map(count -> getNormalFramePoint(bowlingGame, count))
                .forEach(builder::append);
        builder.append(bowlingGame.getFinalFrame().framePoint() + "|");

        return builder.toString();
    }

    private String getNormalFramePoint(BowlingGame bowlingGame, int count) {
        final String BLANK_FRAME = "    ";
        final String SCORE_CONNECTOR = " | ";

        if (count > bowlingGame.lastPosition()) {
            return BLANK_FRAME + SCORE_CONNECTOR;
        }
        NormalFrame frame = bowlingGame.getNormalFrame(count);
        return frame.framePoint() + SCORE_CONNECTOR;
    }
}

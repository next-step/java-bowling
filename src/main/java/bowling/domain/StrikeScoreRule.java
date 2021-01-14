package bowling.domain;

import java.util.List;

public class StrikeScoreRule implements FrameScoreRule {
    @Override
    public FrameScore apply(Frame frame) {
        if (frame.hasNext()
                && frame.next().getStatus() == Frame.Status.Strike
                && frame.next().hasNext()
                && frame.next().next().getCountOfMarks() > 0 ) {
            return FrameScore.of( 10, 10, frame.next().next().getCountListOfFallDownPins().get(0));
        }

        if (frame.hasNext()
                && frame.next().getStatus() == Frame.Status.Spare ) {
            List<Integer> pins = frame.next().getCountListOfFallDownPins();
            return FrameScore.of(10, 10);
        }

        if (frame.hasNext()
                && frame.next().getStatus() == Frame.Status.Miss ) {
            List<Integer> pins = frame.next().getCountListOfFallDownPins();
            return FrameScore.of(10, pins.get(0), pins.get(1));
        }

        if (frame.hasNext()
                && frame.next().getStatus() == Frame.Status.Gutter ) {
            return FrameScore.of(10);
        }

        return FrameScore.unknown;
    }
}

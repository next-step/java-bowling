package bowling.model.frame;

import bowling.model.BowlingPlayStrategy;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private final List<Frame> frames = new ArrayList<>();

    public void play(BowlingPlayStrategy bowlingPlayStrategy) {
        Frame frame = Frame.first(bowlingPlayStrategy.play());

        while (frame.canPlayNext()) {
            frame = frame.next(bowlingPlayStrategy.play());
            frames.add(frame);
        }
    }
}

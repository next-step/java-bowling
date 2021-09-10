package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int FIRST = 1;
    private static final int NINTH = 9;

    private final List<Frame> frames;

    public Frames() {
        this.frames = initFrames();
    }

    private List<Frame> initFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int number = FIRST; number <= NINTH; number++) {
            frames.add(new NormalFrame(number));
        }
        frames.add(new LastFrame());
        return frames;
    }

    public Frame current() {
        return frames.stream()
                .filter(frame -> !frame.isEnd())
                .findFirst()
                .orElse(null);
    }

    public void bowl(int fallenPins) {
        Frame frame = frames.get(current().number() - 1);
        frame.bowl(fallenPins);
    }

    public Frame of(int frameNumber) {
        return frames.get(frameNumber - 1);
    }

}

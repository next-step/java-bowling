package bowling.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Frames {
    private final static int FINAL_FRAME = 9;
    private final List<Frame> frames;
    private int round = 0;

    public Frames() {
        frames = IntStream.range(0, 9)
                .mapToObj(i -> new NormalFrame())
                .collect(Collectors.toList());
        frames.add(new FinalFrame());
    }

    public int currentRound() {
        return round;
    }

    public void adjustRound() {
        if (isEndCurrentFrame()) {
            this.round++;
        }
    }

    public void rollingBowl(int pinCount) {
        frames.get(round)
                .pitching(pinCount);
    }

    public boolean isEndCurrentFrame() {
        return frames.get(round)
                .isEndFrame();
    }

    public boolean isEndFrames() {
        return frames.get(FINAL_FRAME)
                .isEndFrame();
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(frames);
    }
}

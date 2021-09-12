package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bowling.exception.GameOverException;

public class Frames {

    private final List<Frame> frames;

    public Frames() {
        this.frames = initFrames();
    }

    private List<Frame> initFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int number = NormalFrame.MIN_FRAME_NUMBER; number <= NormalFrame.MAX_FRAME_NUMBER; number++) {
            frames.add(NormalFrame.of(number));
        }
        frames.add(new LastFrame());
        return frames;
    }

    public Frame current() {
        return frames.stream()
                .filter(frame -> !frame.isEnd())
                .findFirst()
                .orElseThrow(GameOverException::new);
    }

    public void bowl(int fallenPins) {
        Frame frame = frames.get(current().number() - 1);
        frame.bowl(fallenPins);
    }

    public Frame of(int frameNumber) {
        return frames.get(frameNumber - 1);
    }

    public boolean isEnd() {
        return frames.stream()
                .allMatch(Frame::isEnd);
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(frames);
    }

}

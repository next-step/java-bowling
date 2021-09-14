package bowling.domain;

import static bowling.domain.NormalFrame.*;

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
        Frame frame = NormalFrame.of(MIN_FRAME_NUMBER);
        List<Frame> frames = new ArrayList<>();
        frames.add(frame);

        for (int number = MIN_FRAME_NUMBER; number <= MAX_FRAME_NUMBER; number++) {
            frame = frame.next();
            frames.add(frame);
        }
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

    public Scores scores() {
        List<Score> scores = new ArrayList<>();
        Score base = Score.ofZero();
        for (Frame frame : frames) {
            base = base.add(frame.score());
            scores.add(base);
        }
        return new Scores(scores);
    }

}

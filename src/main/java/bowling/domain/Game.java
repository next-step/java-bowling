package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private static final int FINAL_FRAME_INDEX = 9;
    private final List<Frame> frames;

    public Game(List<Frame> frames) {
        this.frames = frames;
    }

    public static Game init() {
        return new Game(Collections.singletonList(NormalFrame.init()));
    }

    public Game play(final KnockedPins knockedPins) {
        final List<Frame> frames = new ArrayList<>(this.frames);
        final Frame lastFrame = frames.get(frames.size() - 1);
        if (lastFrame.isPlaying()) {
            frames.remove(lastFrame);
        }
        final Frame playedFrame = nextFrame(frames, lastFrame).play(knockedPins);
        frames.add(playedFrame);
        return new Game(frames);
    }

    public boolean isGameOver() {
        return frames.size() == 10 && !frames.get(frames.size() - 1).isPlaying();
    }

    private Frame nextFrame(final List<Frame> frames, final Frame frame) {
        if (frame.isPlaying()) {
            return frame;
        }
        if (frames.size() < FINAL_FRAME_INDEX) {
            return frame.createNextFrame();
        }
        return frame.createFinalFrame();
    }

    public List<Frame> getFrames() {
        return frames;
    }
}



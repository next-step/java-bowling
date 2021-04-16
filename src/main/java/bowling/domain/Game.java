package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;

public class Game {

    private static final int FIRST_FRAME = 1;
    private static final int LAST_FRAME = 10;

    private int frameIndex;
    private Playable[] frames;

    public Game() {
        this.frameIndex = numberToIndex(FIRST_FRAME);
        this.frames = new Playable[LAST_FRAME];
    }

    public void throwBall(int point) {
        AddFrameIfNeeded();
        frames[frameIndex].throwBall(point);
        addFrameCountIfNeeded();
    }

    private void AddFrameIfNeeded() {
        if (frames[frameIndex] == null) {
            addFrame();
        }
    }

    private void addFrame() {
        if (frameIndex < numberToIndex(LAST_FRAME)) {
            frames[frameIndex] = new Frame();
            return;
        }
        frames[frameIndex] = new FinalFrame();
    }

    private void addFrameCountIfNeeded() {
        if (frames[frameIndex].ended() && frameIndex < numberToIndex(LAST_FRAME)) {
            frameIndex++;
        }
    }

    public boolean ended() {
        if (frameIndex < LAST_FRAME) {
            return false;
        }
        return frames[numberToIndex(LAST_FRAME)].ended();
    }

    private int numberToIndex(int number) {
        return number - 1;
    }

    private int indexToNumber(int number) {
        return number + 1;
    }

    public int frameCount() {
        return indexToNumber(frameIndex);
    }
}

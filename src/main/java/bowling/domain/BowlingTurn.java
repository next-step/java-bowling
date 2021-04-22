package bowling.domain;

import java.util.List;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

public class BowlingTurn {
    private static final int LAST_FRAME_NUMBER = 11;
    private static final int FIRST_FRAME_NUMBER = 1;

    private final Player player;
    private final Frames frames;
    private int currentFrameNumber;

    private BowlingTurn(String name, int currentFrameNumber) {
        this.player = Player.of(name);
        this.frames = Frames.init();
        this.currentFrameNumber = currentFrameNumber;
    }

    public static BowlingTurn of(String name) {
        return new BowlingTurn(name, FIRST_FRAME_NUMBER);
    }

    public void play(int pinCount) {
        frames.bowl(pinCount);
        if (frames.isDone()) {
            this.currentFrameNumber += 1;
        }
    }

    public boolean isLastFrame() {
        return this.currentFrameNumber == LAST_FRAME_NUMBER;
    }

    public boolean isEndTurn() {
        return frames.isDone();
    }

    public boolean isEndAllTurn() {
        return frames.isAllDone();
    }

    public String player() {
        return this.player.toString();
    }

    public List<Frame> frames() {
        return this.frames.getFrames();
    }

    public int currentFrameNumber() {
        return this.currentFrameNumber;
    }
}

package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

public class BowlingGame {

    private final Player player;
    private final Frame firstFrame;
    private Frame frame;

    public BowlingGame(final Player player) {
        this.player = player;
        this.frame = new NormalFrame();
        this.firstFrame = this.frame;
    }

    public void play(final int pinCount) {
        if (frame.isFinish()) {
            frame = frame.createNext();
        }

        frame.bowl(pinCount);
    }

    public String getPlayerName() {
        return player.getName();
    }

    public boolean isFinish() {
        if (frame instanceof FinalFrame && frame.isFinish()) {
            return true;
        }
        return false;
    }

    public Frame getFrame() {
        return frame;
    }

    public Frame getFirstFrame() {
        return firstFrame;
    }

    public int getFrameSize() {
        int count = 1;
        Frame frame = firstFrame;

        while (frame.getNext() != null) {
            frame = frame.getNext();
            count++;
        }

        return count + 1;
    }
}

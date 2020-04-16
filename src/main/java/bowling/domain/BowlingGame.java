package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.LinkedList;

public class BowlingGame {

    private final Player player;
    private final Frame frame;
    private final LinkedList<Frame> frames;

    public BowlingGame(final Player player) {
        this.player = player;
        this.frame = new NormalFrame();
        this.frames = new LinkedList<>();
    }

    public BowlingGame(final Player player, final Frame frame, final LinkedList<Frame> frames) {
        this.player = player;
        this.frame = frame;
        this.frames = frames;
    }

    public BowlingGame play(final int pinCount) {
        if (frame.isFinish()) {
            LinkedList<Frame> merge = new LinkedList<>(frames);
            Frame current = frame.createNext();
            merge.add(current);

            Frame next = current.getNext();
            next = next.bowl(pinCount);

            return new BowlingGame(player, next, merge);
        }

        Frame bowl = frame.bowl(pinCount);
        return new BowlingGame(player, bowl, frames);
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

    public LinkedList<Frame> getFrames() {
        return frames;
    }
}

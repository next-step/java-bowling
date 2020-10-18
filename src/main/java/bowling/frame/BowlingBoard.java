package bowling.frame;

import bowling.bowler.Bowler;

import java.util.LinkedList;

public class BowlingBoard {

    private final Bowler bowler;
    private LinkedList<Frame> frames;

    public BowlingBoard(Bowler bowler) {
        this.bowler = bowler;
        this.frames = new LinkedList<>();
        this.frames.add(NormalFrame.first());
    }

    public static BowlingBoard start(Bowler bowler) {
        return new BowlingBoard(bowler);
    }

    public Frame next() {
        return getLastFrame().next();
    }

    public Frame bowl(String fellPins) {
        Frame frame = getLastFrame().bowl(fellPins);

        if (getLastFrame().isFinish()) {
            frames.add(next());
        }
        return frame;
    }

    public String getBowlerName() {
        return bowler.getName();
    }

    public int getFrameNumber() {
        return getLastFrame().getFrameNumber();
    }

    public boolean isFinished() {
        return getLastFrame().isFinish();
    }

    public Frame getLastFrame() {
        return frames.getLast();
    }

    public LinkedList<Frame> getFrames() {
        return frames;
    }
}

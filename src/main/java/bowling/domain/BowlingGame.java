package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.point.Point;

public class BowlingGame {
    private static final int FIRST_FRAME_INDEX = 0;

    private Frames frames;

    public BowlingGame(Player player) {
        this.frames = new Frames(player);
    }

    public Frame throwBall(Frame frame, Point point) {
        frame.throwBall(point);
        frames.calculateScores(point);
        frame.addScore();
        return this.frames.getNextFrame();
    }

    public Frames getFrames() {
        return frames;
    }

    public Frame getFirstFrame() {
        return frames.getFrame(FIRST_FRAME_INDEX);
    }
}

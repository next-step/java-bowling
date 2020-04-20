package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.OverThrowBallException;
import bowling.domain.player.Player;
import bowling.domain.point.Point;

public class BowlingGame {
    private static final int FIRST_FRAME_INDEX = 0;
    private static final int LAST_FRAME_INDEX = 9;

    private Frames frames;

    public BowlingGame(Player player) {
        this.frames = new Frames(player);
    }

    public Frames throwBall(Point point) {
        Frame frame = frames.getNextFrame();
        try {
            frame.throwBall(point);
        } catch (OverThrowBallException e) {
            System.out.println(e.getMessage());
            return frames;
        }
        frames.calculateScores(point);
        frame.addScore();
        return frames;
    }

    public int currentFrameNo() {
        return frames.getFrameNo(frames.getNextFrame());
    }

    public Frames getFrames() {
        return frames;
    }

    public Frame getFirstFrame() {
        return frames.getFrame(FIRST_FRAME_INDEX);
    }

    public boolean isEnd() {
        return !frames.getFrame(LAST_FRAME_INDEX).isThrowable();
    }
}

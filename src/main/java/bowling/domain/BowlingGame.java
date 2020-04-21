package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.OverThrowBallException;
import bowling.domain.player.Player;
import bowling.domain.point.Point;

public class BowlingGame {
    private Frames frames;

    public BowlingGame(Player player) {
        this.frames = new Frames(player);
    }

    public void throwBall(Point point) {
        Frame frame = frames.getNextFrame();
        try {
            frame.throwBall(point);
        } catch (OverThrowBallException e) {
            System.out.println(e.getMessage());
            return;
        }
        frames.calculateScores(point);
    }

    public Frames getFrames() {
        return frames;
    }

    public int currentFrameNo() {
        return frames.size();
    }

    public boolean isEnd() {
        return frames.isEndLastFrame();
    }
}

package bowling.domain.game;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.OverThrowBallException;
import bowling.domain.player.Player;
import bowling.domain.point.Point;

public class BowlingGame {
    private final Player player;
    private Frames frames;

    public BowlingGame(Player player) {
        this.player = player;
        this.frames = new Frames();
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

    public void makeNextFrame() {
        this.frames.getNextFrame();
    }

    public String getPlayerName() {
        return player.getName();
    }

    public Frames getFrames() {
        return frames;
    }

    public boolean isCurrentRoundDone() {
        return !frames.getLastFrame().isThrowable();
    }

    public boolean isEnd() {
        return frames.isEndLastFrame();
    }
}

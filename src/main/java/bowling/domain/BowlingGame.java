package bowling.domain;

import bowling.frame.Frame;
import bowling.frame.Frames;
import bowling.frame.ShootScore;

public class BowlingGame {

    private static final int MAX_ROUND = 10;

    private final PlayerName playerName;
    private final Frames frames;

    private BowlingGame(PlayerName playerName, Frames frames) {
        this.playerName = playerName;
        this.frames = frames;
    }

    public static BowlingGame from(PlayerName playerName, Frames frames) {
        return new BowlingGame(playerName, frames);
    }

    public boolean isEnd() {
        return frames.currentRound() == MAX_ROUND;
    }

    public int currentRound() {
        return frames.currentRound();
    }

    public void shoot(ShootScore shootScore) {
        Frame nextFrame = frames.shoot(shootScore);

        if (nextFrame.isEnd()) {
            frames.goNextRound();
        }
    }

    public String playerName() {
        return playerName.playerName();
    }

    public Frames frames() {
        return frames;
    }
}
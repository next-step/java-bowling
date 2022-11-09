package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.scorestrategy.ScoreStrategy;

public class Player {

    private Pin now;
    private PlayerName playerName;
    private Frames frames;

    public Player(PlayerName playerName) {
        this.playerName = playerName;
        this.frames = new Frames();
    }

    public String getPlayerName() {
        return playerName.toString();
    }

    public Frames getFrames() {
        return frames;
    }

    public boolean isFinished() {
        return frames.isFinished();
    }

    public void play(ScoreStrategy scoreStrategy) {
        now = frames.bowl(scoreStrategy);
    }

    public int getNow() {
        return now.getValue();
    }

    public int nth() {
        return frames.getFrameNumber();
    }

    public boolean isEndOfTurn() {
        return frames.isEndOfTurn();
    }

    public boolean isTurnInProgress() {
        return !frames.isEndOfTurn();
    }


    public void next() {
        frames.next();
    }
}

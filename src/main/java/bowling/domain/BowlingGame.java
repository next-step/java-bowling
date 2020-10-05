package bowling.domain;

import bowling.domain.frame.Frames;

import java.util.List;

public class BowlingGame {

    private final Player player;
    private final Frames frames;

    public BowlingGame(String name) {
        this.player = new Player(name);
        this.frames = new Frames();
    }

    public String getName() {
        return player.getName();
    }

    public List<String> getSwingRecords() {
        return frames.getSwingRecords();
    }

    public List<String> getScores() {
        return frames.getScores();
    }

    public void swing(int score) {
        frames.swing(score);
    }

    public int currentRound() {
        return frames.getRound();
    }

    public boolean isBowlingGameRun() {
        return !frames.isEnd();
    }
}

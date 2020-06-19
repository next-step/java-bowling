package bowling.step4.domain;

import bowling.step4.domain.frame.Frame;
import bowling.step4.domain.scores.Scores;

public class PlayerFrame {
    private final String playerName;
    private final Frame frame;

    private PlayerFrame(String playerName, Frame frame) {
        this.playerName = playerName;
        this.frame = frame;
    }

    public static PlayerFrame of(PlayerFrames playerFrames) {
        return new PlayerFrame(playerFrames.getPlayerName(), playerFrames.getLastFrame());
    }

    public String playerName() {
        return playerName;
    }

    public Frame getFrame() {
        return this.frame;
    }

    public Scores getFrameScores() {
        return this.frame.getScores();
    }
}
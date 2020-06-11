package bowling.step2.domain;

import java.util.HashMap;
import java.util.Map;

public class Frame {

    private final int frame;
    private final PlayerScore playerScore;

    private Frame (int frame, PlayerScore playerScore) {
        this.frame = frame;
        this.playerScore = playerScore;
    }

    public static Frame of (int frame, PlayerScore playerScore) {
        return new Frame(frame, playerScore);
    }

    public static Frame init (int frame, Players players) {
        Map<PlayerName, FrameScore> playerScore = new HashMap<>();
        players.stream()
               .forEach(playerName -> playerScore.put(playerName, FrameScore.init()));
        return of(frame, PlayerScore.of(playerScore));
    }

    public Frame next () {
        int nextFrame = frame + 1;
        return of(nextFrame, playerScore.initBy());
    }

    public FrameScore scoreOfPlayer (PlayerName playerName) {
        return playerScore.getScoreOfPlayer(playerName);
    }

    public int getValue () {
        return frame;
    }

    public void addScore (PlayerName playerName, Score score) {
        playerScore.getScoreOfPlayer(playerName)
                   .addScore(score);
    }
}
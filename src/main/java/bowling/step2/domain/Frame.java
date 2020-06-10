package bowling.step2.domain;

import java.util.Collections;
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

    public int getValue () {
        return frame;
    }
}
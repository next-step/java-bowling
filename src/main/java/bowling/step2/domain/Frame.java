package bowling.step2.domain;

import java.util.HashMap;

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

    public Frame next () {
        int nextFrame = frame + 1;
        return of(nextFrame, PlayerScore.of(new HashMap<>()));
    }

    public int getValue () {
        return frame;
    }
}
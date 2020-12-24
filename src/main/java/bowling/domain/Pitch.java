package bowling.domain;

import bowling.exception.BowlingMaxCountException;

public class Pitch {

    private static final int BOWLING_MAX_NUMBER = 10;

    private final int knockDown;

    private Pitch(int knockDown) {
        this.knockDown = knockDown;
    }

    public static Pitch of(int nextKnockDown) {
        if (nextKnockDown > BOWLING_MAX_NUMBER) {
            throw new BowlingMaxCountException();
        }
        return new Pitch(nextKnockDown);
    }

    public int getKnockDown() {
        return knockDown;
    }
}

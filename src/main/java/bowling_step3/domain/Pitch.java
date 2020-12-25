package bowling_step3.domain;

import bowling_step3.exception.BowlingMaxCountException;

public class Pitch {

    public static final int BOWLING_MAX_NUMBER = 10;

    private final int knockDown;

    private Pitch(int knockDown) {
        this.knockDown = knockDown;
    }

    public static Pitch valueOf(int nextKnockDown) {
        if (nextKnockDown > BOWLING_MAX_NUMBER) {
            throw new BowlingMaxCountException();
        }
        return new Pitch(nextKnockDown);
    }

    public int getKnockDown() {
        return knockDown;
    }
}

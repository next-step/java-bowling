package bowling.domain;

import bowling.exception.CannotCreateException;

public class PinScore {
    public static final int MIN = 0;
    public static final int MAX = 10;
    private int score;

    public PinScore(int score) throws CannotCreateException {
        validation(score);
        this.score = score;
    }

    public int value() {
        return this.score;
    }

    private void validation(int score) throws CannotCreateException {
        if (score < MIN) {
            throw new CannotCreateException(CannotCreateException.SCORE_ERROR_MSG);
        }

        if (score > MAX) {
            throw new CannotCreateException(CannotCreateException.SCORE_ERROR_MSG);
        }
    }

}

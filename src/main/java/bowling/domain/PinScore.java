package bowling.domain;

import bowling.exception.CannotCreateException;

public class PinScore {
    private static final int MIN = 0;
    private static final int MAX = 10;
    private int score;

    public PinScore(int score) throws CannotCreateException {
        validation(score);
        this.score = score;
    }

    public int score() {
        return this.score;
    }

    private void validation(int score) throws CannotCreateException {
        if (score < MIN) {
            throw new CannotCreateException(CannotCreateException.SCORE_EXCEPTION);
        }

        if (score > MAX) {
            throw new CannotCreateException(CannotCreateException.SCORE_EXCEPTION);
        }
    }


}

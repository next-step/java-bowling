package bowling.domain;

import bowling.exceptions.CannotCalculteException;

public class Score {

    private static final int GAME_STEP = 1;

    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score bowl(int countOfPins) {
        return new Score(score += countOfPins, left - GAME_STEP);
    }

    public int getScore() throws CannotCalculteException {
        if (!canCalculateScore()) {
            throw new CannotCalculteException();
        }
        return this.score;
    }

    public boolean canCalculateScore() {
        return left == 0;
    }


}

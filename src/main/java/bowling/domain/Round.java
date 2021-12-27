package bowling.domain;

import bowling.exception.MaximumRoundException;
import bowling.exception.MinimumRoundException;

public class Round {

    private static final int MINIMUM_ROUND = 1;
    private static final int MAXIMUM_ROUND = 10;

    private final int round;

    public Round(int round) {
        valid(round);
        this.round = round;
    }

    public boolean isFinalRound() {
        return this.round == MAXIMUM_ROUND;
    }

    private void valid(int round) {
        if (round < MINIMUM_ROUND) {
            throw new MinimumRoundException(MINIMUM_ROUND);
        }

        if (round > MAXIMUM_ROUND) {
            throw new MaximumRoundException(MAXIMUM_ROUND);
        }
    }

}

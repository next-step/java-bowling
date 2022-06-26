package bowling_step3.state;

import bowling_step3.domain.Scores;

public class Spare extends Done {
    private final Scores scores;

    public Spare(Scores scores) {
        validate(scores);
        this.scores = scores;
    }

    private void validate(Scores scores) {
        if (!scores.isSpare()) {
            throw new IllegalArgumentException("argument is not spare");
        }
    }

    public boolean calculateAdditionalScore(Scores score) {
        return false;
    }
}

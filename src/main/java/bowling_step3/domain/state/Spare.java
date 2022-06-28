package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

public class Spare extends Done {
    public Spare(Scores scores) {
        super(scores);
//        validate(scores);
    }

    private void validate(Scores scores) {
        if (!scores.isSpare()) {
            throw new IllegalArgumentException("argument is not spare");
        }
    }

    public int calculateAdditionalScore(Status status) {
        return this.scores().sum() + status.scores().getFirstScore();
    }
}

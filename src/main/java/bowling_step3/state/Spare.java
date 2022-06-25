package bowling_step3.state;

import bowling_step3.domain.Scores;

public class Spare implements State {
    private final Scores scores;

    public Spare(Scores scores) {
        this.scores = scores;
    }
}

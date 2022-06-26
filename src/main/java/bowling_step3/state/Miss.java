package bowling_step3.state;

import bowling_step3.domain.Scores;

public class Miss implements State {
    private final Scores scores;

    public Miss(Scores scores) {
        this.scores = scores;
    }
}

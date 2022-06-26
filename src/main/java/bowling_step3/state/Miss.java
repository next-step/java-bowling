package bowling_step3.state;

import bowling_step3.domain.Scores;

public class Miss extends Done {
    private final Scores scores;

    public Miss(Scores scores) {
        this.scores = scores;
    }


}

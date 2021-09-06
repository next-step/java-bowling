package bowling.domain;

public class Frame {

    private Scores scores;

    public Frame(final Scores scores) {
        this.scores = scores;
    }

    public void roll(final Score score) {
        this.scores.roll(score);
    }
}

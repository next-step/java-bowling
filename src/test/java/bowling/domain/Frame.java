package bowling.domain;

import java.util.ArrayList;

public class Frame {
    public static final Score MAX_SCORE = new Score(10);
    private final Scores scores;
    private FrameResult result;

    public Frame() {
        this.scores = new Scores(new ArrayList<>());
    }

    public Frame(Scores scores) {
        this.scores = scores;
    }

    public Scores getScores() {
        return scores;
    }

    public boolean isEnd() {
        return scores.sum().equals(MAX_SCORE) || scores.size() == 2;
    }
}

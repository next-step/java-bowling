package bowling.step2.domain.frame;

import bowling.step2.domain.scores.NormalScores;
import bowling.step2.domain.scores.Scores;

public class NormalFrame implements Frame {

    private final int frame;
    private final Scores scores;

    private NormalFrame(int frame, Scores scores) {
        this.frame = frame;
        this.scores = scores;
    }

    public static Frame of (int frame, Scores scores) {
        return new NormalFrame(frame, scores);
    }

    public Scores getScores () {
        return scores;
    }

    public Frame next () {
        return of(frame + 1, NormalScores.init());
    }
}
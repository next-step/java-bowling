package bowling.step2.domain;

import java.util.HashMap;
import java.util.Map;

public class Frame {

    private final int frame;
    private final Scores scores;

    private Frame (int frame, Scores scores) {
        this.frame = frame;
        this.scores = scores;
    }

    public static Frame of (int frame, Scores scores) {
        return new Frame(frame, scores);
    }

    public Scores getScores () {
        return scores;
    }

    public int getValue () {
        return frame;
    }
}
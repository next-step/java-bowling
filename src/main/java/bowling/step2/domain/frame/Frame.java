package bowling.step2.domain.frame;

import bowling.step2.domain.scores.Scores;

public interface Frame {
    static final int FIRST_FRAME = 1;
    static final int LAST_FRAME = 10;

    Scores getScores();
    Frame createNextFrame();
    Frame getNextFrame();
    boolean hasNext();
}
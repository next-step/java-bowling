package bowling.domain.frames;

import bowling.domain.Score;
import bowling.domain.Scores;


public interface Frame {

    void roll(final Score score);

    boolean isSpare();

    boolean isStrike();

    Scores getScores();

    boolean isFinish();
}

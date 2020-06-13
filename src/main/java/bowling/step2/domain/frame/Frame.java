package bowling.step2.domain.frame;

import bowling.step2.domain.scores.Scores;

public interface Frame {
    Scores getScores();
    Frame createNextFrame(Scores scores);
    Frame getPrevFrame();
    int getValue();
}
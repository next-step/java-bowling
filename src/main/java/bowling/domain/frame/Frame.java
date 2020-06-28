package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public interface Frame {
    Frame createNext(boolean isNextLast);

    boolean canAddMoreScore();

    void addScore(Score score);

    Scores getScores();

    int getIndex();
}

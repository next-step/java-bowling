package bowling.step2.domain.scores;

import bowling.step2.domain.Score;

public interface Scores {

    Scores firstInit (Score score);
    Scores secondInit (Score score);
    boolean isStrike ();
    boolean isSpared ();
    int totalScore ();

}
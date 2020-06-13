package bowling.step2.domain.scores;

import bowling.step2.domain.Score;

import java.util.stream.Stream;

public interface Scores {

    Scores nextInit (Score score);
    boolean isStrike ();
    boolean isSpared ();
    boolean isFullOf ();
    int totalScore ();
    Stream<Score> stream ();

}
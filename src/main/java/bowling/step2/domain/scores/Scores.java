package bowling.step2.domain.scores;

import bowling.step2.domain.Score;

import java.util.List;
import java.util.stream.Stream;

public interface Scores {

    Scores nextInit (Score score);
    boolean isStrike ();
    boolean isSpared ();
    boolean isEmptyOf (int index);
    boolean isFullOf ();
    int totalScore ();
    Stream<Score> stream ();

}
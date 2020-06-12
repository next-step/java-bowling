package bowling.step2.domain.scores;

import bowling.step2.domain.Score;

import java.util.List;
import java.util.stream.Stream;

public interface Scores {

    Scores firstInit (Score score);
    Scores secondInit (Score score);
    boolean isStrike ();
    boolean isSpared ();
    boolean isEmptyOf (int index);
    int totalScore ();
    Stream<Score> stream ();

}
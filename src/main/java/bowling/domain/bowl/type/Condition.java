package bowling.domain.bowl.type;

import bowling.domain.score.Scores;

@FunctionalInterface
public interface Condition {
    boolean check(Scores scores);
}

package bowling.domain.bowl.type;

import bowling.domain.bowl.Bowl;
import bowling.domain.score.Scores;

@FunctionalInterface
public interface Creation {
    Bowl create(Scores scores);
}

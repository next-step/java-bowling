package bowling.domain.frame;

import bowling.domain.score.ScoreType;
import java.util.List;
import java.util.Optional;

public interface Pins {

    void down(int downPin);

    boolean hasTurn();

    ScoreType getScoreType();

    List<Integer> getDownPins();

    int sum();
}

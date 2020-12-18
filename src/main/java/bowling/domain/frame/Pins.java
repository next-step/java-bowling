package bowling.domain.frame;

import bowling.domain.score.ScoreType;
import java.util.List;

public interface Pins {

    void down(int downPin);

    boolean hasTurn();

    ScoreType getScoreType();

    List<Integer> getDownPins();

    int sum();
}

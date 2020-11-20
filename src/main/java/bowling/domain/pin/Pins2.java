package bowling.domain.pin;

import bowling.domain.score.ScoreType;
import bowling.domain.score.ScoreType2;

import java.util.List;

public interface Pins2 {

    void down(int pin);

    boolean canRoll();

    ScoreType2 getScoreType();

    List<Integer> getDownPins();

    int sum();
}

package bowling.domain.pin;

import bowling.domain.score.ScoreType;

import java.util.List;

public interface Pins {

    void down(int pin);
    boolean canRoll();
    ScoreType getScoreType();
    List<Integer> getDownPins();
    int sum();
}

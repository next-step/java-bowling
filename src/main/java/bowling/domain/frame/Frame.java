package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.pin.Pins;

public interface Frame {

    void addPoint(int bonusPoint);

    void bowl(int pin);

    boolean isEnd();

    boolean endedScoring();

    Pins pins();

    Score score();
}

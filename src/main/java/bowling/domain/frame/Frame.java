package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.pin.Pins;

public interface Frame {

    int STRIKE_BONUS = 2;
    int SPARE_BONUS = 1;
    int NO_BONUS = 0;

    void addPoint(int bonusPoint);

    void bowl(int pin);

    boolean isEnd();

    void endScoring();

    int bonusAmount();

    boolean endedScoring();

    Pins pins();

    Score score();
}

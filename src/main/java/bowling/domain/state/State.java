package bowling.domain.state;

import bowling.annotations.ForUI;
import bowling.domain.Score;

public interface State {
    State bowl(int pinCount);
    boolean isEnd();
    boolean hasBonus();

    @ForUI
    Score makeScore();

    @ForUI
    Score additionalCalculate(Score beforeScore);

    @ForUI
    boolean isFinished();

    @ForUI
    String display();
}

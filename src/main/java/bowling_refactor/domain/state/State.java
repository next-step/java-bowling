package bowling_refactor.domain.state;

import bowling_refactor.domain.Pin;
import bowling_refactor.domain.Score;

import java.util.List;

public interface State {

    State bowl(int countOfPin, boolean isLastFrame);

    boolean isSpare();

    int sum();

    List<Pin> getPins();

    Score addBonus(Score score);

    Score getScore(int totalStore);

    boolean isEnd();
}

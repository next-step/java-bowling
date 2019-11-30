package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.List;

public interface State {
    State bowl(int countOfPin, boolean isLastFrame);

    Score getScore(int totalStore);

    int sum();

    Score addBonus(Score score);

    List<Pin> getPins();

    boolean isEnd();
}

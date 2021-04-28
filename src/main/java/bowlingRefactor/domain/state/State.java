package bowlingRefactor.domain.state;

import bowlingRefactor.domain.Pin;
import bowlingRefactor.domain.Score;

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

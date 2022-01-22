package bowling.state;

import bowling.Pins;
import bowling.frame.Score;

public interface Throwing {

    Throwing bowl(Pins fallenPins);

    String symbol();
    boolean isEnd();
    boolean isMiss();
    Score getScore();
    Score calculateAdditionalScore(Score beforeScore);
}

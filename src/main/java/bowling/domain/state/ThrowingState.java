package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.frame.Score;
import bowling.exception.CannotScoreCalculateException;

public interface ThrowingState {

    ThrowingState bowl(Pins pins);

    String symbol();

    boolean isMiss();

    boolean isEnd();

    Score getScore();

    Score calculateAdditionalScore(Score score) throws CannotScoreCalculateException;
}

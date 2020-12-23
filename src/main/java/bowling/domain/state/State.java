package bowling.domain.state;

import bowling.domain.score.Score;

public interface State {
    Score getScore();

    boolean isFinished();

    String getSymbol();
}

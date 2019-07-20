package domain.state;

import domain.score.Score;

public interface State {

    String toSymbol();

    boolean isClosed();


    Score getScore();

    Score calculateBonusScore(Score beforeScore);
}

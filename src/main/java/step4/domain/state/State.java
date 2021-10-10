package step4.domain.state;

import step4.domain.Score;

public interface State {

    State throwBowl(int falledPins);
    
    int getScore();

    Score score();

    Score calculateScore(Score beforeScore);

    boolean isFinish();

    String getSymbol();
}

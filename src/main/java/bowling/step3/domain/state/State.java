package bowling.step3.domain.state;

import bowling.step3.domain.score.Score;

public interface State {

    State pitch(Pins pins);

    boolean isFinish();

    String display();

    Score getScore();

    Score addAdditionalScore(Score prevScore);
}

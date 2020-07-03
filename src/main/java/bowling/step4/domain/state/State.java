package bowling.step4.domain.state;

import bowling.step4.domain.score.Score;

public interface State {

    State pitch(Pins pins);

    boolean isFinish();

    String display();

    Score getScore();

    Score addAdditionalScore(Score prevScore);
}

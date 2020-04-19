package bowling.domain.frame.state;

import bowling.domain.Score;

public interface State {

    String CANT_THROW_BALL = "투구 불가능한 상태 입니다";

    State bowl(int pinsCount);

    boolean isFinish();

    String getCurrentPinsState();

    Score getScore();

    Score getFirstScore();
}

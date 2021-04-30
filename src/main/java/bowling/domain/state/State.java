package bowling.domain.state;

import bowling.domain.HitCount;

public interface State {
    State bowl(HitCount hitCount);
    boolean isFinish();

    int getScore();
}

package bowling.domain.state;

import bowling.domain.HitCount;

public interface State {

    boolean isFinish();

    State bowl(HitCount hitCount);
}

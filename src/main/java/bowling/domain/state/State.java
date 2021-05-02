package bowling.domain.state;

import bowling.domain.HitCount;

public interface State {

    static State ready() {
        return Ready.initialize();
    }

    State bowl(HitCount hitCount);

    boolean isFinish();

    boolean isAllPinClear();

    int size();

    int firstCount();

    int secondCount();
}

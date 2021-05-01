package bowling.domain.state;

import bowling.domain.HitCount;

public interface State {

    static State initialize() {
        return Ready.initialize();
    }

    State bowl(HitCount hitCount);

    boolean isFinish();

}

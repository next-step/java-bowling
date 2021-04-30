package bowling.domain.state;

import bowling.domain.HitCount;

public class Ready extends Running {
    @Override
    public State bowl(HitCount hitCount) {
        return null;
    }

    public static State initialize() {
        return new Ready();
    }

}

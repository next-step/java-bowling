package bowling.domain.state;

import bowling.domain.HitCount;

public final class Ready extends Running {

    @Override
    public State bowl(final HitCount hitCount) {
        return null;
    }

    public static State initialize() {
        return new Ready();
    }

}

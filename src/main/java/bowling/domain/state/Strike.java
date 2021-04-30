package bowling.domain.state;

import bowling.domain.HitCount;

public class Strike extends Finish {

    private Strike() { }

    public static State newInstance() {
        return new Strike();
    }

    @Override
    public State bowl(HitCount hitCount) {
        return null;
    }
}

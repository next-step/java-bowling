package bowling.domain.state;

import bowling.domain.HitCount;

public class Strike extends Finish {

    public static final int STRIKE_POINT = 10;

    @Override
    public int getScore() {
        return STRIKE_POINT;
    }
}

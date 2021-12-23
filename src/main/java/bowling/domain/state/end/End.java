package bowling.domain.state.end;

import bowling.domain.pitch.Pitch;
import bowling.domain.frame.Frame;
import bowling.domain.state.State;

public abstract class End implements State {
    public static final String STRIKE_SYMBOL = "X";
    public static final String SPARE_SYMBOL = "/";
    public static final String GUTTER_SYMBOL = "-";
    public static final String OR = "|";

    @Override
    public boolean isBonus() {
        return isEnd() && !isMiss();
    }

    @Override
    public State run(Pitch pitch) {
        throw new IllegalArgumentException("종료된 프레임 입니다.");
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public String toString() {
        return "End{}";
    }
}

package bowling.state.ended;

import bowling.Pins;
import bowling.state.Throwing;

public abstract class Ended implements Throwing {

    @Override
    public Throwing bowl(Pins fallenPins) {
        throw new UnsupportedOperationException("종료된 프레임 입니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}

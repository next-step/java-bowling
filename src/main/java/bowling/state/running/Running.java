package bowling.state.running;

import bowling.state.Throwing;

public abstract class Running implements Throwing {

    @Override
    public boolean isEnd() {
        return false;
    }
}

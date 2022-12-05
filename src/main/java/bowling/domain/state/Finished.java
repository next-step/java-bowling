package bowling.domain.state;

import bowling.domain.Point;
import bowling.exception.AlreadyFinishedException;

public abstract class Finished implements State {

    @Override
    public State bowl(Point point) {
        throw new AlreadyFinishedException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}

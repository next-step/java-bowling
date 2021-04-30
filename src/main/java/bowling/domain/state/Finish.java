package bowling.domain.state;

import static java.lang.Boolean.TRUE;

public abstract class Finish implements State {

    @Override
    public boolean isFinish() {
        return TRUE;
    }

}

package bowling.domain.state;

import static java.lang.Boolean.TRUE;

public abstract class Finished implements State {

    @Override
    public boolean isFinished() {
        return TRUE;
    }

}

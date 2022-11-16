package bowling.domain.status;

import bowling.domain.Pin;
import bowling.domain.Score;

public abstract class Ongoing extends Status {

    @Override
    public boolean isFinished() {
        return false;
    }

}

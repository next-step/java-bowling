package bowling.domain.status.finished;

import bowling.domain.point.Point;
import bowling.domain.status.Status;

public abstract class Finished implements Status {

    @Override
    public Status throwBall(Point point) {
        return null;
    }

    @Override
    public boolean isEnd(){
        return true;
    }
}

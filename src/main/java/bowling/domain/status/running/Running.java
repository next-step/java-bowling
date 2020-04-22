package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.status.Status;

public abstract class Running implements Status{

    @Override
    public Status throwBall(Point point) {
        return null;
    }

    @Override
    public boolean isEnd(){
        return false;
    }
}

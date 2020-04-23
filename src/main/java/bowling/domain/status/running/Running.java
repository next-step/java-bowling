package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.score.ScroeAccessDenyException;
import bowling.domain.status.Status;

public abstract class Running implements Status {

    @Override
    public Status throwBall(Point point) {
        throw new ScroeAccessDenyException("던질수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}

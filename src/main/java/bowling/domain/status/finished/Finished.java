package bowling.domain.status.finished;

import bowling.domain.point.Point;
import bowling.domain.score.ScroeAccessDenyException;
import bowling.domain.status.Status;

public abstract class Finished implements Status {

    @Override
    public Status throwBall(Point point) {
        throw new ScroeAccessDenyException("던질수 없습니다. 이미 끝난 프레임입니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}

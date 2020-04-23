package bowling.domain.status.running;

import bowling.domain.point.Point;
import bowling.domain.score.NoneScoreException;
import bowling.domain.score.Score;
import bowling.domain.status.Status;
import bowling.domain.status.finished.Strike;

public class Ready extends Running {

    public static Ready of() {
        return new Ready();
    }

    @Override
    public Status throwBall(Point point) {
        if (point.isMaxPoint()) {
            return Strike.of();
        }
        return Cover.of(point);
    }

    @Override
    public String print() {
        return "";
    }

    @Override
    public Score getScore() {
        throw new NoneScoreException("스코어가 없습니다");
    }
}

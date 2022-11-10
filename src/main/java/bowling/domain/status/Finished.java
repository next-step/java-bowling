package bowling.domain.status;

import bowling.domain.Pin;
import bowling.domain.Score;

public class Finished extends Status {
    @Override
    public Status bowl(Pin pin) {
        throw new UnsupportedOperationException("더 이상 공을 던질 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Score getScore() {
        return new Score(0,0);
    }
}

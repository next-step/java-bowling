package bowling.domain.State;

import bowling.domain.Score;
import bowling.exception.CannotCalculateException;

public class Ready extends Running {
    @Override
    public State bowl(int felledPins) {
        Pins firstPins = Pins.bowl(felledPins);
        if (firstPins.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(firstPins);
    }

    @Override
    public String getDesc() {
        return STANDBY;
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        throw new CannotCalculateException("아직 계산이 끝나지 않아 스코어를 반환 할 수 없습니다.");
    }
}

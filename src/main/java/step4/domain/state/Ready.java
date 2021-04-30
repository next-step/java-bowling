package step4.domain.state;

import step4.domain.PinCount;
import step4.domain.Score;

public class Ready extends Running {
    private static final String CANNOT_ADD_SCORE = "현재 프레임이 한 번도 진행되지 않았기 때문에 점수를 더할 수 없습니다.";

    @Override
    public State bowl(int pinCount) {
        PinCount knockedDownPinCount = new PinCount(pinCount);

        if (knockedDownPinCount.isStrike()) {
            return new Strike();
        }

        return new FirstBowl(knockedDownPinCount);
    }

    @Override
    public Score addScore(Score score) {
        throw new IllegalStateException(CANNOT_ADD_SCORE);
    }

    @Override
    public String marks() {
        return "";
    }
}
package bowling.domain.state;

import bowling.domain.Score;

public class Ready implements State {
    public static final String READY_CANNOT_MAKE_SCORE_MESSAGE = "준비상태는 점수를 만들 수 없습니다.";
    public static final String READY_CANNOT_CALCULATE_SCORE_MESSAGE = "준비상태는 점수 계산을 할 수 없습니다.";

    private static final String EMPTY_MARK = "";
    private static final int STRIKE_COUNT = 10;

    @Override
    public State bowl(int pinCount) {
        if (pinCount == STRIKE_COUNT) {
            return new Strike();
        }
        return new FirstBowl(pinCount);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean hasBonus() {
        return false;
    }

    @Override
    public Score makeScore() {
        throw new IllegalArgumentException(READY_CANNOT_MAKE_SCORE_MESSAGE);
    }

    @Override
    public Score additionalCalculate(Score beforeScore) {
        throw new IllegalArgumentException(READY_CANNOT_CALCULATE_SCORE_MESSAGE);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public String display() {
        return EMPTY_MARK;
    }
}

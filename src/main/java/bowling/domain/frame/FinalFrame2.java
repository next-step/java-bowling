package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.score.ScoreGenerator;
import bowling.domain.score.State;

import java.util.ArrayList;
import java.util.List;

public class FinalFrame2 implements Frame2 {
    private static final String ROLL_COUNT_ERRORS = "더 이상 던질 수 없습니다";
    private static final String ROLL_SIZE_ERROR = "첫 투구가 있어야합니다";
    private static final String BLOCK = "|";
    private static final String BLANK = "";
    private List<State> state;
    private int rollCount = 0;
    private int point = 0;

    public FinalFrame2(List<State> state, int point, int rollCount) {
        validate(rollCount);
        this.state = state;
        this.rollCount = rollCount;
        this.point = point;
    }

    private void validate(int rollCount) {
        if (rollCount > MAX_FINAL_FRAME_CAN_ROLL) {
            throw new IllegalArgumentException(ROLL_COUNT_ERRORS);
        }
    }

    @Override
    public Frame2 roll(Point point) {
        if (rollCount == 0) {
            return firstRoll(point);
        }
        return nextRoll(point);
    }

    private Frame2 firstRoll(Point point) {
        int totalPoint = this.point + point.getPoint();
        State state = ScoreGenerator.of(point);
        this.state.add(state);
        if (state.isStrike()) {
            return FinalFrame2.of(this.state, point.getPoint(), rollCount + 1);
        }
        return FinalFrame2.of(this.state, totalPoint, rollCount + 1);
    }

    private Frame2 nextRoll(Point point) {
        rollCheck();
        State state = this.state.get(rollCount - 1);
        State nextState = state.nextScore(point);
        this.state.add(nextState);
        int totalPoint = this.point + point.getPoint();

        if (nextState.isSpare()) {
            return FinalFrame2.of(this.state, totalPoint, rollCount + 1);
        }
        if (state.isStrike()) {
            return FinalFrame2.of(this.state, totalPoint, rollCount + 1);
        }
        return FinalFrame2.of(this.state, totalPoint, MAX_FINAL_FRAME_CAN_ROLL);
    }

    private void rollCheck() {
        if (state.size() == 0) {
            throw new IllegalArgumentException(ROLL_SIZE_ERROR);
        }
    }

    public boolean isLastRoll() {
        return rollCount == MAX_FINAL_FRAME_CAN_ROLL;
    }

    public static Frame2 of() {
        List<State> states = new ArrayList<>();
        return new FinalFrame2(states, 0, 0);
    }

    public static Frame2 of(List<State> states, int point, int rollCount) {
        return new FinalFrame2(states, point, rollCount);
    }

    @Override
    public String getState() {
        if (state.size() == FIRST_ROLL) {
            return state.get(0).getScore();
        }
        if (state.size() == SECOND_ROLL) {
            return state.get(0).getScore() + BLOCK + state.get(1).getScore();
        }
        if (state.size() == BONUS_ROLL) {
            return state.get(0).getScore() + BLOCK + state.get(1).getScore() + BLOCK + state.get(2).getScore();
        }
        return BLANK;
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public int getPoint() {
        if (rollCount == MAX_FINAL_FRAME_CAN_ROLL) {
            return this.point;
        }
        return 0;
    }
}

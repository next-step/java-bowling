package bowling.domain.frame;

import bowling.domain.point.Point;
import bowling.domain.score.Score2;
import bowling.domain.score.ScoreGenerator;
import bowling.domain.score.State;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame2 implements Frame2 {
    private static final String ROLL_COUNT_ERRORS = "일반 게임에서는 두번만 던질 수 있습니다.";
    private static final String ROLL_SIZE_ERROR = "첫 투구가 있어야합니다";
    private static final String BLOCK = "|";
    private static final String NONE = "";

    private List<State> states;
    private int rollCount = 0;
    private int point = 0;

    private NormalFrame2(List<State> states, int point, int rollCount) {
        validate(rollCount);
        this.states = states;
        this.rollCount = rollCount;
        this.point = point;
    }

    private void validate(int rollCount) {
        if (rollCount > MAX_NORMAL_FRAME_CAN_ROLL) {
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

    public Frame2 firstRoll(Point point) {
        State state = ScoreGenerator.of(point);
        states.add(state);

        if (state.isStrike()) {
            return NormalFrame2.of(states, state.getPoint(), MAX_NORMAL_FRAME_CAN_ROLL);
        }
        return NormalFrame2.of(states, state.getPoint(), rollCount + 1);
    }

    private Frame2 nextRoll(Point point) {
        rollCheck();
        State firstState = states.get(rollCount - 1);
        State nextState = firstState.nextScore(point);
        states.add(nextState);
        int totalPoint = this.point + point.getPoint();

        if (firstState.isSpare()) {
            NormalFrame2.of(states, totalPoint, MAX_NORMAL_FRAME_CAN_ROLL);
        }
        return NormalFrame2.of(states, totalPoint, MAX_NORMAL_FRAME_CAN_ROLL);
    }

    private void rollCheck() {
        if (states.size() == 0) {
            throw new IllegalArgumentException(ROLL_SIZE_ERROR);
        }
    }

    public boolean isLastRoll() {
        return rollCount == MAX_NORMAL_FRAME_CAN_ROLL;
    }

    @Override
    public boolean isFinalFrame() {
        return false;
    }

    @Override
    public String getState() {
        if (states.size() == FIRST_ROLL) {
            return states.get(0).getScore();
        }
        if (states.size() == SECOND_ROLL) {
            return states.get(0).getScore() + BLOCK + states.get(1).getScore();
        }
        return NONE;
    }

    @Override
    public int getPoint() {
        if (rollCount == MAX_FINAL_FRAME_CAN_ROLL) {
            return this.point;
        }
        return 0;
    }

    public static Frame2 of() {
        List<State> states = new ArrayList<>();
        return new NormalFrame2(states, 0, 0);
    }

    public static Frame2 of(List<State> states, int point, int rollCount) {
        return new NormalFrame2(states, point, rollCount);
    }
}

package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.FirstBowl;
import bowling.domain.bowl.SecondBowl;
import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import bowling.domain.state.Gutter;
import bowling.domain.state.Miss;
import bowling.domain.state.State;
import bowling.domain.state.States;

/**
 * Created : 2020-12-16 오전 9:30
 * Developer : Seo
 */
public class NormalFrame extends Frame {
    public static final int NEXT_FRAME = 1;
    public static final int LAST_FRAME = 9;

    private final States states;
    private final int frameNo;

    public NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.states = new States();
    }

    @Override
    public Frame next() {
        return this.frameNo + NEXT_FRAME == LAST_FRAME ?
                new FinalFrame() : new NormalFrame(this.frameNo + NEXT_FRAME);
    }

    @Override
    public void bowl(Pins pins) {
        Bowl firstBowl = new FirstBowl();
        State state = firstBowl.stroke(pins);
        states.add(state);
    }

    @Override
    public void secondBowl(int userIndex, State firstBowlState, Pins pins) {
        validateUserIndex(userIndex);
        validate(firstBowlState, pins);

        if (firstBowlState instanceof Miss || firstBowlState instanceof Gutter) {
            Score firstScore = firstBowlState.getScore();
            Bowl secondBowl = new SecondBowl(firstScore);
            State secondState = secondBowl.stroke(pins);
            states.set(userIndex, secondState);
        }
    }

    @Override
    public void thirdBowl(int userIndex, State state, Pins thirdPins) {
    }

    @Override
    public State getState(int userIndex) {
        validateUserIndex(userIndex);
        return this.states.getState(userIndex);
    }

    @Override
    public String getSymbol(int userIndex) {
        validateUserIndex(userIndex);
        return this.states.getState(userIndex).getSymbol();
    }

    @Override
    public int getFrameScore(int userIndex) {
        validateUserIndex(userIndex);
        return this.states.getState(userIndex).getScore().getFrameScore();
    }

    @Override
    public int getFirstScore(int userIndex) {
        validateUserIndex(userIndex);
        return this.states.getState(userIndex).getScore().getFirst().get();
    }

    @Override
    public int getSecondScore(int userIndex) {
        validateUserIndex(userIndex);
        return this.states.getState(userIndex).getScore().getSecond().get();
    }

    @Override
    public State getLastState() {
        return this.states.getLast();
    }

    @Override
    public int getLastStateFrameScore() {
        return getLastState().getScore().getFrameScore();
    }

    @Override
    public int getLastStateFirstScore() {
        return getLastState().getScore().getFirst().get();
    }

    @Override
    public int getLastStateSecondScore() {
        return getLastState().getScore().getSecond().get();
    }

    @Override
    public String getLastStateSymbol() {
        return getLastState().getSymbol();
    }

    private void validate(State firstBowlState, Pins secondPins) {
        int firstPins = firstBowlState.getScore().getFirst().get();
        if (firstPins + secondPins.get() > Pins.MAX_PINS) {
            throw new IllegalArgumentException("잘못된 2구 핀입니다.");
        }
    }

    private void validateUserIndex(int userIndex) {
        if (userIndex >= this.states.size()) {
            throw new IllegalArgumentException(String.format("사용자를 확인해주십시요. 현재 사용자 번호 : %d", userIndex));
        }
    }
}

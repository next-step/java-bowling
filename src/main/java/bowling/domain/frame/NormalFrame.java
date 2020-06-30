package bowling.domain.frame;

import bowling.domain.state.State;
import bowling.domain.state.StateCreator;

import java.util.ArrayList;
import java.util.List;

public class NormalFrame implements Frame {

    private static final String SEPARATOR = "|";

    private List<State> states;
    private int pitchCount;
    private int framePoint;

    private NormalFrame(List<State> states, int framePoint, int pitchCount) {
        validatePitchCount(pitchCount);
        this.states = states;
        this.pitchCount = pitchCount;
        this.framePoint = framePoint;
    }

    public static Frame create() {
        List<State> states = new ArrayList<>();
        return new NormalFrame(states, 0, 0);
    }

    public static Frame create(List<State> states, int framePoint, int pitchCount) {
        return new NormalFrame(states, framePoint, pitchCount);
    }

    @Override
    public Frame bowl(Point point) {
        if (pitchCount == 0) {
            return firstBowl(point);
        }
        return nextBowl(point);
    }

    public boolean isLastPitch() {
        return pitchCount == NORMAL_MAX_BOWL_PITCH;
    }

    private Frame firstBowl(Point point) {
        State state = StateCreator.create(point);
        states.add(state);

        if (state.isStrike()) {
            int strikePoint = state.getPoint();
            return NormalFrame.create(states, strikePoint, NORMAL_MAX_BOWL_PITCH);
        }

        return NormalFrame.create(states, state.getPoint(), ++pitchCount);
    }

    private Frame nextBowl(Point point) {
        validateNextBowl();
        State state = states.get(pitchCount - 1);
        State nextState = state.nextScore(point);
        states.add(nextState);

        if (state.isSpare()) {
            int sparePoint = state.getPoint();
            NormalFrame.create(states, this.framePoint + sparePoint, NORMAL_MAX_BOWL_PITCH);
        }

        return NormalFrame.create(states, this.framePoint + state.getPoint(), NORMAL_MAX_BOWL_PITCH);
    }

    @Override
    public String getStates() {
        String result = "";
        if (states.size() == FRAME_SECOND_PITCH_END) {
            return states.get(0).getScore() + SEPARATOR + states.get(1).getScore();
        }

        if (states.size() == FRAME_FIRST_PITCH_END) {
            return states.get(0).getScore();
        }

        return result;
    }

    @Override
    public int getFramePoint() {
        if (pitchCount == FRAME_SECOND_PITCH_END) {
            return this.framePoint;
        }
        return 0;
    }

    private void validateNextBowl() {
        if (states.size() == 0) {
            throw new IllegalArgumentException("첫 투구를 하지 않았습니다.");
        }
    }

    private void validatePitchCount(int pitchCount) {
        if (pitchCount > NORMAL_MAX_BOWL_PITCH) {
            throw new IllegalArgumentException("보통 프레임은 투구수가 2를 넘을 수 없습니다.");
        }
    }

}

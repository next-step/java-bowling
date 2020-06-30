package bowling.domain.frame;

import bowling.domain.state.State;
import bowling.domain.state.StateCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {

    private static final String SEPARATOR = "|";

    private List<State> states;
    private int pitchCount;
    private int framePoint;

    private FinalFrame(List<State> states, int framePoint, int pitchCount) {
        validateScores(states);
        validatePitchCount(pitchCount);
        this.states = states;
        this.pitchCount = pitchCount;
        this.framePoint = framePoint;
    }

    public static Frame create() {
        List<State> states = new ArrayList<>();
        return new FinalFrame(states, 0, 0);
    }

    public static Frame create(List<State> states, int framePoint, int pitchCount) {
        return new FinalFrame(states, framePoint, pitchCount);
    }

    @Override
    public Frame bowl(Point point) {
        if (pitchCount == 0) {
            return firstBowl(point);
        }
        return nextBowl(point);
    }

    private Frame firstBowl(Point point) {
        State state = StateCreator.create(point);
        states.add(state);

        if (state.isStrike()) {
            int strikePoint = point.getPoint();
            return FinalFrame.create(states, strikePoint, ++pitchCount);
        }

        return FinalFrame.create(states, this.framePoint + point.getPoint(), ++pitchCount);
    }

    private Frame nextBowl(Point point) {
        validateNextBowl();
        State state = states.get(pitchCount - 1);
        State nextState = state.nextScore(point);
        states.add(nextState);

        if (nextState.isSpare()) {
            int sparePoint = framePoint + point.getPoint();
            return FinalFrame.create(states, sparePoint, ++pitchCount);
        }

        if (state.isStrike()) {
            int strikePoint = framePoint + point.getPoint();
            return FinalFrame.create(states, strikePoint, ++pitchCount);
        }

        return FinalFrame.create(states, framePoint + point.getPoint(), FINAL_MAX_BOWL_PITCH);
    }

    @Override
    public String getStates() {
        return states.stream()
                .map(State::getScore)
                .collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public int getFramePoint() {
        if (pitchCount == FINAL_MAX_BOWL_PITCH) {
            return this.framePoint;
        }
        return 0;
    }

    public boolean isLastPitch() {
        return pitchCount == FINAL_MAX_BOWL_PITCH;
    }

    private void validateNextBowl() {
        if (states.size() == 0) {
            throw new IllegalArgumentException("첫 투구를 하지 않았습니다.");
        }
    }

    private void validatePitchCount(int pitchCount) {
        if (pitchCount > FINAL_MAX_BOWL_PITCH) {
            throw new IllegalArgumentException("보통 프레임은 투구수가 3를 넘을 수 없습니다.");
        }
    }

    private void validateScores(List<State> states) {
        if (states == null) {
            this.states = new ArrayList<>();
        }
    }
}

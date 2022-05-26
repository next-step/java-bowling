package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.state.BeforeProgress;
import bowling.domain.state.FrameState;
import bowling.domain.state.Miss;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrame implements Frame {
    private static final String DELIMITER = "|";
    private static final int MAX_BOWL = 3;
    private static final int MIN_BOWL = 2;
    private static final int ZERO = 0;

    private final LinkedList<FrameState> frameStates;
    private int bowlCount;

    public FinalFrame() {
        this.frameStates = new LinkedList<>(List.of(new BeforeProgress()));
        this.bowlCount = 0;
    }

    FinalFrame(LinkedList<FrameState> frameStates, int bowlCount) {
        validateFrameStatesAndBowlCount(frameStates, bowlCount);
        this.frameStates = frameStates;
        this.bowlCount = bowlCount;
    }

    private void validateFrameStatesAndBowlCount(LinkedList<FrameState> frameStates, int bowlCount) {
        if (frameStates == null) {
            throw new IllegalArgumentException("프레임 상태 리스트는 null 일 수 없습니다.");
        }
        if (bowlCount < ZERO || bowlCount > MAX_BOWL) {
            throw new IllegalArgumentException(String.format("마지막 프레임의 투구 횟수는 0 ~ 3을 벗어날 수 없습니다. 전달받은 투구 횟수 : %d", bowlCount));
        }
    }

    public static FinalFrame initialize() {
        return new FinalFrame();
    }

    boolean isEqualFrameStates(LinkedList<FrameState> frameStates) {
        return this.frameStates.equals(frameStates);
    }

    boolean isMatchBowlCount(int bowlCount) {
        return this.bowlCount == bowlCount;
    }

    @Override
    public Frame bowl(Pins hitPins) {
        this.bowlCount++;
        FrameState lastFrameState = frameStates.getLast();
        if (lastFrameState.isFrameEnd() && !isMiss(lastFrameState)) {
            frameStates.add(new BeforeProgress().bowl(hitPins));
            return this;
        }
        frameStates.removeLast();
        frameStates.add(lastFrameState.bowl(hitPins));
        return this;
    }

    @Override
    public boolean isFrameEnd() {
        if (this.bowlCount == MAX_BOWL) {
            return true;
        }
        return this.bowlCount == MIN_BOWL && isMiss(frameStates.getLast());
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public String symbol() {
        return frameStates.stream().map(FrameState::symbol).collect(Collectors.joining(DELIMITER));
    }

    private boolean isMiss(FrameState frameState) {
        return frameState instanceof Miss;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FinalFrame that = (FinalFrame) o;

        if (bowlCount != that.bowlCount) return false;
        return frameStates.equals(that.frameStates);
    }

    @Override
    public int hashCode() {
        int result = frameStates.hashCode();
        result = 31 * result + bowlCount;
        return result;
    }
}
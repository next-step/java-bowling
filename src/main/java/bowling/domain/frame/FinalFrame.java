package bowling.domain.frame;

import bowling.domain.Pins;
import bowling.domain.Score;
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
    public void bowl(Pins hitPins) {
        this.bowlCount++;
        FrameState lastFrameState = frameStates.getLast();
        if (lastFrameState.isEnd() && !isMiss(lastFrameState)) {
            frameStates.add(new BeforeProgress().bowl(hitPins));
            return;
        }
        frameStates.removeLast();
        frameStates.add(lastFrameState.bowl(hitPins));
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
    public Score score() {
        Score score = getFirstScore();

        for (int stateIndex = 1; stateIndex < frameStates.size(); stateIndex++) {
            FrameState frameState = frameStates.get(stateIndex);
            score = frameState.calculateAdditionalScore(score);
        }
        return score;
    }

    @Override
    public Score calculateAdditionalScore(Score previousScore) {
        try {
            return calculateAdditionalScore(previousScore, 0);
        } catch (IndexOutOfBoundsException exception) {
            return Score.init();
        }
    }

    private Score calculateAdditionalScore(Score previousScore, int frameStateIndex) {
        Score score = frameStates.get(frameStateIndex).calculateAdditionalScore(previousScore);
        if (score.isNoBonusChance()) {
            return score;
        }
        return calculateAdditionalScore(score, frameStateIndex + 1);
    }

    private Score getFirstScore() {
        return frameStates.getFirst().score();
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
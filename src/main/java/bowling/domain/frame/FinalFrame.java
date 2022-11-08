package bowling.domain.frame;

import bowling.domain.pin.FallenPin;
import bowling.domain.score.Score;
import bowling.domain.state.FrameState;
import bowling.domain.state.Ready;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {
    private static final int BONUS_INCLUDED_TOTAL_TRIES = 3;

    private final List<FrameState> frameStates;

    FinalFrame(List<FrameState> frameStates) {
        this.frameStates = frameStates;
    }

    public static FinalFrame init() {
        List<FrameState> result = new ArrayList<>();
        result.add(new Ready());
        return new FinalFrame(result);
    }

    @Override
    public Frame bowl(FallenPin fallenPin) {
        if (lastState().isFinished()) {
            frameStates.add(new Ready().bowl(fallenPin));
            return this;
        }

        frameStates.set(lastIndex(), lastState().bowl(fallenPin));
        return this;
    }

    @Override
    public boolean isFinished() {
        if (firstState().isStrike() || firstState().isSpare()) {
            return totalTries() == BONUS_INCLUDED_TOTAL_TRIES;
        }

        return firstState().isFinished();
    }

    @Override
    public List<FrameState> getStates() {
        return frameStates;
    }

    @Override
    public boolean isReady() {
        return lastState().isReady();
    }

    @Override
    public Score getScore() {
        Score score = firstState().getScore();
        if (score.canCalculate()) {
            return score;
        }

        return addScoreAfterFirstState(score);
    }

    @Override
    public Score addScore(Score previousScore) {
        Score score = firstState().addScore(previousScore);
        if (score.canCalculate()) {
            return score;
        }

        return addScoreAfterFirstState(score);
    }

    private Score addScoreAfterFirstState(Score previousScore) {
        if (previousScore.canCalculate()) {
            return previousScore;
        }

        Score score = previousScore;
        for (int i = 1; i < frameStates.size(); i++) {
            score = frameStates.get(i).addScore(score);
            if (score.canCalculate()) {
                return score;
            }
        }

        return null;
    }

    private int totalTries() {
        return frameStates.stream()
                .mapToInt(FrameState::tries)
                .sum();
    }

    private FrameState firstState() {
        return frameStates.get(0);
    }

    private FrameState lastState() {
        return frameStates.get(lastIndex());
    }

    private int lastIndex() {
        return frameStates.size() - 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinalFrame)) return false;

        FinalFrame that = (FinalFrame) o;

        return Objects.equals(frameStates, that.frameStates);
    }

    @Override
    public int hashCode() {
        return frameStates != null ? frameStates.hashCode() : 0;
    }
}

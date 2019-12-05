package bowling.domain.set;

import bowling.domain.History;
import bowling.domain.score.Score;
import bowling.domain.state.LastState;
import bowling.domain.state.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static bowling.domain.FrameConstants.*;

public class BaseFrameSet implements FrameSet {

    private final int playCount;
    private State state;
    private final History stateHistory;
    private FrameSet nextFrameSet;

    private BaseFrameSet(int playCount, State state, History stateHistory) {
        this(playCount, state, stateHistory, null);
    }

    private BaseFrameSet(int playCount, State state, History stateHistory, FrameSet nextFrameSet) {
        assertPlayCount(playCount);

        this.playCount = playCount;
        this.state = state;
        this.stateHistory = stateHistory;
    }

    @Override
    public void play(int hitCount) {
        assertHitCount(hitCount);
        state = state.play(hitCount);
        stateHistory.add(state);
    }

    @Override
    public FrameSet getNext() {
        return this;
    }

    @Override
    public boolean isEndedFrame() {
        return state.isEnd();
    }

    @Override
    public boolean isEndedGame() {
        return false;
    }

    @Override
    public FrameSet snapShot() {
        return new BaseFrameSet(playCount, state, stateHistory, nextFrameSet);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public int getPlayCount() {
        return playCount;
    }

    @Override
    public int getTotalScore() {
        if (!isEndedFrame()) {
            return NOT_CALCULATED_SCORE;
        }

        Score score = ((LastState) state).createScore();

        if (score.isEnd()) {
            return score.getPoint();
        }

        if (nextFrameSet == null) {
            return NOT_CALCULATED_SCORE;
        }

        return nextFrameSet.calculateAdditionalScore(score);
    }

    void setNextFrameSet(FrameSet nextFrameSet) {
        this.nextFrameSet = nextFrameSet;
    }

    @Override
    public int calculateAdditionalScore(Score score){
        for (State state : stateHistory.getValue()) {
            score.addBonusPoint(state.getHitCount());

            if (score.isEnd()) {
                return score.getPoint();
            }
        }

        if (nextFrameSet == null) {
            return NOT_CALCULATED_SCORE;
        }

        return nextFrameSet.calculateAdditionalScore(score);
    }

    @Override
    public History getHistory() {
        return stateHistory;
    }

    public static BaseFrameSet create(int playCount, State state) {
        return new BaseFrameSet(playCount, state, new History());
    }

    private void assertPlayCount(int playCount) {
        if (playCount < MIN_PLAY_COUNT || playCount > BonusFrameSet.PLAY_COUNT) {
            throw new IllegalArgumentException("게임 세트 횟수가 올바르지 않습니다.");
        }
    }

    private void assertHitCount(int hitCount) {
        if (hitCount < MIN_HIT_COUNT || hitCount > MAX_HIT_COUNT) {
            throw new IllegalArgumentException("넘어트린 핀 갯수가 올바르지 않습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseFrameSet that = (BaseFrameSet) o;
        return playCount == that.playCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playCount);
    }
}

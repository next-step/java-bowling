package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractBowlingGameFrame implements BowlingGameFrame {

    protected final List<Integer> hits;
    protected final List<BowlingGameHitState> states;
    protected final BowlingGameFrame nextFrame;

    protected AbstractBowlingGameFrame(BowlingGameFrame nextFrame) {
        this.hits = new ArrayList<>();
        this.states = new ArrayList<>();
        this.nextFrame = nextFrame;
    }

    @Override
    public void add(int hit) {
        validateState();
        validateHitIsNegative(hit);
        validateHitIsUnderRemainedPins(hit);
        hits.add(hit);
        states.add(BowlingGameHitState.from(hits));
    }

    private void validateState() {
        if (isEnded()) {
            throw new IllegalStateException("프레임이 종료되어 더 이상 투구 할 수 없습니다.");
        }
    }

    private void validateHitIsNegative(int hit) {
        if (hit < BowlingGameFrame.MIN_NUMBER_OF_BOWLING_PINS) {
            throw new IllegalArgumentException(String.format("투구는 %d 보다 작을 수 없습니다.", BowlingGameFrame.MIN_NUMBER_OF_BOWLING_PINS));
        }
    }

    private void validateHitIsUnderRemainedPins(int hit) {
        int remainedPins = getRemainedPins();
        if (hit > remainedPins) {
            throw new IllegalArgumentException(String.format("투구는 남은 핀의 개수(%d) 보다 클 수 없습니다.", remainedPins));
        }
    }

    @Override
    public int countHits() {
        return hits.size();
    }

    @Override
    public int getHit(int index) {
        return hits.get(index);
    }

    @Override
    public BowlingGameHitState getState(int index) {
        return states.get(index);
    }

    @Override
    public int getRemainedPins() {
        return BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS - sumOfHits() % BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS;
    }

    private int sumOfHits() {
        return hits.stream()
                .reduce(0, Integer::sum);
    }

    @Override
    abstract public boolean isEnded();

    @Override
    public BowlingGameFrame getNextFrame() {
        return nextFrame;
    }

    @Override
    abstract public int getScore();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBowlingGameFrame that = (AbstractBowlingGameFrame) o;
        return Objects.equals(hits, that.hits) && Objects.equals(states, that.states);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hits, states);
    }

    @Override
    public String toString() {
        return "AbstractBowlingGameFrame{" +
                "hits=" + hits +
                ", states=" + states +
                '}';
    }

}

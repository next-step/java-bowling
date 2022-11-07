package bowling.domain;

import java.util.List;
import java.util.Objects;

public abstract class AbstractBowlingGameFrame implements BowlingGameFrame {

    protected final List<Integer> hits;

    protected AbstractBowlingGameFrame(List<Integer> hits) {
        validateSizeOfHits(hits);
        validateHitsContainsNegative(hits);
        validateHits(hits);
        this.hits = hits;
    }

    private void validateSizeOfHits(List<Integer> hits) {
        if (hits.size() > getMaxSizeOfHits()) {
            throw new IllegalArgumentException(String.format("투구 기록은 최대 %d회 까지 저장할 수 있습니다.", getMaxSizeOfHits()));
        }
    }

    public abstract int getMaxSizeOfHits();

    private void validateHitsContainsNegative(List<Integer> hits) {
        hits.forEach(this::validateHitIsNegative);
    }

    protected abstract void validateHits(List<Integer> hits);

    @Override
    public void add(int hit) {
        validateState();
        validateHitIsNegative(hit);
        validateHitIsUnderRemainedPins(hit);
        hits.add(hit);
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
        if (hits.isEmpty()) {
            return;
        }

        int remainedPins = getRemainedPins();
        if (hit > remainedPins) {
            throw new IllegalArgumentException(String.format("투구는 남은 핀의 개수(%d) 보다 클 수 없습니다.", remainedPins));
        }
    }

    @Override
    public int size() {
        return hits.size();
    }

    @Override
    public int get(int index) {
        return hits.get(index);
    }

    @Override
    public boolean isStrike() {
        return hits.size() == 1
                && hits.get(0) == BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS;
    }

    @Override
    public boolean isSpare() {
        return hits.size() == 2
                && sumOfRemainedPins() == BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS;
    }

    @Override
    public boolean isMiss() {
        return hits.size() == 2
                && sumOfRemainedPins() < BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS;
    }

    @Override
    public int getRemainedPins() {
        return BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS - sumOfRemainedPins() % BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PINS;
    }

    private int sumOfRemainedPins() {
        return hits.stream()
                .reduce(0, Integer::sum);
    }

    @Override
    abstract public boolean isEnded();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBowlingGameFrame that = (AbstractBowlingGameFrame) o;
        return Objects.equals(hits, that.hits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hits);
    }

    @Override
    public String toString() {
        return "AbstractHitHistory{" +
                "hits=" + hits +
                '}';
    }

}

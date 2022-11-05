package bowling.domain;

import java.util.List;
import java.util.Objects;

public abstract class AbstractHitHistory implements HitHistory {

    protected final List<Integer> hits;

    protected AbstractHitHistory(List<Integer> hits) {
        validateSizeOfHits(hits);
        validateHitsContainsNegative(hits);
        validateHits(hits);
        this.hits = hits;
    }

    private void validateSizeOfHits(List<Integer> hits) {
        if (hits.size() > getMaxSizeOfHitHistory()) {
            throw new IllegalArgumentException(String.format("투구 기록은 최대 %d회 까지 저장할 수 있습니다.", getMaxSizeOfHitHistory()));
        }
    }

    private void validateHitsContainsNegative(List<Integer> hits) {
        hits.forEach(this::validateHitIsNegative);
    }

    protected abstract void validateHits(List<Integer> hits);

    @Override
    public void add(int hit) {
        validateSizeOfHits();
        validateHitIsNegative(hit);
        validateHitIsUnderRemainedPins(hit);
        validateState();
        hits.add(hit);
    }

    private void validateSizeOfHits() {
        if (hits.size() == getMaxSizeOfHitHistory()) {
            throw new IllegalStateException(String.format("투구 기록은 최대 %d회 까지 저장할 수 있습니다.", getMaxSizeOfHitHistory()));
        }
    }

    public abstract int getMaxSizeOfHitHistory();

    private void validateHitIsNegative(int hit) {
        if (hit < BowlingGameFrame.MIN_NUMBER_OF_BOWLING_PIN) {
            throw new IllegalArgumentException(String.format("투구는 %d 보다 작을 수 없습니다.", BowlingGameFrame.MIN_NUMBER_OF_BOWLING_PIN));
        }
    }

    private void validateHitIsUnderRemainedPins(int hit) {
        if (hits.isEmpty()) {
            return;
        }

        int remainedPins = BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PIN - sumOfRemainedPins() % BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PIN;
        if (hit > remainedPins) {
            throw new IllegalArgumentException(String.format("투구는 남은 핀의 개수(%d) 보다 클 수 없습니다.", remainedPins));
        }
    }

    protected abstract void validateState();

    @Override
    public boolean isStrike() {
        return hits.size() == 1
                && hits.get(0) == BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PIN;
    }

    @Override
    public boolean isSpare() {
        return hits.size() == 2
                && sumOfRemainedPins() == BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PIN;
    }

    @Override
    public boolean isMiss() {
        return hits.size() == 2
                && sumOfRemainedPins() < BowlingGameFrame.MAX_NUMBER_OF_BOWLING_PIN;
    }

    private int sumOfRemainedPins() {
        return hits.stream()
                .reduce(0, Integer::sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractHitHistory that = (AbstractHitHistory) o;
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

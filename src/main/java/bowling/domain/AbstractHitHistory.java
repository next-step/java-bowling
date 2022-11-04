package bowling.domain;

import java.util.List;
import java.util.Objects;

public abstract class AbstractHitHistory implements HitHistory {

    protected final List<Integer> hits;

    protected AbstractHitHistory(List<Integer> hits) {
        validateHits(hits);
        this.hits = hits;
    }

    protected abstract void validateHits(List<Integer> hits);

    protected void validateHitIsNegative(int hit) {
        if (hit < BowlingGameFrame.MIN_NUMBER_OF_BOWLING_PIN) {
            throw new IllegalArgumentException(String.format("투구는 %d 보다 작을 수 없습니다.", BowlingGameFrame.MIN_NUMBER_OF_BOWLING_PIN));
        }
    }

    @Override
    public void add(int hit) {
        validateHit(hit);
        hits.add(hit);
    }

    protected abstract void validateHit(int hit);

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

package bowling.domain.frame;

import bowling.domain.Hit;
import bowling.domain.state.HitState;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFrame implements Frame {

    protected final List<Hit> hits;
    protected final Frame nextFrame;

    protected AbstractFrame(Frame nextFrame) {
        this.hits = new ArrayList<>();
        this.nextFrame = nextFrame;
    }

    @Override
    public void add(int hitValue) {
        validateState();
        hits.add(new Hit(hitValue, getPreviousHit()));
    }

    private Hit getPreviousHit() {
        if (hits.isEmpty()) {
            return null;
        }

        return hits.get(hits.size() - 1);
    }

    private void validateState() {
        if (isEnded()) {
            throw new IllegalStateException("프레임이 종료되어 더 이상 투구 할 수 없습니다.");
        }
    }

    @Override
    public int countHits() {
        return hits.size();
    }

    @Override
    public int getHitValue(int index) {
        return hits.get(index)
                .getValue();
    }

    @Override
    public HitState getState(int index) {
        Hit hit = hits.get(index);
        return hit.getState();
    }

    protected int sumOfHits() {
        return hits.stream()
                .map(Hit::getValue)
                .reduce(0, Integer::sum);
    }

    @Override
    abstract public boolean isEnded();

    @Override
    public Frame getNextFrame() {
        return nextFrame;
    }

    @Override
    abstract public int getScore();

    protected HitState getLastState() {
        return getState(hits.size() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractFrame that = (AbstractFrame) o;
        return Objects.equals(hits, that.hits) && Objects.equals(nextFrame, that.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hits, nextFrame);
    }

}

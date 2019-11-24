package bowling.domain.state;

public class Miss implements State {

    private final int hitCount;

    public Miss(int hitCount) {
        this.hitCount = hitCount;
    }

    @Override
    public State play(int newHitCount) {
        return this;
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State snapShot() {
        return new Miss(hitCount);
    }

    @Override
    public String getString() {
        return String.valueOf(hitCount);
    }
}
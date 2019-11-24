package bowling.domain.state;

public class Hit implements State {

    private final int hitCount;

    public Hit(int hitCount) {
        this.hitCount = hitCount;
    }

    @Override
    public State play(int newHitCount) {
        if (newHitCount == 0) {
            return new SecondGutter();
        }

        if (hitCount + newHitCount == INIT_PIT_COUNT) {
            return new Spare();
        }

        return new Miss(newHitCount);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State snapShot() {
        return new Hit(hitCount);
    }

    @Override
    public String getString() {
        return String.valueOf(hitCount);
    }
}

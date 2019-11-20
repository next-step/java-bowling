package bowling.domain.state;

public class Hit extends State {

    public Hit(int hitCount) {
        this.playCount = 1;
        this.hitCount = hitCount;
    }

    @Override
    public State play(int newHitCount) {
        if (newHitCount == 0) {
            return new Gutter();
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
}

package bowling.domain.state;

public class Ready extends State {

    public Ready() {
        this.playCount = 0;
        this.hitCount = 0;
    }

    @Override
    public State play(int newHitCount) {
        if (newHitCount == INIT_PIT_COUNT) {
            return new Strike();
        }

        if (newHitCount == 0) {
            return new Gutter();
        }

        return new Hit(newHitCount);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State snapShot() {
        return new Ready();
    }
}

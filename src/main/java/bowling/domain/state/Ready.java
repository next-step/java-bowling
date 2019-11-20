package bowling.domain.state;

public class Ready extends State {

    public Ready() {
        this.playCount = 0;
        this.remainPinCount = INIT_PIT_COUNT;
    }

    @Override
    public State play(int hitCount) {
        remainPinCount -= hitCount;

        if (hitCount == INIT_PIT_COUNT) {
            return new Strike();
        }

        if (hitCount == 0) {
            return new Gutter();
        }

        return new Hit(remainPinCount);
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

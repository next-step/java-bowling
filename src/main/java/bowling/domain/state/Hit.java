package bowling.domain.state;

public class Hit extends State {

    public Hit(int remainPinCount) {
        this.playCount = 1;
        this.remainPinCount = remainPinCount;
    }

    @Override
    public State play(int hitCount) {
        remainPinCount -= hitCount;

        if (hitCount == 0) {
            return new Gutter();
        }

        if (remainPinCount == 0) {
            return new Spare();
        }

        return new Miss(remainPinCount);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State snapShot() {
        return new Hit(remainPinCount);
    }
}

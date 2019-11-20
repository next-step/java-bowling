package bowling.domain.state;

public class Miss extends State {

    public Miss(int remainPinCount) {
        this.playCount = 2;
        this.remainPinCount = remainPinCount;
    }

    @Override
    public State play(int hitCount) {
        remainPinCount -= hitCount;
        return this;
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State snapShot() {
        return new Miss(remainPinCount);
    }
}

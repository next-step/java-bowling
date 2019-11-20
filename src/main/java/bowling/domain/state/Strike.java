package bowling.domain.state;

public class Strike extends State {

    public Strike() {
        this.playCount = 1;
        this.remainPinCount = 0;
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
        return new Strike();
    }
}

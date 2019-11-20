package bowling.domain.state;

public class Spare extends State {

    public Spare() {
        this.playCount = 2;
        this.remainPinCount = 0;
    }

    @Override
    public State play(int hitCount) {
        return this;
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}

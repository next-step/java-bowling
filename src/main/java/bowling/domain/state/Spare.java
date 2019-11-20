package bowling.domain.state;

public class Spare extends State {

    public Spare() {
        this.playCount = 2;
        this.hitCount = INIT_PIT_COUNT;
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
        return new Spare();
    }
}

package bowling.domain.state;

public class Strike extends State {

    public Strike() {
        this.playCount = 1;
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
        return new Strike();
    }
}

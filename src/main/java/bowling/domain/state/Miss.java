package bowling.domain.state;

public class Miss extends State {

    public Miss(int hitCount) {
        this.playCount = 2;
        this.hitCount = hitCount;
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
        return new Miss(hitCount);
    }
}

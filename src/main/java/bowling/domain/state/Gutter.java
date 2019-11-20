package bowling.domain.state;

public class Gutter extends State {

    public Gutter() {
        this(1);
    }

    public Gutter(int playCount) {
        this.playCount = playCount;
        this.hitCount = 0;
    }

    @Override
    public State play(int newHitCount) {
        if (newHitCount == 0) {
            return new Gutter(2);
        }

        if (newHitCount == INIT_PIT_COUNT) {
            return new Spare();
        }

        return new Miss(newHitCount);
    }

    @Override
    public boolean isEnd() {
        return playCount == 2;
    }

    @Override
    public State snapShot() {
        return new Gutter(playCount);
    }
}

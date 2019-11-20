package bowling.domain.state;

public class Gutter extends State {

    public Gutter() {
        this(1);
    }

    public Gutter(int playCount) {
        this.playCount = playCount;
        this.remainPinCount = INIT_PIT_COUNT;
    }

    @Override
    public State play(int hitCount) {
        if (hitCount == 0) {
            return new Gutter(2);
        }

        if (hitCount == INIT_PIT_COUNT) {
            return new Spare();
        }

        return new Miss(hitCount);
    }

    @Override
    public boolean isEnd() {
        return playCount == 2;
    }
}

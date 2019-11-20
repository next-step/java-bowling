package bowling.domain.state;

public class Gutter extends State {

    public Gutter() {
        this(1);
    }

    public Gutter(int playCount) {
        this.playCount = playCount;
        this.remainPinCount = 10;
    }

    @Override
    public State play(int hitCount) {
        if (hitCount == 0) {
            return new Gutter(2);
        }

        if (hitCount == 10) {
            return new Spare();
        }

        return new Miss(hitCount);
    }

    @Override
    public boolean isEnd() {
        return playCount == 2;
    }
}

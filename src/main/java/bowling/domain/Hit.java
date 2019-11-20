package bowling.domain;

public class Hit extends State {

    public Hit(int remainPinCount) {
        this.remainPinCount = remainPinCount;
    }

    @Override
    public State play(int hitCount) {
        if (hitCount == 0) {
            return new Gutter();
        }

        if (remainPinCount - hitCount == 0) {
            return new Spare();
        }

        return new Miss(hitCount);
    }
}

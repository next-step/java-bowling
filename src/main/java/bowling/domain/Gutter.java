package bowling.domain;

public class Gutter extends State {

    public Gutter() {
        this.remainPinCount = 10;
    }

    @Override
    public State play(int hitCount) {
        if (hitCount == 0) {
            return new Gutter();
        }

        if (hitCount == 10) {
            return new Spare();
        }

        return new Miss(hitCount);
    }
}

package bowling.domain;

public class Ready extends State {

    public Ready() {
        remainPinCount = 10;
    }

    @Override
    public State play(int hitCount) {
        if (hitCount == 10) {
            return new Strike();
        }

        if (hitCount == 0) {
            return new Gutter();
        }

        return new Hit(remainPinCount);
    }
}

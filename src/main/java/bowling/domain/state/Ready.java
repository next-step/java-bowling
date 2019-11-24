package bowling.domain.state;

public class Ready implements State {

    @Override
    public State play(int newHitCount) {
        if (newHitCount == INIT_PIT_COUNT) {
            return new Strike();
        }

        if (newHitCount == 0) {
            return new FirstGutter();
        }

        return new Hit(newHitCount);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State snapShot() {
        return new Ready();
    }

    @Override
    public String getString() {
        return "";
    }
}

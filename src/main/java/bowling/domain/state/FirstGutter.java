package bowling.domain.state;

public class FirstGutter implements State {

    @Override
    public State play(int newHitCount) {
        if (newHitCount == 0) {
            return new SecondGutter();
        }

        if (newHitCount == INIT_PIT_COUNT) {
            return new Spare();
        }

        return new Miss(newHitCount);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State snapShot() {
        return new FirstGutter();
    }

    @Override
    public boolean isBonusPlayableState() {
        return false;
    }

    @Override
    public String getString() {
        return "-";
    }
}

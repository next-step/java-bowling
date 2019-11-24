package bowling.domain.state;

public class Spare implements State {

    public static final String TEXT = "/";

    @Override
    public State play(int newHitCount) {
        throw new IllegalStateException("Spare 이므로 해당 세트에서 더이상 진행할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State snapShot() {
        return new Spare();
    }

    @Override
    public boolean isBonusPlayableState() {
        return true;
    }

    @Override
    public String getString() {
        return Spare.TEXT;
    }
}

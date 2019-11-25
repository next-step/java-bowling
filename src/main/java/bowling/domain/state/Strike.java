package bowling.domain.state;

public class Strike implements State {

    public static final String TEXT = "X";

    @Override
    public State play(int newHitCount) {
        throw new IllegalStateException("Strike 이므로 해당 세트에서 더이상 진행할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State snapShot() {
        return new Strike();
    }

    @Override
    public boolean isBonusPlayableState() {
        return true;
    }

    @Override
    public String getString() {
        return Strike.TEXT;
    }
}

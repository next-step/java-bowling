package bowling.domain.state;

public class Strike implements State {

    @Override
    public State play(int newHitCount) {
        throw new IllegalStateException("Strike 에서 더이상 진행할 수 없습니다.");
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
        return "X";
    }
}

package bowling.domain.state;

public class SecondGutter implements State {
    @Override
    public State play(int newHitCount) {
        throw new IllegalStateException("Second Gutter 에서 더이상 진행할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State snapShot() {
        return new SecondGutter();
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

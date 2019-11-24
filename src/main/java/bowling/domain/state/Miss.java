package bowling.domain.state;

public class Miss implements State {

    private final int hitCount;

    public Miss(int hitCount) {
        this.hitCount = hitCount;
    }

    @Override
    public State play(int newHitCount) {
        throw new IllegalStateException("Miss 이므로 해당 세트에서 더이상 진행할 수 없습니다.");
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State snapShot() {
        return new Miss(hitCount);
    }

    @Override
    public boolean isBonusPlayableState() {
        return false;
    }

    @Override
    public String getString() {
        return String.valueOf(hitCount);
    }
}
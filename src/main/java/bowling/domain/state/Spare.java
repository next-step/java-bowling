package bowling.domain.state;

public class Spare implements State {

    @Override
    public State play(int newHitCount) {
        throw new IllegalStateException("Spare 에서 더이상 진행할 수 없습니다.");
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
    public String getString() {
        return "/";
    }
}

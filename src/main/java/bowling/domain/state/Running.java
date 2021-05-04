package bowling.domain.state;

public abstract class Running implements State {

    @Override
    public final boolean isFinish() {
        return false;
    }

    @Override
    public final boolean isAllPinClear() {
        return false;
    }

}

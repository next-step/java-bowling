package bowling.domain.state;

public final class TestFinish extends Finish {

    @Override
    public boolean isAllPinClear() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int firstCount() {
        return 0;
    }

    @Override
    public int secondCount() {
        return 0;
    }
}

package bowling.domain.status;

public class Strike extends Finished {
    @Override
    public String printResult() {
        return "X";
    }

    @Override
    public boolean isClearAllPins() {
        return true;
    }

    @Override
    public boolean canRemovePendingStatue() {
        return false;
    }
}

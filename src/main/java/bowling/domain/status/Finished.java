package bowling.domain.status;

public abstract class Finished implements Status {
    @Override
    public boolean canPlayMore() {
        return false;
    }
}

package bowling.domain.status;

public abstract class Finished implements Status {
    @Override
    public boolean isEnd() {
        return true;
    }
}

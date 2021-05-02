package bowling.domain.status;

public abstract class Continue implements Status {
    @Override
    public boolean isEnd() {
        return false;
    }
}

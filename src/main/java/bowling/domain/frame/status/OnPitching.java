package bowling.domain.frame.status;

public abstract class OnPitching implements Status {

    @Override
    public boolean isEnd() {
        return false;
    }
}

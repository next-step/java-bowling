package bowling.domain.status;

public abstract class Finished implements Status {
    @Override
    public boolean canPlayMore() {
        return false;
    }

    @Override
    public Status next(int downPin) {
        throw new IllegalStateException("해당 프레임은 종료 되었습니다");
    }
}

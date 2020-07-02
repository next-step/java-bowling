package bowling.domain.status;

public abstract class Finished implements Status {
    @Override
    public boolean canPlayMore() {
        return false;
    }

    @Override
    public Status bowl(int downPin) {
        throw new IllegalStateException("투구 가능한 상태가 아닙니다");
    }
}

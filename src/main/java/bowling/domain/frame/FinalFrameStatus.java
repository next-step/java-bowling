package bowling.domain.frame;

import java.util.Objects;

public class FinalFrameStatus {

    private final boolean isThirdAvailable;

    private final boolean isDone;

    private FinalFrameStatus(boolean isThirdAvailable, boolean isDone) {
        this.isThirdAvailable = isThirdAvailable;
        this.isDone = isDone;
    }

    public static FinalFrameStatus of(boolean isThirdAvailable, boolean isDone) {
        validate(isThirdAvailable, isDone);
        return new FinalFrameStatus(isThirdAvailable, isDone);
    }

    private static void validate(boolean isThirdAvailable, boolean isDone) {
        if (isThirdAvailable && isDone) {
            throw new IllegalStateException("아직 시도가 남았기 때문에 끝낼 수 없습니다.");
        }
    }

    public boolean isThirdAvailable() {
        return isThirdAvailable;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrameStatus that = (FinalFrameStatus) o;
        return isThirdAvailable == that.isThirdAvailable && isDone == that.isDone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isThirdAvailable, isDone);
    }
}

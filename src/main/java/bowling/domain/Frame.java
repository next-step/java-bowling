package bowling.domain;

public interface Frame {
    void bowl(PinCount fallenPinCount);
    boolean isFinished();
    Renderer toRenderer();

    default void validateFinishedFrame() {
        if (this.isFinished()) {
            throw new IllegalStateException("종료된 프레임입니다.");
        }
    }

    default void validateFollowUpPinCount(PinCount prev, PinCount post) {
        if (prev.isDefined() && !prev.isStrike() && post.over(prev.remainPinCount())) {
            throw new IllegalArgumentException(String.format("쓰러트린 핀의 수가 유효하지 않습니다. prev: %s, post: %s", prev, post));
        }
    }
}

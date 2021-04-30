package bowling.domain.state;

public interface Result extends State {

    default State bowl(BowlingPin bowlingPin) {
        throw new IllegalStateException("투구 가능한 상태가 아닙니다.");
    }

    default boolean isDone() {
        return true;
    }
}

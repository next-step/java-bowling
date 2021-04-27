package bowling.domain.engine.frame.state;

public class CannotInitializeScoreException extends RuntimeException {

    public CannotInitializeScoreException() {
        super("점수 산정이 불가능한 상태입니다.");
    }
}

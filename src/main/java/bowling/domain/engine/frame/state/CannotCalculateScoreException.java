package bowling.domain.engine.frame.state;

public class CannotCalculateScoreException extends RuntimeException {

    public CannotCalculateScoreException() {
        super("아직 점수를 계산할 수 없습니다.");
    }
}

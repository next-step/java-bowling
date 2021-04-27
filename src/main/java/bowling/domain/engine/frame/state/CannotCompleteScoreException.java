package bowling.domain.engine.frame.state;

public class CannotCompleteScoreException extends RuntimeException {

    public CannotCompleteScoreException() {
        super("점수 계산을 완료하기 위한 투구 수가 부족합니다.");
    }
}

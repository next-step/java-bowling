package domain;

public class UndoneCalculationException extends RuntimeException {

    UndoneCalculationException() {
        super("아직 계산이 끝나지 않았습니다.");
    }
}

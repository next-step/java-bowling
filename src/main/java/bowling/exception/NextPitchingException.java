package bowling.exception;

public class NextPitchingException extends IllegalArgumentException{

    public NextPitchingException(String message) {
        super(message + " 처리가 끝났으므로 투구를 할 수 없습니다.");
    }
}

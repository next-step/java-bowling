package bowling_step3.exception;

public class PitchOverBoundException extends UnsupportedOperationException{
    public PitchOverBoundException() {
        super("더이상 투구할 수 없습니다.");
    }
}

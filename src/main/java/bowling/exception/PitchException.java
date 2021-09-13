package bowling.exception;

public class PitchException extends RuntimeException {
    private static final String PITCH_EXCEPTION = "투구수(%s)를 잘못 입력 했습니다.";

    public PitchException(int pitch) {
        super(String.format(PITCH_EXCEPTION, pitch));
    }
}

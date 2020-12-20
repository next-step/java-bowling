package bowling.exception;

public class FrameSizeException extends IllegalArgumentException{

    public static final String FRAME_SIZE_EXCEPTION = "프레임 사이즈가 맞지 않습니다.";

    public FrameSizeException() {
        super(FRAME_SIZE_EXCEPTION);
    }
}

package step4.exception;

public class NeedAdditionalFrameException extends RuntimeException {
    private static final String NEED_ADDITIONAL_FRAME = "점수를 계산하기 위해서는 추가적은 프레임을 진행해야 합니다.";

    public NeedAdditionalFrameException() {
        super(NEED_ADDITIONAL_FRAME);
    }
}

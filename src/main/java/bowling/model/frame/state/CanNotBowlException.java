package bowling.model.frame.state;

class CanNotBowlException extends UnsupportedOperationException {

    private static final String ERROR_MESSAGE = "더 이상 볼을 던질 수 없습니다";

    CanNotBowlException() {
        super(ERROR_MESSAGE);
    }
}
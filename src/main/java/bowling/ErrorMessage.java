package bowling;

public enum ErrorMessage {
    SCORE_OUT_OF_RANGE("스코어는 0 ~ 10 사이 입니다."), FRAME_RESULT_NOT_EXIST("존재할 수 없는 프레임 결과 입니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

package step3.exceptions;

public class pinNumberException extends IllegalArgumentException {
    private static final String PIN_NUMBER_EXCEPTION = "한 프레임에 볼링핀을 10개 이상 쓰러뜨릴 수 없습니다.";

    public pinNumberException(String s) {
        super(PIN_NUMBER_EXCEPTION);
    }
}

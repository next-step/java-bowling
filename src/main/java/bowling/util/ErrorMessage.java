package bowling.util;

public class ErrorMessage {

    private static String CHECK_PIN_COUNT = "한 프레임에 넘어진 핀의 갯수는 최대 10개 입니다.";
    private static String CHECK_NAME_NULL_OR_EMPTY = "입력하신 이름을 확인해주세요";
    private static String CHECK_NAME_LENGTH = "이름의 길이는 3글자 입니다.";
    private static String CHECK_NULL = "해당 파라미터의 객체를 NULL이 될 수 없습니다.";

    private ErrorMessage(){

    }

    public static String getCheckPinCount() {
        return CHECK_PIN_COUNT;
    }

    public static String getCheckNameNullOrEmpty() {
        return CHECK_NAME_NULL_OR_EMPTY;
    }

    public static String getCheckNameLength() {
        return CHECK_NAME_LENGTH;
    }

    public static String getCheckNull() {
        return CHECK_NULL;
    }
}

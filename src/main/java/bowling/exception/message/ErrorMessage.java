package bowling.exception.message;

public class ErrorMessage {

    public static final String IS_NULL_OR_EMPTY = "입력 값이 null 이거나 빈 공백 문자입니다.";
    public static final String NULL_VALUE = "입력값이 null 입니다.";

    public static final String REQUIRED_FRAME_RANGE = "프레임 번호는 1~10 까지만 가능합니다.";

    public static final String NOT_ALLOW_FIRST_BOWL = "첫 번째 투구에 대한 볼링 핀 정보가 없습니다.";
    public static final String NOT_ALLOW_SECOND_BOWL = "두 번째 투구에 대한 볼링 핀 정보가 없습니다.";

    public static final String NOT_EXIST_STATE = "존재하지 않는 상태값입니다.";

    private ErrorMessage() {
    }
}

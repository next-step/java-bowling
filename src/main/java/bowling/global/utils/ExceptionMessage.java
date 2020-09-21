package bowling.global.utils;

public class ExceptionMessage {

    public static final String INVALID_PLAYER_NAME_IS_NULL = "플레이어 이름을 입력해주세요.";
    public static final String INVALID_PLAYER_NAME_LENGTH = "이름의 길이는 영어 3자 이하입니다.";
    public static final String INVALID_PITCH_BALL_IS_NULL = "투구 값을 입력해주세요.";
    public static final String INVALID_PITCH_RANGE = "투구 범위는 0 ~ 10 입니다.";
    public static final String INVALID_LARGE_THAN_REMAINING_PINS = "%d이(가) 넘는값을 입력할 수 없습니다.";

    private ExceptionMessage() {
    }

}

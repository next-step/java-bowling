package bowling.global.utils;

public class ExceptionMessage {

    public static final String INVALID_PLAYER_NAME_IS_NULL = "플레이어 이름을 입력해주세요.";
    public static final String INVALID_PLAYER_NAME_LENGTH = "이름의 길이는 영어 3자 이하입니다.";
    public static final String INVALID_PITCH_BALL_IS_NULL = "투구 값을 입력해주세요.";
    public static final String INVALID_PITCH_RANGE = "투구 범위는 0 ~ 10 입니다.";
    public static final String INVALID_SCORES_MAX_SIZE = "scores 최대 횟수는 3번 입니다.";
    public static final String INVALID_SCORES_SUMALL = "Score의 총 값은 10을 넘을 수 없습니다.";
    public static final String INVALID_NOMAL_FRAME_NUMBER = "각 프레임 번호는 1 ~ 10의 숫자만 허용합니다.";
    public static final String INVALID_FINAL_FRAME_NUMBER = "마지막 프레임 번호는 10만 허용합니다.";

    private ExceptionMessage() {
    }

}

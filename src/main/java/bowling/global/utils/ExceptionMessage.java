package bowling.global.utils;

public class ExceptionMessage {

    public static final String INVALID_PLAYER_NAME_IS_NULL = "플레이어 이름을 입력해주세요.";
    public static final String INVALID_PLAYER_NAME_LENGTH = "이름의 길이는 영어 3자 이하입니다.";
    public static final String INVALID_PITCH_BALL_IS_NULL = "투구 값을 입력해주세요.";
    public static final String INVALID_PITCH_RANGE = "투구 범위는 0 ~ 10 입니다.";
    public static final String INVALID_INPUT_PLAYER_NUMBER = "게임 참여 인원은 숫자만 입력 가능합니다.";
    public static final String INVALID_BOWLER = "볼링에 참여한 참가자 동일하지 않습니다.";
    public static final String INVALID_SCORE_RANGE = "스코어는 1회당 총 10 이내의 값만 허용합니다.";

    public static final String MESSAGE_GAME_OVER = "게임이 종료되었습니다.";

    private ExceptionMessage() {
    }

}

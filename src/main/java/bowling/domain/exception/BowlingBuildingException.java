package bowling.domain.exception;

public class BowlingBuildingException extends RuntimeException {
    public static final String INVALID_PLAYER_NAME = "참가 선수 이름은 3글자만 허용됩니다.";
    public static final String INVALID_BOWLING_PIN_INDEX = "사용할 수 있는 볼링 핀의 인덱스 번호는 0부터 9까지의 정수입니다.";
    public static final String INVALID_PITCH = "투구에서 맞춘 볼링 핀의 개수는 0부터 10까지의 정수만 허용됩니다.";
    public static final String INVALID_FRAME_RESULT = "프레임에서 주어지는 기본 투구(2회) 시 최대 10개까지 맞출 수 있습니다.";

    public BowlingBuildingException() {
        super();
    }

    public BowlingBuildingException(String message) {
        super(message);
    }
}

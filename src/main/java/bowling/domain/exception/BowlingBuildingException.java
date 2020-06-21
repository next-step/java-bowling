package bowling.domain.exception;

public class BowlingBuildingException extends RuntimeException {
    public static final String INVALID_PLAYER_NAME = "참가 선수 이름은 3글자만 허용됩니다.";
    public static final String INVALID_PITCH = "투구에서 맞춘 볼링 핀의 개수는 0부터 10까지의 정수만 허용됩니다.";
    public static final String INVALID_NORMAL_FRAME_RESULT
            = "1~9번 프레임에서 맞출 수 있는 볼링 핀의 개수는 0부터 10까지의 정수입니다.";

    public BowlingBuildingException() {
        super();
    }

    public BowlingBuildingException(String message) {
        super(message);
    }
}

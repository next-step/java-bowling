package bowling_step3.exception;

import static bowling.domain.Player.NAME_MAX_LENGTH;

public class PlayerExceedsMaxLengthException extends IllegalArgumentException {
    public PlayerExceedsMaxLengthException() {
        super(String.format("플레이어 이름은 최대 %d글자 입니다.", NAME_MAX_LENGTH));
    }
}

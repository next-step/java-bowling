package bowling_step3.exception;

public class PlayerNullPointException extends NullPointerException {
    public PlayerNullPointException() {
        super("플레이어를 입력해주세요.");
    }
}

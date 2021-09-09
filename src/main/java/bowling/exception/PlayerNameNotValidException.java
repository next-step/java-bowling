package bowling.exception;

import bowling.domain.player.Player;

public final class PlayerNameNotValidException extends IllegalArgumentException {

    private static final String MESSAGE = "플레이어 이름은 %d자로 입력해 주세요.";

    public PlayerNameNotValidException() {
        super(String.format(MESSAGE, Player.NAME_LENGTH));
    }
}
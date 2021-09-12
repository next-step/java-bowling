package bowling.exception;

import bowling.domain.Player;

public class InvalidPlayersException extends IllegalArgumentException {
    private static final String MESSAGE = "참가자 이름은 반드시 3자 이내 이어야 합니다 : ";

    public InvalidPlayersException(Player player) {
        super(MESSAGE + player.name());
    }
}
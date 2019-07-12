package bowling.model;

import static bowling.model.Player.MAX_LENGTH_OF_NAME;

class PlayerNameLengthException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "이름을 %d 이하로 작성해주세요.";

    PlayerNameLengthException() {
        super(String.format(ERROR_MESSAGE, MAX_LENGTH_OF_NAME));
    }
}
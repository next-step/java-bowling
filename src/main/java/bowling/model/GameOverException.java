package bowling.model;

class GameOverException extends IllegalStateException {

    private static final String ERROR_MESSAGE = "게임이 종료되었습니다";

    GameOverException() {
        super(ERROR_MESSAGE);
    }
}

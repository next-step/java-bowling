package bowling.exception;

public final class NotAllowedPlayerNameCharacterException extends PlayerNameValidationException {

    public static final String NOT_ALLOWED_PLAYER_NAME_CHARACTER = "사용자의 이름은 영문 대소문자만 입력할 수 있습니다. 입력된 이름: ";

    public NotAllowedPlayerNameCharacterException(String name) {
        super(NOT_ALLOWED_PLAYER_NAME_CHARACTER + name);
    }

}

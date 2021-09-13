package bowling.exception;

public class NotEnglishNameException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String NOT_ENGLISH = "플레이어 이름은 영어만 가능합니다 -> %s";

    public NotEnglishNameException(String name) {
        super(String.format(NOT_ENGLISH, name));
    }

}

package bowling.domain.player;

public class NameLettersException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "이름은 영문만 가능합니다.";

    public NameLettersException() {
        super(DEFAULT_MESSAGE);
    }
}

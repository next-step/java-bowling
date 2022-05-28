package bowling.domain.player.exception;

public class NameEngException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "이름은 영문으로 작성해주세요.";

    public NameEngException() {
        super(EXCEPTION_MESSAGE);
    }
}

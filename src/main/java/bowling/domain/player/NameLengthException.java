package bowling.domain.player;

public class NameLengthException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "이름의 길이는 3자만 가능합니다. 현재 이름 길이 : %d";
    public static final int ZERO = 0;

    public NameLengthException() {
        super(String.format(DEFAULT_MESSAGE, ZERO));
    }

    public NameLengthException(int length) {
        super(String.format(DEFAULT_MESSAGE, length));
    }
}

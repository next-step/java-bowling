package bowling.exception;

public class BadNameException extends RuntimeException {
    private BadNameException() {
        super("플레이어 이름은 3개의 영어 문자여야 합니다.");
    }

    public static BadNameException getInstance() {
        return SingletonHelper.instance;
    }

    private static class SingletonHelper {
        private static final BadNameException instance = new BadNameException();
    }
}

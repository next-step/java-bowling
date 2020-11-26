package bowling.exception;

public class BadCountOfPinsException extends RuntimeException {
    private BadCountOfPinsException() {
        super("한 프레임에서 쓰러진 개수는 0 이상 10 이하여야 합니다.");
    }

    public static BadCountOfPinsException getInstance() {
        return SingletonHelper.instance;
    }

    private static class SingletonHelper {
        private static final BadCountOfPinsException instance = new BadCountOfPinsException();
    }
}

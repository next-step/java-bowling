package bowling.exception;

public class CanNotAddRollException extends RuntimeException {
    private CanNotAddRollException() {
        super("이미 끝난 프레임에서는 볼링을 굴릴 수 없습니다.");
    }

    public static CanNotAddRollException getInstance() {
        return SingletonHelper.instance;
    }

    private static class SingletonHelper {
        private static final CanNotAddRollException instance = new CanNotAddRollException();
    }
}

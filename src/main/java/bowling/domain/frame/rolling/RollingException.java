package bowling.domain.frame.rolling;

public class RollingException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "1회 투구 점수 범위를 벗어났습니다. 유효범위 0 ~ 10. 현재 점수 : %d";

    public RollingException(int fallenPin) {
        super(String.format(DEFAULT_MESSAGE, fallenPin));
    }


}

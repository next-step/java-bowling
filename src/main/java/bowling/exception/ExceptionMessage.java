package bowling.exception;

public final class ExceptionMessage {
    public static final String NAME_NOT_NULL = "플레이어 이름은 null이거나 빈 값일 수 없습니다.";
    public static final String INVALID_NAME_LENGTH = "플레이어 이름은 3글자여야 합니다.";
    public static final String INVALID_SCORE_VALUE = "투구값으로는 0 ~ 10 사이의 숫자를 입력해주세요.";
    public static final String NEXT_BOWL_IS_IMPOSSIBLE = "해당 프레임에서 다음 투구는 불가능합니다.";

    private ExceptionMessage() {
    }
}

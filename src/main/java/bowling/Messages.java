package bowling;

public class Messages {
    public static final String MESSAGE_INPUT_NAME = "플레이어 이름은? (3 English letters)";

    public static final String WARNING_PLAYERNAME_NOT_ALLOWED_LENGTH = "플레이어 이름은 3글자여야 합니다.";
    public static final String WARNING_PLAYERNAME_MUST_ENGLISH = "플레이어 이름은 영문이어야 합니다.";
    public static final String WARNING_SCANNERUTIL_NOT_ALLOWED_NULL_EMPTY = "공백 문자열은 입력할 수 없습니다.";
    public static final String WARNING_SCORE_NOT_ALLOWED_RANGE = "스코어는 0 이상 10 이하의 숫자로만 생성 가능합니다.";
    public static final String WARNING_FRAME_NOT_ALLOWED_SECOND_WHEN_STRIKE = "첫 번째 타구가 10점이면, 두 번째는 0점이어야 합니다.";
    public static final String WARNING_FRAME_NOT_ALLOWED_SUM = "첫번째 타구와 두번째 타구의 합은 10 이하여야 합니다.";
    public static final String WARNING_ORDINAL_NOT_FOUND_MATCHED_ORDINAL = "일치하는 Ordinal이 존재하지 않습니다.";
}

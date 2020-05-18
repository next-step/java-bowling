package bowling.view;

public class InputValidChecker {
    private static final String PLAYER_NAME_INVALID_MESSAGE = "플레이어 이름은 3글자의 영단어 입니다!";
    private static final String PIN_COUNT_INVALID_MESSAGE = "투구 결과는 0~10 사이입니다!";
    private static final String PLAYER_NAME_FORMAT = "[a-zA-Z]+";
    private static final int PLAYER_NAME_SIZE = 3;
    private static final int KNOCKED_DOWN_PIN_COUNT_MIN = 0;
    private static final int KNOCKED_DOWN_PIN_COUNT_MAX = 10;

    public static boolean isNameValid(String name) {
        if (!isNameStringValid(name)) {
            System.out.println(PLAYER_NAME_INVALID_MESSAGE);
            return false;
        }

        return true;
    }

    private static boolean isNameStringValid(String name) {
        return name.length() == PLAYER_NAME_SIZE && name.matches(PLAYER_NAME_FORMAT);
    }

    public static boolean isPinCountValid(int knockedDownPinCount) {
        if (!isKnockedDownPinCountValid(knockedDownPinCount)) {
            System.out.println(PIN_COUNT_INVALID_MESSAGE);
            return false;
        }

        return true;
    }

    private static boolean isKnockedDownPinCountValid(int knockedDownPinCount) {
        return KNOCKED_DOWN_PIN_COUNT_MIN <= knockedDownPinCount &&
                KNOCKED_DOWN_PIN_COUNT_MAX >= knockedDownPinCount;
    }
}

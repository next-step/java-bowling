package bowling.model;

public class BowlingValidator {
    public static void isBlank(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("빈값입니다.다시 입력해주세요.");
        }
    }

    public static int changeToInt(String pinCount) {
        return Integer.parseInt(pinCount.trim());
    }

}

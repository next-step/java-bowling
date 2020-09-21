package bowling.domain.player;

import java.util.Objects;
import java.util.regex.Pattern;

public class EnglishName {
    private static final String ENGLISH_PATTERN = "^[a-zA-Z]*$";
    private static final String ENGLISH_INPUT_ONLY = "영어만 입력이 입력이 가능합니다.";
    private static final String ALLOW_THREE_WORD = "3글자까지 입력이 가능합니다.";
    private static final int MAXIMUM_NAME_LENGTH = 3;

    private final String name;

    public EnglishName(final String inputText) {
        this.name = validateName(inputText);
    }

    public String getName() {
        return name;
    }

    private String validateName(final String inputText) {
        if (Objects.isNull(inputText) || inputText.isEmpty()) {
            throw new IllegalArgumentException("빈값이 입력될 수는 없습니다.");
        }
        if (isNotEnglish(inputText)) {
            throw new IllegalArgumentException(ENGLISH_INPUT_ONLY);
        }
        if (inputText.length() > MAXIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException(ALLOW_THREE_WORD);
        }
        return inputText;
    }

    private static boolean isNotEnglish(final String inputText) {
        return !Pattern.matches(ENGLISH_PATTERN, inputText);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnglishName)) return false;
        EnglishName that = (EnglishName) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}

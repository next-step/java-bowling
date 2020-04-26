package bowling.domain;

import java.util.regex.Pattern;

public class EnglishName {
    private final String userName;

    public EnglishName(final String inputText) {
        this.userName = validateName(inputText);
    }

    public String validateName(final String inputText) {
        if (isNotEnglish(inputText)) {
            throw new IllegalArgumentException("영어만 입력이 입력이 가능합니다.");
        }

        if (inputText.length() > 3) {
            throw new IllegalArgumentException("3글자까지 입력이 가능합니다.");
        }
        return inputText;
    }

    public static boolean isNotEnglish(String inputText) {
        return !Pattern.matches("^[a-zA-Z]*$", inputText);
    }

}

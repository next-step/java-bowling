package bowling.domain;

public class EnglishName {
    private final String userName;

    public EnglishName(final String inputText) {
        this.userName = validateName(inputText);
    }

    public String validateName(final String inputText) {
        if (inputText.length() > 3) {
            throw new IllegalArgumentException("3글자까지 입력이 가능합니다.");
        }
        return inputText;
    }
}

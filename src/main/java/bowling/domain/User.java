package bowling.domain;

public class User {
    private static final int LETTERS_LENGTH = 3;
    private final String letters;

    public User(String letters) {
        validate(letters);
        this.letters = letters;
    }

    private void validate(String letters) {
        if(letters.length() != LETTERS_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름은 3글자여야 합니다.");
        }
    }

    public String getLetters() {
        return letters;
    }

}

package bowling.domain;

import org.apache.logging.log4j.util.Strings;

public class User {
    private static final int MAX_LENGTH = 3;
    private static final String ALPHABET_MATCHER = "[a-zA-Z]+";
    private String name;

    public User(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (Strings.isEmpty(name)) {
            throw new IllegalArgumentException("이름이 비어있습니다.");
        }

        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("이름은 3글자 이상을 넘길 수 없습니다.");
        }

        if (!name.matches(ALPHABET_MATCHER)) {
            throw new IllegalArgumentException("이름은 영어만 포함되야 합니다.");
        }
    }
}

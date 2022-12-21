package bowling.model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {

    public static final int NAME_LENGTH = 3;
    public static final String REQUIRE_ENGLISH_NAME = "영어로 입력해주세요.";
    public static final String REQUIRE_NAME_LENGTH = "이름의 길이는 3글자입니다.";
    private final String name;

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (!isEnglish(name)) {
            throw new IllegalArgumentException(REQUIRE_ENGLISH_NAME);
        }
        if (!isValidNameLength(name)) {
            throw new IllegalArgumentException(REQUIRE_NAME_LENGTH);
        }
    }

    private static boolean isEnglish(String name) {
        return Pattern.matches("^[a-zA-Z]*$", name);
    }

    private static boolean isValidNameLength(String name) {
        return name.length() == NAME_LENGTH;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

package bowling.domain.player;

import bowling.exception.BowlingException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {

    static final String MAX_NAME_LENGTH = "이름은 최대 %d 글자여야 합니다.";
    static final String NAME_SHOULD_BE_ALPHABET = "영문자로 된 이름을 입력해주세요.";
    static final int MAX_LENGTH = 3;
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z]+");


    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name.toUpperCase();
    }

    public String name() {
        return name;
    }

    private void validateName(String name) {
        if (!getMatcherAboutName(name).matches()) {
            throw new BowlingException(NAME_SHOULD_BE_ALPHABET);
        }
        if (name.length() > MAX_LENGTH) {
            throw new BowlingException(String.format(MAX_NAME_LENGTH, MAX_LENGTH));
        }
    }

    private Matcher getMatcherAboutName(String name) {
        return NAME_PATTERN.matcher(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

package bowling.domain;

import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {
    private static final Pattern ENGLISH_PATTERN = Pattern.compile("[a-zA-Z]");

    private final String name;

    public Player(String name) {
        this.validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (StringUtils.isEmpty(name)
                || !ENGLISH_PATTERN.matcher(name).find()
                || name.length() != 3) {
            throw new IllegalArgumentException("이름은 영어 3글자로 입력해야합니다.");
        }
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

    @Override
    public String toString() {
        return name;
    }
}

package bowling.domain;

import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {
    private final String name;
    private final static String INVALID_PATTERN_REGEX = "^[a-zA-Z]*$";
    private final static Pattern pattern = Pattern.compile(INVALID_PATTERN_REGEX);
    private final static String INVALID_NAME = "잘못된 이름입니다.";
    private final static int NAME_LENGTH = 3;


    public Player(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (StringUtils.isEmpty(name) ||
            name.length() != NAME_LENGTH || !pattern.matcher(name).matches()) {
            throw new IllegalArgumentException(INVALID_NAME);
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
}

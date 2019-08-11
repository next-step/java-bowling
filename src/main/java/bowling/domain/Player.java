package bowling.domain;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-20 03:04
 */
public class Player {
    private static final String REG_NAME = "^[A-Z]*$";
    public static final String NAME_REG_EXCEPTION_MESSAGE = "이름은 영문 대문자 3자리만 가능합니다. : {} %s";
    public static final int IIMIT_NAME_SIZE = 3;

    private final String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player of(String name) {
        boolean match = Pattern.matches(REG_NAME, name);
        if (!match || name.length() > IIMIT_NAME_SIZE) {
            throw new IllegalArgumentException(String.format(NAME_REG_EXCEPTION_MESSAGE, name));
        }
        return new Player(name);
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

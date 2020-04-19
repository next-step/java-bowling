package bowling;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {

    private static final String NAME_VALIDATE_REGEX = "^[a-zA-Z]{3}$";
    private static final Pattern NAME_VALIDATE_PATTERN = Pattern.compile(NAME_VALIDATE_REGEX);

    private final String name;

    private Player(final String name) {
        this.name = name;
    }

    public static Player of(final String name) {
        validate(name);
        return new Player(name);
    }

    private static void validate(final String name) {
        validateNull(name);
        validateFormat(name);
    }

    private static void validateNull(String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("Player name is null");
        }
    }

    private static void validateFormat(final String name) {
        Matcher matcher = NAME_VALIDATE_PATTERN.matcher(name);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Player name must be three english character");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(getName(), player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}

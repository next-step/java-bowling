package bowling.player.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {
    private static final Pattern pattern = Pattern.compile("^[a-zA-Z]{3}$");

    private final String name;

    private Player(String name) {
        validate(name);
        this.name = name;
    }

    public static Player of(String name) {
        return new Player(name);
    }

    private void validate(String name) {
        if (Objects.isNull(name) || !pattern.matcher(name).find()) {
            throw new IllegalArgumentException("이름은 3글자로 입력해야 합니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player user = (Player) o;
        return Objects.equals(getName(), user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}

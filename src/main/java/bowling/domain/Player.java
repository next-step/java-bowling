package bowling.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {
    private static final int NAME_MAX_LENGTH = 3;
    private static final String REGEX = "^[a-zA-Z]*$";
    private static final Pattern pattern = Pattern.compile(REGEX);

    private final String name;


    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.length() != NAME_MAX_LENGTH || !pattern.matcher(name).matches()) {
            throw new IncorrectPlayerNameException();
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
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

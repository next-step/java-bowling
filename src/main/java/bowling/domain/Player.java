package bowling.domain;

import java.util.regex.Pattern;

public class Player {

    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Z]{3}");

    private final String name;

    public Player(String name) {
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException("Player name must be 3 alphabet letters");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

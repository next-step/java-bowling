package bowling.domain.player;

import java.util.Objects;

public class Player {
    private final static int MAX_LENGTH = 3;

    private final String name;

    public Player(String name) {
        this.validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException("name is required");
        }

        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(String.format("name less than %d", MAX_LENGTH));
        }
    }

    public String getName() {
        return this.name;
    }
}

package bowling.domain;

import bowling.annotations.ForUI;

public class Player {
    public static final String MAX_LENGTH_MESSAGE = "이름은 1~3글자입니다.";

    private static final int MAX_LENGTH = 3;

    private final String name;

    public Player(String name) {
        if (name == null || name.trim().isEmpty() || name.trim().length() > MAX_LENGTH) {
            throw new IllegalArgumentException(MAX_LENGTH_MESSAGE);
        }
        this.name = name.trim();
    }

    @ForUI
    public String name() {
        return name;
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

        return name != null ? name.equals(player.name) : player.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

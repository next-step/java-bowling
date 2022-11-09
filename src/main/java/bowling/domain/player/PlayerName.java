package bowling.domain.player;

import java.util.Objects;

import static java.util.Objects.isNull;

public class PlayerName {
    private final String name;

    public PlayerName(String name) {
        if (isNull(name) || name.isBlank() || name.length() > 3) {
            throw new IllegalArgumentException("이름은 공백이 아니고 3자 이하이어야 합니다.");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerName)) return false;

        PlayerName that = (PlayerName) o;

        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

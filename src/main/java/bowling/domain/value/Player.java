package bowling.domain.value;

import bowling.annotations.GetterForUI;
import bowling.utils.Preconditions;

import java.util.Objects;

public class Player {
    private static final int MAXIMUM_PLAYER_NAME_LENGTH = 3;

    private final String name;

    public Player(String name) {
        Preconditions.checkNotNull(name, "name은 필수값입니다.");
        Preconditions.checkMaximumSize(name.length(), MAXIMUM_PLAYER_NAME_LENGTH,
                                       String.format("name의 길이는 %s 이하 이어야 합니다.", MAXIMUM_PLAYER_NAME_LENGTH));

        this.name = name;
    }

    public static Player from(String name) {
        return new Player(name);
    }

    @GetterForUI
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

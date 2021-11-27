package bowling.domain.value;

import bowling.annotations.GetterForUI;
import bowling.utils.Preconditions;

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
}

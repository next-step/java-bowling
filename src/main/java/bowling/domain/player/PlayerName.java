package bowling.domain.player;

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
}

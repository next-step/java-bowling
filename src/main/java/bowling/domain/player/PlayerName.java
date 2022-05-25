package bowling.domain.player;


import org.apache.commons.lang3.StringUtils;

public class PlayerName {
    private final String name;

    public PlayerName(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("PlayerName은 빈 값일 수 없습니다.");
        }
    }
}

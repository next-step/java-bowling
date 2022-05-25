package bowling.domain.player;


import org.apache.commons.lang3.StringUtils;

public class PlayerName {
    static final int NAME_LENGTH = 3;

    private final String name;


    public PlayerName(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("PlayerName은 빈 값일 수 없습니다.");
        }

        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException(String.format("name(%s)은 길이가 %s 이어야 합니다.", name, NAME_LENGTH));
        }
    }

    @Override
    public String toString() {
        return name;
    }
}

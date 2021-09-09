package bowling.domain.player;

import java.util.Objects;

public class PlayerName {
    private String value;

    public PlayerName(String value){
        if(value.length() > 3){
            throw new IllegalArgumentException("이름은 3자리 이내로 입력해주세요.");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerName playerName = (PlayerName) o;
        return Objects.equals(value, playerName.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

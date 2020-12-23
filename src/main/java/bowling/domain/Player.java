package bowling.domain;

import java.util.Objects;

public class Player {

    private static final String PLAYER_NAME_PATTERN = "^[a-zA-Z]{3}$";

    private String name;

    private Player(String name){
        validatePlayerName(name);
        this.name = name;
    }

    public static Player from(String name){
        return new Player(name);
    }

    public String getName() {
        return name;
    }

    private void validatePlayerName(String name) {
        if (!name.matches(PLAYER_NAME_PATTERN)) {
            throw new IllegalArgumentException("플레이어의 이름을 영문자 세글자로 입력하세요.");
        }
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

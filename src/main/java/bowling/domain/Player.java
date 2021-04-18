package bowling.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {
    public static final int PLAYER_LENGTH = 3;
    public static final Pattern NAME_REGEX_PATTERN = Pattern.compile("[A-Za-z]+");

    private final String player;

    public Player(String player) {
        this.player = player;
        validate();
    }

    private void validate() {
        if (player.length() != PLAYER_LENGTH) {
            throw new IllegalArgumentException("플레이어 글자 수는 3글자 입니다.");
        }

        if (!NAME_REGEX_PATTERN.matcher(player).matches()) {
            throw new IllegalArgumentException("플레이어 이름은 영문만 입력 가능합니다.");
        }

        if (player.isEmpty()) {
            throw new IllegalArgumentException("3글자를 입력 해야합니다.");
        }
    }

    public static Player get(String name) {
        return new Player(name);
    }

    public String getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player1 = (Player) o;
        return Objects.equals(player, player1.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }
}

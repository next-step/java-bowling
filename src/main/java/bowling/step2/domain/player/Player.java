package bowling.step2.domain.player;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {
    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z]{3}$");

    private final String name;

    private Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        Matcher matcher = PATTERN.matcher(name);
        if (!matcher.find()){
            throw new IllegalArgumentException("플레이어 이름은 영어로 3글자만 가능합니다.");
        }
    }

    public static Player of(String name){
        return new Player(name);
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

    public String getName() {
        return name;
    }
}

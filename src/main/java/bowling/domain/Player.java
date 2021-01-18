package bowling.domain;

import org.springframework.util.StringUtils;

import java.util.Objects;

public class Player {
    private static final int MAX_LENGTH = 3;
    private String name;

    public Player(String name) {
        if(!StringUtils.hasLength(name)) throw new IllegalArgumentException("이름이 비어 있습니다");
        if(name.length() > MAX_LENGTH ) throw new IllegalArgumentException("이름은 최대 3글자 까지 허용합니다");

        this.name = name;
    }

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

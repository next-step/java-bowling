package bowling.domain;

import org.springframework.util.StringUtils;

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
}

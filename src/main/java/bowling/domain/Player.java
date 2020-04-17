package bowling.domain;

import java.util.Optional;

public class Player {
    private static final int REQUIRED_LENGTH = 3;

    private final String name;

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validate(String input) {
        Optional.ofNullable(input).orElseThrow(() -> new InvalidNameException("이름을 입력해야 합니다."));
        if (input.length() != REQUIRED_LENGTH) {
            throw new InvalidNameException("이름은 3자를 적어주세요.");
        }
    }
}

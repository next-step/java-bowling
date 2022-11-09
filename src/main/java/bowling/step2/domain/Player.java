package bowling.step2.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Player {
    static final String NAME_BLANK_EXCEPTION = "플레이어 이름이 입력되어야 합니다.";
    static final String NAME_LENGTH_EXCEPTION = "플레이어 이름은 3글자를 넘을 수 없습니다.";
    static final String NAME_ENGLISH_EXCEPTION = "플레이어 이름은 영어만 사용 가능합니다.";

    private final String name;

    private final Map<Integer, Frame> scoreMap = new HashMap<>();

    public Player(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_BLANK_EXCEPTION);
        }

        if (name.length() > 3) {
            throw new IllegalArgumentException(NAME_LENGTH_EXCEPTION);
        }

        if (!Pattern.matches("^[a-zA-Z]*$", name)) {
            throw new IllegalArgumentException(NAME_ENGLISH_EXCEPTION);
        }
    }

    public void addScoreMap(int index, Frame frame) {
        this.scoreMap.put(index, frame);
    }

    public Map<Integer, Frame> scoreMap() {
        return new HashMap<>(scoreMap);
    }

    public String name() {
        return name;
    }
}

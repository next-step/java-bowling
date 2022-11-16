package bowling.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Names {
    private static final int NAMES_SIZE_MIN = 1;

    private final List<Name> names;

    public Names(List<Name> names) {
        validateSize(names);
        this.names = names;
    }

    private void validateSize(List<Name> names) {
        if (names.size() < NAMES_SIZE_MIN) {
            throw new IllegalArgumentException("참여자는 " + NAMES_SIZE_MIN + "이상이어야 합니다.");
        }
    }

    public List<Name> names() {
        return this.names;
    }

    public Map<Name, Scoreboard> createScoreboards() {
        Map<Name, Scoreboard> scoreboards = new HashMap<>();
        for (Name name : this.names) {
            scoreboards.put(name, new Scoreboard(name));
        }
        return scoreboards;
    }
}

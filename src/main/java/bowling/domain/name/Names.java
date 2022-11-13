package bowling.domain.name;

import java.util.ArrayList;
import java.util.List;

public class Names {
    public static final String ERR_MSG_DUPLICATE_NAME = "다른 사용자와 동일한 이름을 사용할 수 없습니다.";
    private final List<Name> names;

    public Names(List<Name> names) {
        this.names = names;
    }

    public static Names init() {
        List<Name> names = new ArrayList<>();
        return new Names(names);
    }

    public void addName(final Name name) {
        if (names.contains(name)) {
            throw new IllegalArgumentException(ERR_MSG_DUPLICATE_NAME);
        }
        names.add(name);
    }

    public Name getPlayerName(int idx) {
        return names.get(idx);
    }

    public int getCountOfPlayer() {
        return names.size();
    }
}

package bowling.domain.name;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Names {
    public static final String ERR_MSG_DUPLICATE_NAME = "다른 사용자와 동일한 이름을 사용할 수 없습니다.";
    private final List<Name> names;

    public Names(List<Name> names) {
        Set<Name> nameSet = new HashSet<>(names);
        if (nameSet.size() != names.size()) {
            throw new IllegalArgumentException(ERR_MSG_DUPLICATE_NAME);
        }
        this.names = names;
    }

    public static Names init() {
        List<Name> names = new ArrayList<>();
        return new Names(names);
    }

    public int getCountOfPlayer() {
        return names.size();
    }

    public Name getPlayerName(int i) {
        return names.get(i);
    }
}

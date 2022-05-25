package bowling.domain.bowl;

import java.util.Collections;
import java.util.List;

public class Bowls {
    private final List<Bowl> bowls;

    public Bowls(List<Bowl> bowls) {
        validate(bowls);
        this.bowls = bowls;
    }

    private void validate(List<Bowl> bowls) {
        if (bowls == null) {
            throw new IllegalArgumentException("bowls는 null 일 수 없습니다.");
        }
    }

    public static Bowls initialize() {
        return new Bowls(Collections.emptyList());
    }
}

package bowling.util;

import bowling.domain.Shot;

import java.util.List;

public class BowlingUtils {
    public static int sum(List<Shot> shots) {
        return shots.stream().mapToInt((shot) -> shot.getDownCount())
                .sum();
    }

}

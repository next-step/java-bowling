package bowling.engine;

import java.util.List;
import java.util.stream.Stream;

public interface Result {
    Result next(Shot shot);
    Score score();
    boolean completed();
    boolean completedBonus();

    int size();
    boolean hasThirdChance();

    Bonus bonus();
    List<Shot> collect();
    Stream<Shot> stream();
}

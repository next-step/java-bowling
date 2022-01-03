package bowling.engine;

import java.util.List;
import java.util.stream.Stream;

public interface Result {
    Result next(Shot shot);
    Score score();
    boolean notEmpty();
    boolean completed();
    boolean remainBonus();

    int size();

    boolean hasThirdChance();
    Bonus bonus();
    List<Shot> collect();

    Stream<Shot> stream();

    Shots shots();
}

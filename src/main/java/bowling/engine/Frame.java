package bowling.engine;

import java.util.stream.Stream;

public interface Frame {
    Frame nextShot(Shot shot);

    Sequence sequence();
    Score score();
    boolean completed();
    boolean notEmpty();

    boolean isFinal();

    boolean remainBonus();

    Stream<Shot> stream();
}

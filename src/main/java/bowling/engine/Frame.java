package bowling.engine;

import java.util.stream.Stream;

public interface Frame {
    Frame nextShot(Shot shot);

    Sequence sequence();
    boolean completed();
    Score score();

    boolean isFinal();
    boolean hasThirdChance();

    Stream<Shot> stream();
}

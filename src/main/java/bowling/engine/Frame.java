package bowling.engine;

import java.util.stream.Stream;

public interface Frame {
    int NUMBER_OF_PINS = 10;
    int NUMBER_OF_SHOT = 2;
    int NUMBER_OF_FINAL_SHOT = 3;

    Frame nextShot(Shot shot);

    Sequence sequence();
    boolean completed();
    Score score();

    boolean isFinal();
    boolean hasThirdChance();

    Stream<Shot> stream();
}

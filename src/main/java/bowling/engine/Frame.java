package bowling.engine;

import bowling.engine.collection.FirstClassList;

public interface Frame extends FirstClassList<Shot> {
    int NUMBER_OF_PINS = 10;
    int NUMBER_OF_SHOT = 2;
    int NUMBER_OF_FINAL_SHOT = 3;

    Frame nextShot(Shot shot);

    Sequence sequence();
    boolean completed();
    Score score();

    boolean isClear();
    boolean isFinal();
    boolean isSpareChallenge();
    boolean hasThirdChance();
}

package bowling.engine;

import bowling.engine.collection.FirstClassList;

public interface Shots extends FirstClassList<Shot> {
    int NUMBER_OF_PINS = 10;
    int NUMBER_OF_SHOT = 2;
    int NUMBER_OF_FINAL_SHOT = 3;

    Shots nextShot(Shot shot);

    Score score();
    boolean isClear();
    boolean isSpareChallenge();
}

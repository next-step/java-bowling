package bowling.engine;

import bowling.engine.collection.FirstClassList;

public interface ScoreBoard extends FirstClassList<Frame> {
    int NUMBER_OF_FRAME = 10;

    Score score(Sequence sequence);
    Frame nextShot(Shot shot);

    Sequence current();

    Name name();

    boolean isEnded();
}

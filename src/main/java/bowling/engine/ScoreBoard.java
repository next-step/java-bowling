package bowling.engine;

import java.util.Optional;

import bowling.engine.collection.FirstClassList;

public interface ScoreBoard extends FirstClassList<Frame> {
    int NUMBER_OF_FRAME = 10;

    Optional<Score> score(Sequence sequence);
    Frame nextShot(Shot shot);

    Sequence current();

    Name name();

    boolean isEnded();
}

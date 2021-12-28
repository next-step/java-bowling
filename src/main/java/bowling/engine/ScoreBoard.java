package bowling.engine;

public interface ScoreBoard {
    int NUMBER_OF_FRAME = 10;

    Score score(Sequence sequence);
    Frame nextShot(Shot shot);

    Sequence current();

    Name name();
}

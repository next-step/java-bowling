package bowling.domain;


public interface Frame {

    Frame pitch(int countOfPins);
    
    boolean isEnd();

    Frame next();

    boolean isNormal();

    Pitches pitches();

    Score addScore(Score beforeScore);

    Score score();
}

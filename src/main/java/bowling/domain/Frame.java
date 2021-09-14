package bowling.domain;


public interface Frame {

    Frame pitch(int countOfPins);
    
    boolean isEnd();

    Frame next();

    boolean isNormal();

    Pitches pitches();
}

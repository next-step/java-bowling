package bowling.domain;


public interface Frame {

    Frame pitch(int countOfPins);
    
    boolean isEnd();

    String result();

    Frame next();
}

package bowling.domain;


public interface Frame {

    Frame pitch(int countOfPins);

    boolean isEnd();

    int sumPitches();
}

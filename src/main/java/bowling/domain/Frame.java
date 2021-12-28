package bowling.domain;

public interface Frame {

    void pitching(int pitchingNumber);
    boolean isPossiblePitching();
    int getCountOfHits(int pitchingNumber);
    int getCountOfPitching();

}

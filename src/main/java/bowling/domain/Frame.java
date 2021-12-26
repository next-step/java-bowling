package bowling.domain;

public interface Frame {

    void addKnockDownPins(int hittingPins);
    boolean isStrike(int hittingPins);
    boolean isPossiblePitching();
    int getCountOfHits(int index);
    int getCountOfPitchingSize();

}

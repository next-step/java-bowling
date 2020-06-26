package bowling.domain;

public interface Frame {
    Frame nextBowl(int downPin);
    boolean isLast();

}

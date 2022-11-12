package bowling.domain;

public interface Frame {
    Integer MAX_PIN = 10;

    String score();

    boolean availablePitching();

    int pitch(BowlingStrategy bowlingStrategy);
}

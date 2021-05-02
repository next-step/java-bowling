package bowling.domain;

public interface FrameType {
    Pitches pitch(int point);

    boolean isContinue();

    Pitches pitches();

    Score score();
}

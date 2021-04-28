package bowling.domain;

public interface FrameType {
    Pitches pitch(int point);

    boolean isContinue();

    int count();

    int sum();

    Pitches pitches();
}

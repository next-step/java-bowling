package bowling.domain;

public interface Frame {

    void play(Hit hit);

    FrameStatus getFirstStatus();
    FrameStatus getSecondStatus();
    FrameStatus getThirdStatus();

    Hit getFirstHit();
    Hit getSecondHit();
    Hit getThirdHit();

    boolean isEnd();
}

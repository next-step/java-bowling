package bowling.domain;

public interface Frame {

    void firstThrow(Hit hit);
    void secondThrow(Hit hit);
    void thirdThrow(Hit hit);

    FrameStatus getFirstStatus();
    FrameStatus getSecondStatus();
    FrameStatus getThirdStatus();

    Hit getFirstHit();
    Hit getSecondHit();
    Hit getThirdHit();

    boolean isEnd();
}

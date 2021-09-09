package bowling.domain.frame.info;

public interface FrameInfo {

    int FIRST_ROUND = 0;

    FrameInfo nextFrame();

    FrameInfo nextRound();

    boolean isEndFrame();

    boolean isLastRound();

    boolean isFirstRound();

}

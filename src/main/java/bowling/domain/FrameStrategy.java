package bowling.domain;

public interface FrameStrategy {

    boolean isFinal();
    boolean isFrameEnd(int round, Result beforeResult);

    Score getScore(RollingResult rollingResult);
}

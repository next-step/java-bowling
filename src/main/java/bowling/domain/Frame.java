package bowling.domain;

public interface Frame {

    String printableTitle();
    String printableValue();
    boolean record(BowlingScore score);
    Frame makeNextFrame();
    boolean isFinalFrame();
    boolean isEnd();
}

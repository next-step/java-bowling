package bowling.domain.frame;

public interface Frame {

    void record(int numDownedPins);

    boolean isEnd();

    int calculateScore();

    boolean needAdditionalScore();
}

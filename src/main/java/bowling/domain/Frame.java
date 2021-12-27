package bowling.domain;

public interface Frame {
    Frame bowl(Ball ball);

    boolean isEnd();

    int getFrameIndex();

    String symbol();
}

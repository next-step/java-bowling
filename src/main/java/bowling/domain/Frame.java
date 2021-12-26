package bowling.domain;

public interface Frame {
    Frame bowl(Pin pins);

    boolean isEnd();

    int getFrameIndex();

    String symbol();
}

package bowling.domain.frame;

public interface Frame {
    boolean isEnd();
    void pitch(int countOfPins);
    String valueOfFrame();
}

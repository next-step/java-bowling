package bowling.domain;

public interface Frame {
    Frame next(int countOfPin);
    String resultOfFrame();
}

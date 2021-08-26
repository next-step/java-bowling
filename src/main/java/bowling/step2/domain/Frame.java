package bowling.step2.domain;

public interface Frame {
    void pitch(TryNo tryNo, int count);
    int total();
    int countOfFirst();
    int countOfSecond();
}

package bowling.domain;

public interface Frame {
    Frame bowl(int fallenPinCount);

    int getNumber();

    String getRecord();
}

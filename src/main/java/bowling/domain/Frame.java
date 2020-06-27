package bowling.domain;

public interface Frame {
    Frame bowl(int downPin);
    boolean isLastTrying();
    String getResult();
}

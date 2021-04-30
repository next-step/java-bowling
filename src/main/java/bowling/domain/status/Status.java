package bowling.domain.status;

public interface Status {
    String display(int fallenPins);

    boolean isStrike();

    boolean isSpare();

    boolean isOpen();
}

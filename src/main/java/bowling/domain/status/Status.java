package bowling.domain.status;

public interface Status {
    Status roll(int fallenPins);

    boolean isEnd();

    boolean hasBonusPitch();

    String display();
}

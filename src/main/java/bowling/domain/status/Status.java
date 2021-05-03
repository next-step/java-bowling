package bowling.domain.status;

public interface Status {
    Status roll(int fallenPins);

    boolean isEnd();

    boolean hasBonusPitch();

    int bonusPitchCount();

    String display();
}

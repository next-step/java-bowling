package bowling.domain;

public interface Pitch {
    KnockedPins knockedPins();

    Pitch play(int knockedPinsCount);

    Pitch play(KnockedPins knockedPins);
}

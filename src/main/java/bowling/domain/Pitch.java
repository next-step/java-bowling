package bowling.domain;

public interface Pitch {
    KnockedPins knockedPins();

    Pitch play(KnockedPins knockedPins);
}

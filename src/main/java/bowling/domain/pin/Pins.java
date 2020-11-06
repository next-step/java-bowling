package bowling.domain.pin;

@FunctionalInterface
public interface Pins {
    int PIN_COUNT = 10;

    int count();
}

package bowling.domain.pin;

public class PinFactory {

    public static Pin create(PinGenerator pinGenerator) {
        return pinGenerator.generate();
    }
}

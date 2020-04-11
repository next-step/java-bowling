package bowling.domain.pin;

import bowling.domain.DomainGenerator;

public class PinGenerator implements DomainGenerator<Pin> {

    @Override
    public Pin generate() {
        return new Pin(PinState.STANDING);
    }

    public Pin generate(final PinState pinState) {
        return new Pin(pinState);
    }
}

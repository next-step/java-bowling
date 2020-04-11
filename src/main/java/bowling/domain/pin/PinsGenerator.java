package bowling.domain.pin;

import bowling.domain.DomainGenerator;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PinsGenerator implements DomainGenerator<Pins> {
    @Override
    public Pins generate() {
        return IntStream.range(0, Pins.MAX_COUNT)
                        .mapToObj(count -> new Pin(PinState.STANDING))
                        .collect(Collectors.collectingAndThen(Collectors.toList(), Pins::new));
    }
}

package bowling.domain.pin;

import bowling.domain.DomainGenerator;

import java.util.Arrays;

public class FakeThreePinsGenerator implements DomainGenerator<Pins> {
    @Override
    public Pins generate() {
        PinGenerator pinGenerator = new PinGenerator();
        return new Pins(Arrays.asList(
                pinGenerator.generate(PinState.KNOCK_OVER)
                , pinGenerator.generate(PinState.KNOCK_OVER)
                , pinGenerator.generate(PinState.KNOCK_OVER)
                , pinGenerator.generate(PinState.STANDING)
                , pinGenerator.generate(PinState.STANDING)
                , pinGenerator.generate(PinState.STANDING)
                , pinGenerator.generate(PinState.STANDING)
                , pinGenerator.generate(PinState.STANDING)
                , pinGenerator.generate(PinState.STANDING)
                , pinGenerator.generate(PinState.STANDING)
        ));
    }
}

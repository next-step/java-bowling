package bowling.domain.pin;

import bowling.domain.DomainGenerator;
import bowling.domain.frame.BowlCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {
    private DomainGenerator<Pins> pinsGenerator;

    @DisplayName("10개의 핀을 가진 Pins 객체를 생성할 수 있다.")
    @Test
    void create() {
        pinsGenerator = new PinsGenerator();
        Pins expect = pinsGenerator.generate();

        Pins actual = pinsGenerator.generate();

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("핀을 넘어트려 서있는 핀을 구할 수 있다.")
    @Test
    void getStandingPins() {
        pinsGenerator = new PinsGenerator();
        Pins pins = pinsGenerator.generate();
        BowlCount bowlCount = new BowlCount(3);
        final int expect = 7;

        final Pins standingPins = pins.knockOver(bowlCount);
        final int actual = standingPins.standingCount();

        assertThat(actual).isEqualTo(expect);
    }
}
package bowling.domain.pin;

import bowling.domain.DomainGenerator;
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

    @DisplayName("넘어져있는 3개의 핀의 갯수를 구할 수 있다.")
    @Test
    void knockOverCount() {
        pinsGenerator = new FakeThreePinsGenerator();

        Pins expect = pinsGenerator.generate();

        assertThat(expect.knockOverCount()).isEqualTo(3);
    }
}
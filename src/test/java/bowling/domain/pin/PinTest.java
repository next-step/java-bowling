package bowling.domain.pin;

import bowling.domain.DomainGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PinTest {
    private DomainGenerator<Pin> pinGenerator;

    @BeforeEach
    void setUp() {
        pinGenerator = new PinGenerator();
    }

    @DisplayName("핀을 생성할 수 있다.")
    @Test
    void create() {
        Pin expect = pinGenerator.generate();

        Pin actual = pinGenerator.generate();

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("핀을 넘어트릴 수 있다.")
    @Test
    void knockOver() {
        Pin expect = pinGenerator.generate().knockOver();

        Pin actual = pinGenerator.generate().knockOver();

        assertThat(actual).isEqualTo(expect);
    }
}
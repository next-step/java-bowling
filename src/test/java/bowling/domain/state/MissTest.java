package bowling.domain.state;

import bowling.domain.Pins;
import bowling.exception.NextPitchingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MissTest {

    Miss miss;

    @BeforeEach
    void setUp() {
        miss = new Miss(Pins.pitching(5), Pins.pitching(2));
    }

    @Test
    void 예외_테스트() {

        assertThatThrownBy(() -> {
            miss.nextPitch(3);
        }).isInstanceOf(NextPitchingException.class);
    }
}
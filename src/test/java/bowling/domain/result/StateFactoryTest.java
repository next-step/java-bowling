package bowling.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.Pin;
import bowling.domain.result.status.Gutter;
import bowling.domain.result.status.HitNumber;
import bowling.domain.result.status.Miss;
import bowling.domain.result.status.Spare;
import bowling.domain.result.status.Strike;
import org.junit.jupiter.api.Test;

public class StateFactoryTest {

    @Test
    void createStrikeTest() {
        assertThat(StateFactory.strike(Pin.of(10))).isInstanceOf(Strike.class);
    }

    @Test
    void createSpareTest() {
        assertThat(StateFactory.spare(Pin.of(5))).isInstanceOf(Spare.class);
    }

    @Test
    void createGutterTest() {
        assertThat(StateFactory.gutter(Pin.of(0))).isInstanceOf(Gutter.class);
    }

    @Test
    void createMissTest() {
        assertThat(StateFactory.miss()).isInstanceOf(Miss.class);
    }

    @Test
    void createHitNumberTest() {
        assertThat(StateFactory.number(Pin.of(5))).isInstanceOf(HitNumber.class);
    }
}

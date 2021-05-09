package bowling.domain.state.running;

import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.domain.state.finish.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReadyTest {

    @DisplayName("Ready 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        State ready = Ready.initialize();

        // then
        assertAll(
                () -> assertThat(ready).isNotNull(),
                () -> assertThat(ready).isInstanceOf(Ready.class)
        );
    }

    @DisplayName("Ready 인스턴스가 bowl() 시, 10이 들어오면 Strike 인스턴스를 반환하는지 테스트")
    @Test
    void 반환_bowl() {
        //given
        Pins pins = Pins.valueOf(10);

        // when
        State ready = Ready.initialize();
        State actual = ready.bowl(pins);

        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual).isInstanceOf(Strike.class)
        );
    }

}
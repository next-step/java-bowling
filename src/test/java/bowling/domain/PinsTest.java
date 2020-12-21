package bowling.domain;

import bowling.domain.state.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    private Pins pins;

    @BeforeEach
    void setUp() {
        pins = new Pins();
    }

    @Test
    @DisplayName("핀 쓰러뜨리기")
    void fall() {
        pins.fall(9);

        Pins pinsExpected = new Pins(1, Collections.singletonList(9));
        assertThat(pins).isEqualTo(pinsExpected);
    }

    @Test
    @DisplayName("핀 10개 이상 쓰러뜨리면 에러")
    void fall_fail() {
        assertThatThrownBy(() -> {
            pins.fall(11);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("pins must be over 0");
    }

    @Test
    @DisplayName("스페어인지 확인")
    void isSpare() {
        pins.fall(9);
        pins.fall(1);

        assertThat(pins.isSpare()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 확인")
    void isStrike() {
        pins.fall(10);

        assertThat(pins.isStrike()).isTrue();
    }

    @Test
    @DisplayName("핀 다 쓰러졌는지 확인")
    void isAllDown_success() {
        Pins pins = new Pins(0, new ArrayList<>());

        assertThat(pins.isAllDown()).isTrue();
    }

    @Test
    @DisplayName("핀 다 쓰러졌는지 확인")
    void isAllDown_fail() {
        Pins pins = new Pins(1, new ArrayList<>());

        assertThat(pins.isAllDown()).isFalse();
    }
}


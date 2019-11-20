package com.seok2.bowling.state.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import com.seok2.bowling.pin.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpareTest {

    @Test
    @DisplayName("Cover 상테에서 넘어진 핀과 같은 수의 넘어진 핀을 가진다.")
    void of() {
        assertThat(Cover.of(Pin.of(7)).roll(Pin.of(3))).isEqualTo(Spare.of(Pin.of(7)));
    }

    @Test
    @DisplayName("Spare 상태에서는 추가적으로 공을 굴릴 수 없다.")
    void throwException() {
        assertThatIllegalArgumentException().isThrownBy(() -> Strike.of().roll(Pin.of(10)));
    }

    @Test
    @DisplayName("Spare 상태는 종료(True) 값을 가진다.")
    void isEnd() {
        assertThat(Spare.of(Pin.of(7)).isEnd()).isTrue();
    }
}
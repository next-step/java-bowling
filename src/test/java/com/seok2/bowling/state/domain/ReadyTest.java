package com.seok2.bowling.state.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.seok2.bowling.pin.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {

    @Test
    @DisplayName("Ready 상태에서 모든 핀을 쓰러뜨리면 Strike 상태가 된다.")
    void Strike() {
        assertThat(Ready.of().roll(Pin.of(10))).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("Ready 상태에서 10개 미만의 핀을 쓰러뜨리면 Cover 상태가 된다.")
    void Continue() {
        assertThat(Ready.of().roll(Pin.of(0))).isInstanceOf(Cover.class);
    }

    @Test
    @DisplayName("Ready 상태는 추가 투구 가능한(False) 값을 가진다.")
    void isEnd() {
        assertThat(Ready.of().isEnd()).isFalse();
    }
}
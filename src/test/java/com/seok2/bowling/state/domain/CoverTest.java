package com.seok2.bowling.state.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.seok2.bowling.pin.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoverTest {

    @Test
    @DisplayName("Cover 상태에서 모든 핀을 쓰러뜨리면 Spare 상태가 된다.")
    void spare() {
        assertThat(Cover.of(Pin.of(7)).roll(Pin.of(3))).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("Cover 상태에서 이전 투구를 포함하여 아무 핀도 쓰러뜨리지 못한 경우 Gutter 상태가 된다.")
    void gutter() {
        assertThat(Cover.of(Pin.of(0)).roll(Pin.of(0))).isInstanceOf(Gutter.class);
    }

    @Test
    @DisplayName("Cover 상태에서 이전 투구를 포함하여 1~9개의 핀을 쓰러뜨린 경우 MISS 상태가 된다.")
    void miss() {
        assertThat(Cover.of(Pin.of(3)).roll(Pin.of(0))).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("Cover 상태는 추가 투구 가능한(False) 값을 가진다.")
    void isEnd() {
        assertThat(Cover.of(Pin.of(3)).isEnd()).isFalse();
    }

}
package com.seok2.bowling.frame.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.seok2.bowling.pin.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    @DisplayName("일반 프레임에서 0~9개의 핀을 투구 가능한(False) 값을 가진다.")
    void cover() {
        Frame frame = NormalFrame.of();
        frame.roll(Pin.of(8));
        assertThat(frame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("일반 프레임에서 MISS 를 치면 상태가 종료(True) 값을 가진다.")
    void miss() {
        Frame frame = NormalFrame.of();
        frame.roll(Pin.of(8));
        frame.roll(Pin.of(1));
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("일반 프레임에서 Spare 를 치면 상태가 종료(True) 값을 가진다.")
    void spare() {
        Frame frame = NormalFrame.of();
        frame.roll(Pin.of(8));
        frame.roll(Pin.of(2));
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("일반 프레임에서 Strike 를 치면 상태가 종료(True) 값을 가진다.")
    void strike() {
        Frame frame = NormalFrame.of();
        frame.roll(Pin.of(10));
        assertThat(frame.isEnd()).isTrue();
    }
}
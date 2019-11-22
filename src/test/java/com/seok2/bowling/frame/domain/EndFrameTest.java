package com.seok2.bowling.frame.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.seok2.bowling.pin.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndFrameTest {

    @Test
    @DisplayName("모두 STRIKE 를 기록할 경우 최대 3번의 투구 기회를 가진다.")
    void strike() {
        Frame frame = EndFrame.of();
        frame.roll(Pin.of(10));
        frame.roll(Pin.of(10));
        frame.roll(Pin.of(10));
        assertThat(frame.isEnd()).isTrue();
    }


    @Test
    @DisplayName("SPARE 를 기록할 경우 최대 3번의 투구 기회를 가진다.")
    void spare() {
        Frame frame = EndFrame.of();
        frame.roll(Pin.of(9));
        frame.roll(Pin.of(1));
        frame.roll(Pin.of(10));
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("MISS 기록할 경우 최대 2번의 투구 기회를 가진다.")
    void miss() {
        Frame frame = EndFrame.of();
        frame.roll(Pin.of(8));
        frame.roll(Pin.of(1));
        assertThat(frame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("모두 STRIKE 를 기록할 경우 점수 계산")
    void strikeGetScore() {
        Frame frame = EndFrame.of();
        frame.roll(Pin.of(10));
        frame.roll(Pin.of(10));
        frame.roll(Pin.of(10));
        assertThat(frame.getScore()).isEqualTo(Score.of(30));
    }


    @Test
    @DisplayName("SPARE 를 기록할 경우 점수 계산")
    void spareGetScore() {
        Frame frame = EndFrame.of();
        frame.roll(Pin.of(9));
        frame.roll(Pin.of(1));
        frame.roll(Pin.of(10));
        assertThat(frame.getScore()).isEqualTo(Score.of(20));
    }

    @Test
    @DisplayName("MISS 기록할 경우 점수 계산")
    void missGetScore() {
        Frame frame = EndFrame.of();
        frame.roll(Pin.of(8));
        frame.roll(Pin.of(1));
        assertThat(frame.getScore()).isEqualTo(Score.of(9));
    }

}
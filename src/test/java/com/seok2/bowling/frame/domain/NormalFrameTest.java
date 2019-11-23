package com.seok2.bowling.frame.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.seok2.bowling.pin.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    @DisplayName("일반 프레임에서 0~9개의 핀을 넘어뜨리면 현재 프레임을 유지한다.")
    void cover() {
        Frame frame = Frame.init();
        Frame current = frame.roll(Pin.of(8));
        assertThat(frame).isEqualTo(current);
    }

    @Test
    @DisplayName("일반 프레임에서 MISS 를 기록하면 다음프레임으로 전환 된다.")
    void miss() {
        Frame frame = Frame.init();
        frame.roll(Pin.of(1));
        Frame current = frame.roll(Pin.of(2));
        assertThat(current.getIndex()).isEqualTo(Index.of(2));
    }

    @Test
    @DisplayName("일반 프레임에서 Spare 를 기록하면 다음프레임으로 전환 된다.")
    void spare() {
        Frame frame = Frame.init();
        frame.roll(Pin.of(8));
        Frame current = frame.roll(Pin.of(2));
        assertThat(current.getIndex()).isEqualTo(Index.of(2));
    }



    @Test
    @DisplayName("일반 프레임에서 Strike 를 기록하면 다음프레임으로 전환 된다.")
    void strike() {
        Frame frame = Frame.init();
        Frame current = frame.roll(Pin.of(10));
        assertThat(current.getIndex()).isEqualTo(Index.of(2));
    }

    @Test
    @DisplayName("일반 프레임에서 Cover 인 경우 쓰러뜨린 핀의 점수와 1의 추가 횟수를 가진 상태를 리턴한다.")
    void coverGetScore() {
        Frame frame = Frame.init();
        frame.roll(Pin.of(7));
        assertThat(frame.getScore()).isEqualTo(Score.of(7,Remaining.COVER));
    }

    @Test
    @DisplayName("일반 프레임에서 Gutter 인 경우 기록할 경우 바로 점수를 계산할 수 있다.")
    void gutterGetScore() {
        Frame frame = Frame.init();
        frame.roll(Pin.of(0));
        frame.roll(Pin.of(0));
        assertThat(frame.getScore()).isEqualTo(Score.of(0,Remaining.of(0)));
    }

    @Test
    @DisplayName("일반 프레임에서 MISS 인 경우 기록할 경우 바로 점수를 계산할 수 있다.")
    void missGetScore() {
        Frame frame = Frame.init();
        frame.roll(Pin.of(1));
        frame.roll(Pin.of(2));
        assertThat(frame.getScore()).isEqualTo(Score.of(3,Remaining.of(0)));
    }

    @Test
    @DisplayName("일반 프레임에서 Spare 인 경우 추가적으로 1번에 투구가 더 필요하다.")
    void spareGetScore() {
        Frame frame = Frame.init();
        frame.roll(Pin.of(8));
        Frame current = frame.roll(Pin.of(2));
        current.roll(Pin.of(5));
        assertThat(frame.getScore()).isEqualTo(Score.of(15,Remaining.of(0)));
    }

    @Test
    @DisplayName("일반 프레임에서 Strike 인 경우 추가적으로 2번에 투구가 더 필요하다.")
    void strikeGetScore() {
        Frame frame = Frame.init();
        Frame current = frame.roll(Pin.of(10));
        current.roll(Pin.of(5));
        current.roll(Pin.of(2));
        assertThat(frame.getScore()).isEqualTo(Score.of(17,Remaining.of(0)));
    }



}
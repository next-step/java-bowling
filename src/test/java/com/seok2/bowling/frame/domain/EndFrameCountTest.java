package com.seok2.bowling.frame.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndFrameCountTest {

    @Test
    @DisplayName("increment 를 호출 할 경우 값이 1 증가한다.")
    void increment() {
        assertThat(EndFrameCount.of().increment()).isEqualTo(EndFrameCount.of(1));
    }

    @Test
    @DisplayName("Count 는 최대값을 3으로 가진다.")
    void isMax() {
        assertThat(EndFrameCount.of(2).isMax()).isFalse();
        assertThat(EndFrameCount.of(3).isMax()).isTrue();
    }
}
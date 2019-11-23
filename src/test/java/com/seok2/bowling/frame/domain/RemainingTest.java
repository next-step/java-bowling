package com.seok2.bowling.frame.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RemainingTest {

    @Test
    @DisplayName("생성 태스트 - MISS, COVER 등")
    void zero() {
        assertThat(Remaining.ofZero()).isEqualTo(Remaining.of(0));
    }

    @Test
    @DisplayName("생성 태스트 - SPARE")
    void ofSpare() {
        assertThat(Remaining.ofSpare()).isEqualTo(Remaining.of(1));
    }

    @Test
    @DisplayName("생성 태스트 - STRIKE")
    void ofStrike() {
        assertThat(Remaining.ofStrike()).isEqualTo(Remaining.of(2));
    }

    @Test
    @DisplayName("생성 테스트 - INFINITY")
    void ofInfinity() {
        assertThat(Remaining.ofInfinity()).isEqualTo(Remaining.of(3));
    }

    @Test
    @DisplayName("감소 테스")
    void decrement() {
        Remaining remaining = Remaining.of(2);
        remaining.decrement();
        assertThat(remaining).isEqualTo(Remaining.of(1));
    }

    @Test
    @DisplayName("값이 0인경우 True 를 리턴한다.")
    void isZero() {
        assertThat(Remaining.ofZero().isZero()).isTrue();

    }
}
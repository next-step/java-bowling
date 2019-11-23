package com.seok2.bowling.frame.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RemainingTest {

    @Test
    @DisplayName("Spare 상태는 1회의 추가 점수를 요구한다.")
    void spare() {
        assertThat(Remaining.SPARE).isEqualTo(Remaining.of(1));
    }

    @Test
    @DisplayName("Strike 상태는 1회의 추가 점수를 요구한다.")
    void strike() {
        assertThat(Remaining.STRIKE).isEqualTo(Remaining.of(2));
    }


    @Test
    @DisplayName("감소 테스트 2-1 = 1")
    void decrement() {
        assertThat(Remaining.of(2).decrement()).isEqualTo(Remaining.of(1));
    }

    @Test
    @DisplayName("Remaining 의 값이 0인 경우 True 를 리턴한다.")
    void isZero() {
        assertThat(Remaining.of(0).isZero()).isTrue();
    }
}
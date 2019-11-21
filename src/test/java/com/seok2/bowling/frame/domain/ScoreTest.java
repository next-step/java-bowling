package com.seok2.bowling.frame.domain;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("Score 생성 테스트")
    void of() {
        assertThat(Score.of(10)).isEqualTo(Score.of(10));
    }

    @Test
    @DisplayName("Score 덧셈 테스트 10 + 10 = 20")
    void add() {
        assertThat(Score.of(10).add(Score.of(10))).isEqualTo(Score.of(20));
    }
}
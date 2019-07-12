package com.jaeyeonling.bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CounterTest {

    private Counter counter;

    @BeforeEach
    void setUp() {
        counter = new Counter();
    }

    @DisplayName("카운트하면 기본 값 보다 높거나 같다.")
    @Test
    void isHigherAndEquals() {
        counter.count();
        assertThat(counter.isHigherAndEquals(1)).isTrue();
    }

    @DisplayName("카운트하면 기본 값 보다 낮거나 같다.")
    @Test
    void isLowerAndEquals() {
        counter.count();
        assertThat(counter.isLowerAndEquals(0)).isFalse();
    }
}

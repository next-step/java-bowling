package com.jaeyeonling.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CounterTest {

    private Counter counter;

    @BeforeEach
    void setUp() {
        counter = new Counter();
    }

    @DisplayName("카운트 후 해당 숫자보다 높은지 확인한다.")
    @Test
    void isHigherAndEquals() {

        assertThat(counter.isHigherAndEquals(0)).isTrue();
        assertThat(counter.isHigherAndEquals(1)).isFalse();

        counter.count();
        assertThat(counter.isHigherAndEquals(0)).isTrue();
        assertThat(counter.isHigherAndEquals(1)).isTrue();
        assertThat(counter.isHigherAndEquals(2)).isFalse();

        counter.count();
        assertThat(counter.isHigherAndEquals(0)).isTrue();
        assertThat(counter.isHigherAndEquals(1)).isTrue();
        assertThat(counter.isHigherAndEquals(2)).isTrue();
        assertThat(counter.isHigherAndEquals(3)).isFalse();
    }

    @DisplayName("카운트 후 해당 숫자보다 높은지 확인한다.")
    @Test
    void isLowerAndEquals() {

        assertThat(counter.isLowerAndEquals(0)).isTrue();
        assertThat(counter.isLowerAndEquals(1)).isTrue();

        counter.count();
        assertThat(counter.isLowerAndEquals(0)).isFalse();
        assertThat(counter.isLowerAndEquals(1)).isTrue();
        assertThat(counter.isLowerAndEquals(2)).isTrue();

        counter.count();
        assertThat(counter.isLowerAndEquals(0)).isFalse();
        assertThat(counter.isLowerAndEquals(1)).isFalse();
        assertThat(counter.isLowerAndEquals(2)).isTrue();
        assertThat(counter.isLowerAndEquals(3)).isTrue();
    }
}

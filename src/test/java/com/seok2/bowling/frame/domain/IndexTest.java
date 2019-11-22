package com.seok2.bowling.frame.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IndexTest {

    @Test
    void first() {
        assertThat(Index.first()).isEqualTo(Index.of(1));
    }

    @Test
    void of() {
        assertThat(Index.of(1)).isEqualTo(Index.of(1));
    }

    @Test
    void increment() {
        assertThat(Index.first().increment()).isEqualTo(Index.of(2));
    }

    @Test
    void isThreshold() {
        assertThat(Index.of(9).isThreshold()).isTrue();
    }
}
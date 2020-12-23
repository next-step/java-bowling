package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalTriesTest {
    private NormalTries normalTries;

    @BeforeEach
    void setUp() {
        normalTries = NormalTries.init();
    }

    @Test
    void isStrikeTest() {
        normalTries.add(10);
        assertThat(normalTries.isStrike()).isTrue();
    }

    @Test
    void isSpareTest() {
        normalTries.add(3);
        normalTries.add(7);
        assertThat(normalTries.isSpare()).isTrue();
    }

    @Test
    void isOverTest() {
        normalTries.add(3);
        normalTries.add(5);
        assertThat(normalTries.isOver()).isTrue();
    }

    @Test
    void isFirstNotThrownTest() {
        assertThat(normalTries.isFirstNotThrown()).isTrue();
    }

    @Test
    void isSecondNotThrownTest() {
        normalTries.add(10);
        assertThat(normalTries.isSecondNotThrown()).isTrue();
    }

    @Test
    void firstTest() {
        normalTries.add(3);
        assertThat(normalTries.first()).isEqualTo(Try.of(3));
    }

    @Test
    void secondTest() {
        normalTries.add(3);
        normalTries.add(5);
        assertThat(normalTries.second()).isEqualTo(Try.of(5));
    }
}

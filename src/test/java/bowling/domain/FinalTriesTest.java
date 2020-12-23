package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalTriesTest {
    private FinalTries finalTries;

    @BeforeEach
    void setUp() {
        finalTries = FinalTries.init();
    }

    @Test
    void isStrikeTest() {
        finalTries.add(10);
        assertThat(finalTries.isStrike()).isTrue();
    }

    @Test
    void isSpareTest() {
        finalTries.add(3);
        finalTries.add(7);
        assertThat(finalTries.isSpare()).isTrue();
    }

    @Test
    void isOverTest() {
        finalTries.add(3);
        finalTries.add(5);
        assertThat(finalTries.isOver()).isTrue();
    }

    @Test
    void isFirstNotThrownTest() {
        assertThat(finalTries.isFirstNotThrown()).isTrue();
    }

    @Test
    void isSecondNotThrownTest() {
        finalTries.add(10);
        assertThat(finalTries.isSecondNotThrown()).isTrue();
    }

    @Test
    void isThirdNotThrownTest() {
        finalTries.add(10);
        finalTries.add(5);
        assertThat(finalTries.isThirdNotThrown()).isTrue();
    }

    @Test
    void firstTest() {
        finalTries.add(3);
        assertThat(finalTries.first()).isEqualTo(Try.of(3));
    }

    @Test
    void secondTest() {
        finalTries.add(3);
        finalTries.add(5);
        assertThat(finalTries.second()).isEqualTo(Try.of(5));
    }

    @Test
    void thirdTest() {
        finalTries.add(10);
        finalTries.add(3);
        finalTries.add(5);
        assertThat(finalTries.third()).isEqualTo(Try.of(5));
    }
}

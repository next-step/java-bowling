package bowling.domain.state;

import bowling.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowledOnceTest {
    private BowledOnce bowledOnce;

    @BeforeEach
    void setUp() {
        bowledOnce = BowledOnce.of(5);
    }

    @Test
    void isCalculatedTest() {
        assertThat(bowledOnce.isCalculated()).isFalse();
    }

    @Test
    void isOverTest() {
        assertThat(bowledOnce.isOver()).isFalse();
    }

    @Test
    void bowlTest_whenResultingInSpare() {
        assertThat(bowledOnce.bowl(5)).isEqualTo(Spare.of(5,5));
    }

    @Test
    void bowlTest_whenResultingInMiss() {
        assertThat(bowledOnce.bowl(3)).isEqualTo(Miss.of(5,3));
    }

    @Test
    void calculateScoreTest() {
        assertThat(bowledOnce.calculateScore()).isEqualTo(new Score(5,1));
    }

}

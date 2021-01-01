package bowling.domain.state;

import bowling.exception.NoMoreChanceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MissTest {
    private Miss miss;

    @BeforeEach
    void setUp() {
        miss = Miss.of(5,3);
    }

    @Test
    void ofTest(){
        assertThatThrownBy( () -> Miss.of(5,5)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void isCalculatedTest() {
        assertThat(miss.isCalculated()).isTrue();
    }

    @Test
    void isOverTest() {
        assertThat(miss.isOver()).isTrue();
    }

    @Test
    void bowlTest() {
        assertThatThrownBy( () -> miss.bowl(3)).isInstanceOf(NoMoreChanceException.class);
    }
}

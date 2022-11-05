package bowling.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

class BowlingGameGeneratorTest {

    @DisplayName("볼링 게임을 생성하면, 예외가 발생하지 않아야 한다.")
    @Test
    void createBowlingGame() {
        assertThatNoException()
                .isThrownBy(BowlingGameGenerator::createBowlingGame);
    }

}

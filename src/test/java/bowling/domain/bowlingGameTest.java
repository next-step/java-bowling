package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class bowlingGameTest {

    @DisplayName("bowling game 수 확인")
    @Test
    void construct() {
        BowlingGame bowlingGame = new BowlingGame();
        assertThat(bowlingGame.getBowlingGame()).hasSize(10);
    }
}

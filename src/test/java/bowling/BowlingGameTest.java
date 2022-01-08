package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    @Test
    void 게임_생성() {
        assertThat(BowlingGame.create(new Player("dugi"))).isNotNull();
    }

}
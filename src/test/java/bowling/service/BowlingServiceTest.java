package bowling.service;

import bowling.domain.Board;
import bowling.domain.frame.Pin;
import bowling.domain.game.BowlingGame;
import bowling.domain.result.GameResultTest;
import bowling.domain.user.UserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingServiceTest {

    private BowlingService bowlingService;

    @BeforeEach
    void setUp() {
        bowlingService = new BowlingService(BowlingGame.readyGame(UserTest.MIZ));
    }

    @DisplayName("bowl 호출 시 Board 객체를 반환한다.")
    @Test
    void bowlTest() {
        assertThat(bowlingService.bowl(Pin.TEN))
                .isEqualTo(new Board(GameResultTest.MIZ_STRIKE));
    }
}

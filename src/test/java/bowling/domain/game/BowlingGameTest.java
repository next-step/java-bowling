package bowling.domain.game;

import bowling.domain.frame.Frames;
import bowling.domain.frame.Pin;
import bowling.domain.frame.Round;
import bowling.domain.result.GameResultTest;
import bowling.domain.user.User;
import bowling.domain.user.UserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    @DisplayName("생성 테스트")
    @Test
    void createTest() {
        assertThat(BowlingGame.readyGame(User.from("miz")))
                .isEqualTo(BowlingGame.of(User.from("miz"), Frames.readyFrames()));
    }

    @DisplayName("bowl() 은 GameResult를 반환한다.")
    @Test
    void bowlTest() {
        BowlingGame bowlingGame = BowlingGame.readyGame(UserTest.MIZ);
        bowlingGame.bowl(Pin.TEN);
        assertThat(bowlingGame.createResult()).isEqualTo(GameResultTest.MIZ_STRIKE);

        bowlingGame.bowl(Pin.from(5));
        assertThat(bowlingGame.createResult()).isEqualTo(GameResultTest.MIZ_STRIKE_AND_FIVE);
    }

    @DisplayName("round() 현재 라운드를 반환한다.")
    @Test
    void roundTest() {
        BowlingGame bowlingGame = BowlingGame.readyGame(UserTest.MIZ);
        assertThat(bowlingGame.round()).isEqualTo(Round.FIRST);
        bowlingGame.bowl(Pin.TEN);
        assertThat(bowlingGame.round()).isEqualTo(Round.from(2));
    }
}

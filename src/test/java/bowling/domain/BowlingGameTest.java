package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.BowlingGameTestFixture.createBowlingGame;
import static bowling.domain.BowlingGameTestFixture.createEndedBowlingGame;
import static bowling.domain.FrameTestFixture.createNormalBowlingGameFrame;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class BowlingGameTest {

    @DisplayName("종료된 프레임인 경우, 종료 여부 확인시 true를 반환해야 한다.")
    @Test
    void isEnded() {
        BowlingGame game = createEndedBowlingGame();

        assertThat(game.isEnded()).isTrue();
    }

    @DisplayName("진행 중인 프레임을 반환해야 한다.")
    @Test
    void getCurrentFrame() {
        BowlingGame game = createBowlingGame(1, 2, 1);

        assertThat(game.getCurrentFrame()).isEqualTo(createNormalBowlingGameFrame(1));
    }

    @DisplayName("종료된 상태라면, 예외가 발생해야 한다.")
    @Test
    void getCurrent_givenEndedGame() {
        BowlingGame game = createEndedBowlingGame();

        assertThatIllegalStateException()
                .isThrownBy(game::getCurrentFrame)
                .withMessage("이미 종료된 게임입니다.");
    }

    @DisplayName("투구를 하면, 투구 결과가 반영되어야 한다.")
    @Test
    void hit() {
        BowlingGame game = createBowlingGame(10);
        game.hit(1);
        game.hit(2);
        game.hit(10);
        game.hit(3);

        assertThat(game).isEqualTo(createBowlingGame(10, 1, 2, 10, 3));
    }

    @DisplayName("남아 있는 볼링 핀의 개수를 반환해야 한다.")
    @Test
    void getRemainedPins() {
        BowlingGame game = new BowlingGame();

        assertThat(game.getRemainedPins()).isEqualTo(10);

        game.hit(1);
        assertThat(game.getRemainedPins()).isEqualTo(9);

        game.hit(2);
        assertThat(game.getRemainedPins()).isEqualTo(10);

        game.hit(3);
        assertThat(game.getRemainedPins()).isEqualTo(7);
    }

}

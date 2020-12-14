package bowling.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import bowling.domain.FallingPinCount;
import bowling.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BowlingGameTest {

    private final Player givenPlayer = new Player("TED");
    private BowlingGame givenGame;

    @BeforeEach
    void setup() {
        givenGame = new BowlingGame(givenPlayer);
    }

    @DisplayName("프레임 번호 가져오기 테스트")
    @Test
    void getCurrentFrameSequence() {
        assertThat(givenGame.getCurrentFrameSequence()).isEqualTo(1);
    }

    @DisplayName("게임 플레이 테스트")
    @Test
    void pitchTheBall() {
        givenGame.play(FallingPinCount.fromFallingCount(8));
        assertThat(givenGame.getCurrentFrameSequence()).isEqualTo(1);

        givenGame.play(FallingPinCount.fromFallingCount(2));
        assertThat(givenGame.getCurrentFrameSequence()).isEqualTo(2);
    }

    @DisplayName("10 프레임까지 진행하는지 테스트")
    @Test
    void isGameFinished() {
        assertThat(givenGame.getCurrentFrameSequence()).isEqualTo(1);
        while (!givenGame.isGameFinished()) {
            givenGame.play(FallingPinCount.fromFallingCount(10));
        }
        assertThat(givenGame.getCurrentFrameSequence()).isEqualTo(10);
    }
}

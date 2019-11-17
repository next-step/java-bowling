import game.Frame;
import game.GameType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FrameTest {
    @Test
    @DisplayName("프레임 생성 테스트")
    void createTest() {
        Frame frame = Frame.of(10);
        assertThat(frame).isEqualTo(Frame.of(10));
    }

    @Test
    @DisplayName("점수생성")
    void checkScore() {
        Frame frame = Frame.of(9);
        frame.addScore(1);
        assertThat(frame.getGameType()).isEqualTo(GameType.SPARE);

        frame = Frame.of(10);
        assertThat(frame.getGameType()).isEqualTo(GameType.STRIKE);

        frame = Frame.of(4);
        frame.addScore(4);
        assertThat(frame.getGameType()).isEqualTo(GameType.MISS);
    }

    @Test
    @DisplayName("점수 생성 시 예외발생")
    void createScoreException() {
        assertThrows(IllegalArgumentException.class, () -> Frame.of(11));
        assertThrows(IllegalArgumentException.class, () -> Frame.of(10).addScore(1));
        assertThrows(IllegalArgumentException.class, () -> Frame.of(4).addScore(7));
    }

    @Test
    @DisplayName("마지막 프레임이 아닌데 보너스 게임을 한 경우")
    void bonusGameWithoutFinalFrameException() {
        Frame frame = Frame.of(10);
        assertThrows(IllegalArgumentException.class, () -> frame.addBonus(10));
    }

    @Test
    @DisplayName("보너스 게임 조건이 충족되지 않은 경우")
    void bonusGameWithMissFrame() {
        Frame bonusGameWithGutter = Frame.finalOf(8);
        bonusGameWithGutter.addScore(1);
        assertThrows(IllegalArgumentException.class, () -> bonusGameWithGutter.addBonus(1));

        Frame bonusGameWithSingleRollWithoutStrike = Frame.finalOf(8);
        assertThrows(IllegalArgumentException.class, () -> bonusGameWithSingleRollWithoutStrike.addBonus(1));
    }
}

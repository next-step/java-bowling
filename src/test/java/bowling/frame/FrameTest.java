package bowling.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FrameTest {
    @Test
    @DisplayName("점수생성")
    void checkScore() {
        Frame frame = Frame.of(9);
        frame.addScore(1);
        assertThat(frame.getFrameScoreType()).isEqualTo(FrameScoreType.SPARE);

        frame = Frame.of(10);
        assertThat(frame.getFrameScoreType()).isEqualTo(FrameScoreType.STRIKE);

        frame = Frame.of(4);
        frame.addScore(4);
        assertThat(frame.getFrameScoreType()).isEqualTo(FrameScoreType.MISS);
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

    @Test
    @DisplayName("스페어에서 보너스 게임을 두번 실행 한 경우")
    void twoBonusGameWithSpareException() {
        Frame frame = Frame.finalOf(9);
        frame.addScore(1);
        frame.addBonus(10);
        assertThrows(IllegalArgumentException.class, () -> frame.addBonus(10));
    }

    @Test
    @DisplayName("보너스게임에서 스트라이크를 잡지 못했는데 보너스 게임을 추가로 진행한 경우")
    void twoBonusGameWithoutDoubleStrikeException() {
        Frame frame = Frame.finalOf(10);
        frame.addBonus(9);
        assertThrows(IllegalArgumentException.class, () -> frame.addBonus(10));
    }
}

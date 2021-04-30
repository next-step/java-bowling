package bowling.ui;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FrameScoreTest {
    @Test
    @DisplayName("스코어 추가 테스트")
    void addScoreTest() {
        FrameScore frameScore = new FrameScore();

        frameScore.addScore("6");
        frameScore.addScore("/");

        assertThat(frameScore.scoreConfirm()).isEqualTo("6|/");
    }

    @Test
    @DisplayName("스코어가 확정되면, 더 이상 추가 불가능.")
    void scoreConfirmedTest() {
        FrameScore frameScore = new FrameScore();

        frameScore.addScore("X");
        frameScore.scoreConfirm();

        Assertions.assertThatThrownBy(() -> frameScore.addScore("3"))
                .isInstanceOf(IllegalStateException.class);
    }
}

package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @Test
    @DisplayName("새 프레임을 생성한다.")
    void createFrame() {

        GameResult gameResult = new GameResult("KSH");

        gameResult.createFrame(3);

        assertThat(gameResult.getFrames()).hasSize(1);
        assertThat(gameResult.getFrames().get(0).isEnd()).isFalse();

    }

    @Test
    @DisplayName("아직 끝나지 않은 프레임이면 해당 프레임에 점수 추가하고 End로 상태를 변경한다.")
    void createFrameAddScoreAndChangeEnd() {

        GameResult gameResult = new GameResult("KSH");

        gameResult.createFrame(3);
        gameResult.createFrame(5);

        assertThat(gameResult.getFrames()).hasSize(1);
        assertThat(gameResult.getFrames().get(0).isEnd()).isTrue();

    }

    @Test
    @DisplayName("9번째 프레임 이후로는 finalFrame이 생성된다.")
    void createFinalFrame() {

        GameResult gameResult = new GameResult("KSH");

        gameResult.createFrame(10);
        gameResult.createFrame(10);
        gameResult.createFrame(10);
        gameResult.createFrame(10);
        gameResult.createFrame(10);
        gameResult.createFrame(10);
        gameResult.createFrame(10);
        gameResult.createFrame(10);
        gameResult.createFrame(10); // 9번째 프레임까지 생성

        gameResult.createFrame(9);

        assertThat(gameResult.getFinalFrame().getScores()).hasSize(1);
        assertThat(gameResult.getFinalFrame().isEnd()).isFalse();

    }
}

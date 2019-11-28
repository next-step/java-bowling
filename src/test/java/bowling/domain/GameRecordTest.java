package bowling.domain;

import bowling.domain.status.FinalFirstBowl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameRecordTest {

    @Test
    @DisplayName("새 프레임을 생성한다.")
    void createFrame() {

        GameRecord gameRecord = new GameRecord("KSH");

        gameRecord.recordFrame(3);

        assertThat(gameRecord.getNormalFrames()).hasSize(1);
        assertThat(gameRecord.getNormalFrames().get(0).isEnd()).isFalse();

    }

    @Test
    @DisplayName("아직 끝나지 않은 프레임이면 해당 프레임에 점수 추가하고 End로 상태를 변경한다.")
    void createFrameAddScoreAndChangeEnd() {

        GameRecord gameRecord = new GameRecord("KSH");

        gameRecord.recordFrame(3);
        gameRecord.recordFrame(5);

        assertThat(gameRecord.getNormalFrames()).hasSize(1);
        assertThat(gameRecord.getNormalFrames().get(0).isEnd()).isTrue();

    }

    @Test
    @DisplayName("9번째 프레임 이후로는 finalFrame이 생성된다.")
    void createFinalFrame() {

        GameRecord gameRecord = new GameRecord("KSH");

        gameRecord.recordFrame(10);
        gameRecord.recordFrame(10);
        gameRecord.recordFrame(10);
        gameRecord.recordFrame(10);
        gameRecord.recordFrame(10);
        gameRecord.recordFrame(10);
        gameRecord.recordFrame(10);
        gameRecord.recordFrame(10);
        gameRecord.recordFrame(10); // 9번째 프레임까지 생성

        gameRecord.recordFrame(9);

        assertThat(gameRecord.getFinalFrame().getStatus() instanceof FinalFirstBowl).isTrue();
        assertThat(gameRecord.getFinalFrame().isEnd()).isFalse();

    }
}

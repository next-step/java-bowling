package bowling;

import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.frame.Frames.BOWLING_GAME_FRAME;
import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    Frames frames = new Frames();

    @DisplayName("프레임 생성 테스트")
    @Test
    void makeFrameTest() {
        assertThat(frames.getFrames().size()).isEqualTo(10);
    }

    @DisplayName("일반 프레임 점수 체크 프레임 기회 2번 스트라이크 시 다음 프레임")
    @Test
    public void normalFrameTest() {
        frames.addFrameScore(0, 10);

        frames.addFrameScore(1, 9);

        frames.addFrameScore(2, 7);
        frames.addFrameScore(2, 3);

        assertThat(frames.getFrames().get(0).validateLimitScore()).isTrue();
        assertThat(frames.getFrames().get(1).validateLimitScore()).isFalse();
        assertThat(frames.getFrames().get(2).validateLimitScore()).isTrue();
    }

    @DisplayName("마지막 프레임 체크")
    @Test
    void finalFrameTest() {
        frames.addFrameScore(BOWLING_GAME_FRAME, 10);
        frames.addFrameScore(BOWLING_GAME_FRAME, 10);
        frames.addFrameScore(BOWLING_GAME_FRAME, 3);
        assertThat(frames.getFrames().get(BOWLING_GAME_FRAME).validateLimitScore()).isTrue();
    }

    @DisplayName("마지막 프레임 체크")
    @Test
    void continueFinalFrameTest() {
        frames.addFrameScore(BOWLING_GAME_FRAME, 7);
        frames.addFrameScore(BOWLING_GAME_FRAME, 3);
        assertThat(frames.getFrames().get(BOWLING_GAME_FRAME).validateLimitScore()).isFalse();
    }
}

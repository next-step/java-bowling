package bowling.domain.frame;

import bowling.domain.score.PitchScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("bowl 요청을 보내고 다음 프레임으로 넘어가면 프레임 리스트의 사이즈가 증가")
    @Test
    public void bowl_스트라이크() {
        Frames frames = Frames.initiate();

        frames.bowl(PitchScore.valueOf(10));

        assertThat(frames.isCurrentFrameFinished()).isTrue();

        frames.moveToNextFrame();
    }

    @DisplayName("플레이 할 투구가 남아있다면, hasNextTurn은 true")
    @Test
    public void hasNextTurn_True() {
        Frames frames = Frames.initiate();

        assertThat(frames.hasNextTurn()).isTrue();
    }

    @DisplayName("12번의 스트라이크를 치면 더 이상 투구 기회가 없어 hasNextTurn은 false")
    @Test
    public void hasNextTurn_False() {
        Frames frames = Frames.initiate();
        for (int i = 0; i < 12; i++) {
            frames.bowl(PitchScore.valueOf(10));
            frames.moveToNextFrame();
        }
        assertThat(frames.hasNextTurn()).isFalse();
    }
}

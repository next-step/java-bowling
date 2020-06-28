package bowling.domain.frame;

import bowling.domain.score.PitchScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("initiate하면 자동으로 NormalFrame을 내부 컬렉션에 추가함")
    @Test
    public void initiate_정상() {
        Frames frames = Frames.initiate();
        List<Frame> frameList = frames.getFrames();

        assertThat(frameList.size()).isEqualTo(1);
    }

    @DisplayName("bowl 요청을 보내고 다음 프레임으로 넘어가면 프레임 리스트의 사이즈가 증가")
    @Test
    public void bowl_스트라이크() {
        Frames frames = Frames.initiate();
        List<Frame> frameList = frames.getFrames();

        frames.bowl(PitchScore.valueOf(10));

        assertThat(frameList.get(0).isFinished()).isTrue();

        frames.moveToNextFrame();

        assertThat(frameList.size()).isEqualTo(2);
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

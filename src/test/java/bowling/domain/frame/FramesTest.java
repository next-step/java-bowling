package bowling.domain.frame;

import bowling.domain.score.PitchScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {

    @DisplayName("전체 볼링 게임에서 아직 플레이할 수 있는 투구가 남아있다면, isEnd은 False")
    @Test
    public void isEnd_False() {
        Frames frames = Frames.initiate();

        assertThat(frames.isEnd()).isFalse();
    }

    @DisplayName("12번의 스트라이크를 치면 더 이상 투구 기회가 없어 isEnd은 false")
    @Test
    public void isEnd_True() {
        Frames frames = Frames.initiate();
        for (int i = 0; i < 12; i++) {
            frames.bowl(PitchScore.valueOf(10));
            frames.moveToNextFrame();
        }
        assertThat(frames.isEnd()).isTrue();
    }

    @DisplayName("현재 플레이 중인 프레임에서 투구할 것이 없으면 True 반환")
    @Test
    public void isCurrentFrameFinished_True() {
        Frames frames = Frames.initiate();
        frames.bowl(PitchScore.valueOf(10));

        assertThat(frames.isCurrentFrameFinished()).isTrue();
    }

    @DisplayName("현재 플레이 중인 프레임에서 투구할 것이 있으면 False 반환")
    @Test
    public void isCurrentFrameFinished_False() {
        Frames frames = Frames.initiate();
        frames.bowl(PitchScore.valueOf(3));

        assertThat(frames.isCurrentFrameFinished()).isFalse();
    }

    @DisplayName("스트라이크를 연속으로 친 3개 프레임의 점수를 요청해도, 현재 계산이 가능한 1개 프레임만 점수를 반환한다")
    @Test
    public void getFrameScores_크기_1() {
        Frames frames = Frames.initiate();
        for (int i = 0; i < 3; i++) {
            frames.bowl(PitchScore.valueOf(10));
            frames.moveToNextFrame();
        }

        assertThat(frames.getFrameScores()).containsExactly(30);
    }

    @DisplayName("2개 프레임이 모두 미스이면 프레임 점수 리스트의 크기는 2")
    @Test
    public void getFrameScores_크기_2() {
        Frames frames = Frames.initiate();
        frames.bowl(PitchScore.valueOf(3));
        frames.bowl(PitchScore.valueOf(4));
        frames.moveToNextFrame();
        frames.bowl(PitchScore.valueOf(5));
        frames.bowl(PitchScore.valueOf(3));

        assertThat(frames.getFrameScores()).containsExactly(7, 15);
    }

    @DisplayName("12번 스트라이크를 치면 300점이 최종 누계됨")
    @Test
    public void getFrameScores_300점() {
        Frames frames = Frames.initiate();
        for (int i = 0; i < 12; i++) {
            frames.bowl(PitchScore.valueOf(10));
            frames.moveToNextFrame();
        }

        List<Integer> frameScores = frames.getFrameScores();

        assertThat(frameScores.get(frameScores.size() - 1)).isEqualTo(300);
    }
}

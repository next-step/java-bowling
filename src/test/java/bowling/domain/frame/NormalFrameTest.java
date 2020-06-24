package bowling.domain.frame;

import bowling.domain.score.FrameScore;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("2번 투구한 경우 다음 Frame으로 넘어감")
    @Test
    public void isMovableToNextFrame_2번투구() {
        NormalFrame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(Score.valueOf(5));
        normalFrame.bowl(Score.valueOf(5));

        assertThat(normalFrame.isMovableToNextFrame()).isTrue();
    }

    @DisplayName("Strike를 한 경우 Frame으로 넘어감")
    @Test
    public void isMovableToNextFrame_스트라이크() {
        NormalFrame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(Score.valueOf(10));

        assertThat(normalFrame.isMovableToNextFrame()).isTrue();
    }

    @DisplayName("1번 투구한 경우 넘어갈수 없음")
    @Test
    public void isMovableToNextFrame_1번() {
        NormalFrame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(Score.valueOf(3));

        assertThat(normalFrame.isMovableToNextFrame()).isFalse();
    }

    @DisplayName("기존 Frame을 통해 다음 Frame을 생성함")
    @Test
    public void next_2번() {
        NormalFrame normalFrame = NormalFrame.initiate();

        assertThat(normalFrame.getIndex()).isEqualTo(1);

        Frame nextFrame = normalFrame.next();

        assertThat(nextFrame.getIndex()).isEqualTo(2);
    }

    @DisplayName("기존 Frame의 인덱스가 9이면 다음 Frame은 FinalFrame을 생성함")
    @Test
    public void next_마지막() {
        NormalFrame normalFrame = NormalFrame.initiate();
        Frame nextFrame = normalFrame.next();
        for (int i = 0; i < 8; i++) {
            nextFrame = nextFrame.next();
        }

        assertThat(nextFrame.getIndex()).isEqualTo(10);
        assertThat(nextFrame.getClass()).isEqualTo(FinalFrame.class);
    }

    @DisplayName("점수 계산시 lastFrame이 스트라이크인 경우 NextFrame이 2번 투구를 하지 않으면 점수는 0(null)")
    @Test
    public void calculateScore_스트라이크_Null() {
        Frame lastFrame = NormalFrame.initiate();
        lastFrame.bowl(Score.valueOf(10));

        Frame nextFrame = lastFrame.next();
        assertThat(nextFrame.calculateFrameScore(lastFrame)).isNull();

        nextFrame.bowl(Score.valueOf(5));
        assertThat(nextFrame.calculateFrameScore(lastFrame)).isNull();
    }

    @DisplayName("점수 계산시 lastFrame이 스트라이크인 경우 NextFrame이 2번 투구를 해야 정상 집계")
    @Test
    public void calculateScore_스트라이크_20점() {
        Frame lastFrame = NormalFrame.initiate();
        lastFrame.bowl(Score.valueOf(10));

        Frame nextFrame = lastFrame.next();
        nextFrame.bowl(Score.valueOf(5));
        nextFrame.bowl(Score.valueOf(5));

        FrameScore result = nextFrame.calculateFrameScore(lastFrame);

        assertThat(result.getFrameScoreTotal()).isEqualTo(20);
    }

    @DisplayName("점수 계산시 lastFrame이 스페어인 경우 NextFrame이 1번 투구를 하지 않으면 점수는 0(null)")
    @Test
    public void calculateScore_스페어_Null() {
        Frame lastFrame = NormalFrame.initiate();
        lastFrame.bowl(Score.valueOf(3));
        lastFrame.bowl(Score.valueOf(7));

        Frame nextFrame = lastFrame.next();

        assertThat(nextFrame.calculateFrameScore(lastFrame)).isNull();
    }

    @DisplayName("점수 계산시 lastFrame이 스페어인 경우 NextFrame이 1번 투구를 해야 정상 집계")
    @Test
    public void calculateScore_스페어_15점() {
        Frame lastFrame = NormalFrame.initiate();
        lastFrame.bowl(Score.valueOf(3));
        lastFrame.bowl(Score.valueOf(7));

        Frame nextFrame = lastFrame.next();
        nextFrame.bowl(Score.valueOf(5));

        FrameScore result = nextFrame.calculateFrameScore(lastFrame);

        assertThat(result.getFrameScoreTotal()).isEqualTo(15);
    }

    @DisplayName("lastFrame에 스페어나 스트라이크가 없는 경우 이전 프레임의 점수의 합을 바로 반환")
    @Test
    public void lastFrameScores_미스() {
        Frame lastFrame = NormalFrame.initiate();
        lastFrame.bowl(Score.valueOf(4));
        lastFrame.bowl(Score.valueOf(0));
        Frame nextFrame = lastFrame.next();

        FrameScore result = nextFrame.calculateFrameScore(lastFrame);

        assertThat(result.getFrameScoreTotal()).isEqualTo(4);
    }
}

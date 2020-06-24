package bowling.domain.frame;

import bowling.domain.score.FrameNumericScore;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @DisplayName("1번 투구했을 때 추가 투구 가능")
    @Test
    public void isMovableToNextFrame_1번투구() {
        FinalFrame finalFrame = FinalFrame.last(10);
        finalFrame.bowl(Score.valueOf(10));

        assertThat(finalFrame.isMovableToNextFrame()).isFalse();
    }

    @DisplayName("2번 투구했을 때 스트라이크나 스페어가 없으면 경기 종료")
    @Test
    public void isMovableToNextFrame_미스() {
        FinalFrame finalFrame = FinalFrame.last(10);
        finalFrame.bowl(Score.valueOf(0));
        finalFrame.bowl(Score.valueOf(3));

        assertThat(finalFrame.isMovableToNextFrame()).isTrue();
    }

    @DisplayName("2번 투구했을 때 스트라이크가 1번 존재하면 추가 투구 가능")
    @Test
    public void isMovableToNextFrame_원스트라이크() {
        FinalFrame finalFrame = FinalFrame.last(10);
        finalFrame.bowl(Score.valueOf(10));
        finalFrame.bowl(Score.valueOf(0));

        assertThat(finalFrame.isMovableToNextFrame()).isFalse();
    }

    @DisplayName("2연속 스트라이크일 때 추가 투구 가능")
    @Test
    public void isMovableToNextFrame_투스트라이크() {
        FinalFrame finalFrame = FinalFrame.last(10);
        finalFrame.bowl(Score.valueOf(10));
        finalFrame.bowl(Score.valueOf(10));

        assertThat(finalFrame.isMovableToNextFrame()).isFalse();
    }

    @DisplayName("2번 투구 시 스페어이면 추가 투구 가능")
    @Test
    public void isMovableToNextFrame_스페어() {
        FinalFrame finalFrame = FinalFrame.last(10);
        finalFrame.bowl(Score.valueOf(3));
        finalFrame.bowl(Score.valueOf(7));

        assertThat(finalFrame.isMovableToNextFrame()).isFalse();
    }

    @DisplayName("3번 투구하면 경기 종료")
    @Test
    public void isMovableToNextFrame_3번투구() {
        FinalFrame finalFrame = FinalFrame.last(10);
        finalFrame.bowl(Score.valueOf(10));
        finalFrame.bowl(Score.valueOf(10));
        finalFrame.bowl(Score.valueOf(0));

        assertThat(finalFrame.isMovableToNextFrame()).isTrue();
    }

    @DisplayName("이전 9프레임의 결과가 스트라이크라면 마지막 프레임 2번 투구 전까지 점수가 집계되지 않는다")
    @Test
    public void calculateScores_스트라이크() {
        Frame lastFrame = NormalFrame.initiate();
        lastFrame.bowl(Score.valueOf(10));

        Frame finalFrame = FinalFrame.last(10);
        assertThat(finalFrame.calculateFrameScore(lastFrame)).isNull();

        finalFrame.bowl(Score.valueOf(10));
        assertThat(finalFrame.calculateFrameScore(lastFrame)).isNull();

        finalFrame.bowl(Score.valueOf(10));
        FrameNumericScore result = finalFrame.calculateFrameScore(lastFrame);
        assertThat(result.getFrameScoreTotal()).isEqualTo(30);
    }

    @DisplayName("이전 9프레임의 결과가 스페어라면 마지막 프레임 1번 투구 전까지 점수가 집계되지 않는다")
    @Test
    public void calculateScores_스페어() {
        Frame lastFrame = NormalFrame.initiate();
        lastFrame.bowl(Score.valueOf(3));
        lastFrame.bowl(Score.valueOf(7));

        Frame finalFrame = FinalFrame.last(10);
        assertThat(finalFrame.calculateFrameScore(lastFrame)).isNull();

        finalFrame.bowl(Score.valueOf(10));
        FrameNumericScore result = finalFrame.calculateFrameScore(lastFrame);
        assertThat(result.getFrameScoreTotal()).isEqualTo(20);
    }

    @DisplayName("마지막 프레임이 종료된 뒤 생성된 nextFrame 객체를 통해 마지막 프레임으로 점수 집계 : 단순 총합")
    @Test
    public void calcualteScores_마지막_프레임() {
        Frame finalFrame = FinalFrame.last(10);
        finalFrame.bowl(Score.valueOf(10));
        finalFrame.bowl(Score.valueOf(5));
        finalFrame.bowl(Score.valueOf(5));

        Frame nextFrame = finalFrame.next();
        FrameNumericScore result = nextFrame.calculateFrameScore(finalFrame);

        assertThat(result.getFrameScoreTotal()).isEqualTo(20);
    }
}

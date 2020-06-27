package bowling.domain.frame;

import bowling.domain.score.FrameScore;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @DisplayName("1번 투구했을 때 추가 투구 가능")
    @Test
    public void isMovableToNextFrame_1번투구() {
        FinalFrame finalFrame = FinalFrame.ofFinal();
        finalFrame.bowl(Score.valueOf(10));

        assertThat(finalFrame.isFinished()).isFalse();
    }

    @DisplayName("2번 투구했을 때 스트라이크나 스페어가 없으면 경기 종료")
    @Test
    public void isMovableToNextFrame_미스() {
        FinalFrame finalFrame = FinalFrame.ofFinal();
        finalFrame.bowl(Score.valueOf(0));
        finalFrame.bowl(Score.valueOf(3));

        assertThat(finalFrame.isFinished()).isTrue();
    }

    @DisplayName("2번 투구했을 때 스트라이크가 1번 존재하면 추가 투구 가능")
    @Test
    public void isMovableToNextFrame_원스트라이크() {
        FinalFrame finalFrame = FinalFrame.ofFinal();
        finalFrame.bowl(Score.valueOf(10));
        finalFrame.bowl(Score.valueOf(0));

        assertThat(finalFrame.isFinished()).isFalse();
    }

    @DisplayName("2연속 스트라이크일 때 추가 투구 가능")
    @Test
    public void isMovableToNextFrame_투스트라이크() {
        FinalFrame finalFrame = FinalFrame.ofFinal();
        finalFrame.bowl(Score.valueOf(10));
        finalFrame.bowl(Score.valueOf(10));

        assertThat(finalFrame.isFinished()).isFalse();
    }

    @DisplayName("2번 투구 시 스페어이면 추가 투구 가능")
    @Test
    public void isMovableToNextFrame_스페어() {
        FinalFrame finalFrame = FinalFrame.ofFinal();
        finalFrame.bowl(Score.valueOf(3));
        finalFrame.bowl(Score.valueOf(7));

        assertThat(finalFrame.isFinished()).isFalse();
    }

    @DisplayName("3번 투구하면 경기 종료")
    @Test
    public void isMovableToNextFrame_3번투구() {
        FinalFrame finalFrame = FinalFrame.ofFinal();
        finalFrame.bowl(Score.valueOf(10));
        finalFrame.bowl(Score.valueOf(10));
        finalFrame.bowl(Score.valueOf(0));

        assertThat(finalFrame.isFinished()).isTrue();
    }

    @DisplayName("마지막 프레임의 점수는 단순히 합계만함")
    @Test
    public void calculateFrameScore_마지막_프레임() {
        FinalFrame finalFrame = FinalFrame.ofFinal();
        finalFrame.bowl(Score.valueOf(3));
        finalFrame.bowl(Score.valueOf(7));
        finalFrame.bowl(Score.valueOf(10));

        FrameScore frameScore = finalFrame.calculateFrameScore().get();
        assertThat(frameScore.getFrameScore()).isEqualTo(20);
    }

    @DisplayName("FinalFrame도 동일하게 계산을 delegate함")
    @Test
    public void delegate_마짐작_프레임() {
        Frame normalFrame = NormalFrame.initiate();
        Frame finalFrame = normalFrame.next(9);

        normalFrame.bowl(Score.valueOf(10));
        finalFrame.bowl(Score.valueOf(10));
        finalFrame.bowl(Score.valueOf(5));

        FrameScore frameScore = normalFrame.calculateFrameScore().get();
        assertThat(frameScore.getFrameScore()).isEqualTo(25);
    }
}

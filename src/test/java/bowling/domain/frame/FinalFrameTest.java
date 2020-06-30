package bowling.domain.frame;

import bowling.domain.dto.ScoreSignaturesDto;
import bowling.domain.exception.FramesMaximumException;
import bowling.domain.score.FrameScore;
import bowling.domain.score.PitchScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    @DisplayName("1번 투구했을 때 추가 투구 가능")
    @Test
    public void isFinished_1번투구_False() {
        Frame finalFrame = FinalFrame.ofFinal();
        finalFrame.bowl(PitchScore.valueOf(10));

        assertThat(finalFrame.isFinished()).isFalse();
    }

    @DisplayName("2번 투구했을 때 스트라이크나 스페어가 없으면 경기 종료")
    @Test
    public void isFinished_미스_True() {
        Frame finalFrame = FinalFrame.ofFinal();
        finalFrame.bowl(PitchScore.valueOf(0));
        finalFrame.bowl(PitchScore.valueOf(3));

        assertThat(finalFrame.isFinished()).isTrue();
    }

    @DisplayName("FinalFrame 객체로 next 객체 생성시 예외 발생")
    @Test
    public void next_예외() {
        assertThatThrownBy(() -> {
            FinalFrame.ofFinal().next(11);
        }).isInstanceOf(FramesMaximumException.class);
    }

    @DisplayName("2번 투구했을 때 스트라이크가 1번 존재하면 추가 투구 가능")
    @Test
    public void isFinished_원스트라이크_False() {
        Frame finalFrame = FinalFrame.ofFinal();
        finalFrame.bowl(PitchScore.valueOf(10));
        finalFrame.bowl(PitchScore.valueOf(0));

        assertThat(finalFrame.isFinished()).isFalse();
    }

    @DisplayName("2연속 스트라이크일 때 추가 투구 가능")
    @Test
    public void isFinished_투스트라이크_False() {
        Frame finalFrame = FinalFrame.ofFinal();
        finalFrame.bowl(PitchScore.valueOf(10));
        finalFrame.bowl(PitchScore.valueOf(10));

        assertThat(finalFrame.isFinished()).isFalse();
    }

    @DisplayName("2번 투구 시 스페어이면 추가 투구 가능")
    @Test
    public void isFinished_스페어_False() {
        Frame finalFrame = FinalFrame.ofFinal();
        finalFrame.bowl(PitchScore.valueOf(3));
        finalFrame.bowl(PitchScore.valueOf(7));

        assertThat(finalFrame.isFinished()).isFalse();
    }

    @DisplayName("3번 투구하면 경기 종료")
    @Test
    public void isFinished_3번투구_True() {
        Frame finalFrame = FinalFrame.ofFinal();
        finalFrame.bowl(PitchScore.valueOf(10));
        finalFrame.bowl(PitchScore.valueOf(10));
        finalFrame.bowl(PitchScore.valueOf(0));

        assertThat(finalFrame.isFinished()).isTrue();
    }

    @DisplayName("마지막 프레임의 점수는 단순히 합계만함")
    @Test
    public void calculateFrameScore_마지막_프레임() {
        Frame finalFrame = FinalFrame.ofFinal();
        finalFrame.bowl(PitchScore.valueOf(3));
        finalFrame.bowl(PitchScore.valueOf(7));
        finalFrame.bowl(PitchScore.valueOf(10));

        FrameScore frameScore = finalFrame.calculateFrameScore().get();
        ScoreSignaturesDto scoreSignaturesDto = finalFrame.getScoreSignaturesDto();

        assertThat(frameScore.getFrameScore()).isEqualTo(20);
        assertThat(scoreSignaturesDto.getScoreSignatures()).containsExactly("3", "/", "X");
    }

    @DisplayName("FinalFrame도 동일하게 delegate 작업을 수행함")
    @Test
    public void delegate_마지막_프레임() {
        Frame normalFrame = NormalFrame.initiate();
        Frame finalFrame = normalFrame.next(9);

        normalFrame.bowl(PitchScore.valueOf(10));
        finalFrame.bowl(PitchScore.valueOf(10));
        finalFrame.bowl(PitchScore.valueOf(5));

        FrameScore frameScore = normalFrame.calculateFrameScore().get();
        assertThat(frameScore.getFrameScore()).isEqualTo(25);
    }
}

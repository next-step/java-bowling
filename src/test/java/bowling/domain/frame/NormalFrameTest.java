package bowling.domain.frame;

import bowling.domain.dto.ScoreSignaturesDto;
import bowling.domain.score.FrameScore;
import bowling.domain.score.PitchScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @DisplayName("2번 투구한 경우 다음 Frame으로 넘어감")
    @Test
    public void isFinished_2번투구_True() {
        Frame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(PitchScore.valueOf(5));
        normalFrame.bowl(PitchScore.valueOf(5));

        assertThat(normalFrame.isFinished()).isTrue();
    }

    @DisplayName("Strike를 한 경우 다음 Frame으로 넘어감")
    @Test
    public void isFinished_스트라이크_True() {
        Frame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(PitchScore.valueOf(10));

        assertThat(normalFrame.isFinished()).isTrue();
    }

    @DisplayName("1번 투구한 경우 넘어갈수 없음")
    @Test
    public void isFinished_1번_False() {
        Frame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(PitchScore.valueOf(3));

        assertThat(normalFrame.isFinished()).isFalse();
    }

    @DisplayName("기존 Frame의 인덱스가 9이면 다음 Frame은 FinalFrame을 생성함")
    @Test
    public void next_마지막() {
        Frame normalFrame = NormalFrame.initiate();
        Frame nextFrame = normalFrame.next(9);

        assertThat(nextFrame.getClass()).isEqualTo(FinalFrame.class);
    }

    @DisplayName("Miss인 경우 바로 점수를 반환함")
    @Test
    public void getFrameScore_Miss() {
        Frame frame = NormalFrame.initiate();
        frame.bowl(PitchScore.valueOf(3));
        frame.bowl(PitchScore.valueOf(6));

        FrameScore frameScore = frame.calculateFrameScore().get();
        ScoreSignaturesDto scoreSignaturesDto = frame.getScoreSignaturesDto();

        assertThat(frameScore.getFrameScore()).isEqualTo(9);
        assertThat(frameScore.isAbleToCalculate()).isTrue();
        assertThat(scoreSignaturesDto.getScoreSignatures()).containsExactly("3", "6");
    }

    @DisplayName("Spare인 경우 다음 프레임으로 점수 계산을 위임함")
    @Test
    public void getFrameScore_Spare() {
        Frame frame = NormalFrame.initiate();
        Frame nextFrame = frame.next(2);

        frame.bowl(PitchScore.valueOf(3));
        frame.bowl(PitchScore.valueOf(7));
        nextFrame.bowl(PitchScore.valueOf(4));

        FrameScore frameScore = frame.calculateFrameScore().get();
        ScoreSignaturesDto scoreSignaturesDto = frame.getScoreSignaturesDto();

        assertThat(frameScore.getFrameScore()).isEqualTo(14);
        assertThat(frameScore.isAbleToCalculate()).isTrue();
        assertThat(scoreSignaturesDto.getScoreSignatures()).containsExactly("3", "/");
    }

    @DisplayName("결과가 Strike이고 그 다음 1번밖에 안 친 경우 empty Optional 객체를 리턴함")
    @Test
    public void getFrameScore_Strike_추가투구_1번() {
        Frame frame = NormalFrame.initiate();
        Frame nextFrame = frame.next(2);

        frame.bowl(PitchScore.valueOf(10));
        nextFrame.bowl(PitchScore.valueOf(4));
        ScoreSignaturesDto scoreSignaturesDto = frame.getScoreSignaturesDto();

        assertThat(frame.calculateFrameScore()).isEmpty();
        assertThat(scoreSignaturesDto.getScoreSignatures()).containsExactly("X");
    }

    @DisplayName("결과가 Strike이고 그 다음 2번 투구한 경우 점수 계산이 가능함")
    @Test
    public void getFrameScore_Strike_추가투구_2번() {
        Frame frame = NormalFrame.initiate();
        Frame nextFrame = frame.next(2);

        frame.bowl(PitchScore.valueOf(10));
        nextFrame.bowl(PitchScore.valueOf(4));
        nextFrame.bowl(PitchScore.valueOf(3));

        FrameScore frameScore = frame.calculateFrameScore().get();

        assertThat(frameScore.getFrameScore()).isEqualTo(17);
    }

    @DisplayName("스트라이크가 3연속인 경우 가장 처음 프레임의 점수는 30점이며 두 번째 프레임은 점수 집계 불가능")
    @Test
    public void getFrameScore_Strike_연속3번() {
        Frame firstFrame = NormalFrame.initiate();
        Frame secondFrame = firstFrame.next(2);
        Frame thirdFrame = secondFrame.next(3);

        firstFrame.bowl(PitchScore.valueOf(10));
        secondFrame.bowl(PitchScore.valueOf(10));
        thirdFrame.bowl(PitchScore.valueOf(10));

        FrameScore firstFrameScore = firstFrame.calculateFrameScore().get();

        assertThat(firstFrameScore.getFrameScore()).isEqualTo(30);
        assertThat(secondFrame.calculateFrameScore()).isEmpty();
    }
}

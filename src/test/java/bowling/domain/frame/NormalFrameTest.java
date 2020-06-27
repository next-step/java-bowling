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
        Frame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(Score.valueOf(5));
        normalFrame.bowl(Score.valueOf(5));

        assertThat(normalFrame.isFinished()).isTrue();
    }

    @DisplayName("Strike를 한 경우 Frame으로 넘어감")
    @Test
    public void isMovableToNextFrame_스트라이크() {
        Frame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(Score.valueOf(10));

        assertThat(normalFrame.isFinished()).isTrue();
    }

    @DisplayName("1번 투구한 경우 넘어갈수 없음")
    @Test
    public void isMovableToNextFrame_1번() {
        Frame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(Score.valueOf(3));

        assertThat(normalFrame.isFinished()).isFalse();
    }

    @DisplayName("기존 Frame을 통해 다음 Frame을 생성함")
    @Test
    public void next_2번() {
        Frame normalFrame = NormalFrame.initiate();

        Frame nextFrame = normalFrame.next(1);
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
        frame.bowl(Score.valueOf(3));
        frame.bowl(Score.valueOf(6));

        FrameScore frameScore = frame.calculateFrameScore().get();

        assertThat(frameScore.getFrameScore()).isEqualTo(9);
        assertThat(frameScore.isAbleToCalculate()).isTrue();
    }

    @DisplayName("Spare인 경우 프레임으로 점수 계산을 위임함")
    @Test
    public void getFrameScore_Spare() {
        Frame frame = NormalFrame.initiate();
        Frame nextFrame = frame.next(2);

        frame.bowl(Score.valueOf(3));
        frame.bowl(Score.valueOf(7));

        nextFrame.bowl(Score.valueOf(4));

        FrameScore frameScore = frame.calculateFrameScore().get();

        assertThat(frameScore.getFrameScore()).isEqualTo(14);
        assertThat(frameScore.isAbleToCalculate()).isTrue();
    }

    @DisplayName("결과가 Strike이고 그 다음 1번밖에 안 친 경우 null을 리턴함")
    @Test
    public void getFrameScore_Strike_추가투구_1번() {
        Frame frame = NormalFrame.initiate();
        Frame nextFrame = frame.next(2);

        frame.bowl(Score.valueOf(10));
        nextFrame.bowl(Score.valueOf(4));

        assertThat(frame.calculateFrameScore()).isEmpty();
    }

    @DisplayName("결과가 Strike이고 그 다음 2번 투구한 경우 점수 계산이 가능함")
    @Test
    public void getFrameScore_Strike_추가투구_2번() {
        Frame frame = NormalFrame.initiate();
        Frame nextFrame = frame.next(2);

        frame.bowl(Score.valueOf(10));
        nextFrame.bowl(Score.valueOf(4));
        nextFrame.bowl(Score.valueOf(3));

        FrameScore frameScore = frame.calculateFrameScore().get();

        assertThat(frameScore.getFrameScore()).isEqualTo(17);
    }

    @DisplayName("스트라이크가 3연속인 경우 가장 처음 프레임의 점수는 30점이며 두 번째 프레임은 점수 집계 불가능")
    @Test
    public void getFrameScore_Strike_연속3번() {
        Frame frame = NormalFrame.initiate();
        Frame nextFrame = frame.next(2);
        Frame thirdFrame = nextFrame.next(3);

        frame.bowl(Score.valueOf(10));
        nextFrame.bowl(Score.valueOf(10));
        thirdFrame.bowl(Score.valueOf(10));

        FrameScore frameScore = frame.calculateFrameScore().get();

        assertThat(frameScore.getFrameScore()).isEqualTo(30);
        assertThat(nextFrame.calculateFrameScore()).isEmpty();
    }
}

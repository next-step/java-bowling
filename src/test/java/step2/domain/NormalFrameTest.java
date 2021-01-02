package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.domain.frame.FinalFrame;
import step2.domain.frame.Frame;
import step2.domain.frame.NormalFrame;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("NormalFrame 초기화")
    void init() {
        assertThat(NormalFrame.init()).isInstanceOf(Frame.class);
    }

    @Test
    @DisplayName("NormalFrame 투구시 현재 인스턴스 반환")
    void bowl() {
        Frame frame = NormalFrame.init();

        assertThat(frame.bowl(9)).isSameAs(frame);
    }

    @Test
    @DisplayName("NormalFrame 투구시 새로운 NormalFrame 반환")
    void bowlReturnNewNormalFrame() {
        Frame frame = NormalFrame.init();

        assertThat(frame.bowl(10)).isInstanceOf(NormalFrame.class);
    }

    @Test
    @DisplayName("NormalFrame 투구시 FinalFrame 반환")
    void bowlReturnFinalFrame() {
        Frame frame = new NormalFrame(9);

        assertThat(frame.bowl(10)).isInstanceOf(FinalFrame.class);
    }

    @Test
    @DisplayName("NormalFrame 투구 시 점수 계산")
    void getFrameScore() {
        Frame frame1 = NormalFrame.init();
        frame1.bowl(3).bowl(5);
        Score score = frame1.calculateAdditionalScore(Score.of(0, 2));

        assertThat(score.getScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("NormalFrame 투구 시 이전 프레이 합산 점수 계산")
    void getNextFrameScore() {
        Frame frame1 = NormalFrame.init();
        Frame frame2 = frame1.bowl(3).bowl(5);
        Score score1 = frame1.calculateAdditionalScore(Score.of(0, 2));
        frame2.bowl(6).bowl(3);
        Score score2 = frame2.calculateAdditionalScore(Score.of(score1.getScore(), 2));

        assertThat(score2.getScore()).isEqualTo(17);
    }

    @Test
    @DisplayName("NormalFrame 스트라이크 점수 계산")
    void getStrikeScore() {
        Frame frame1 = NormalFrame.init();
        frame1.bowl(10).bowl(10).bowl(10);

        Score score = frame1.calculateAdditionalScore(Score.ofStrike());

        assertThat(score.getScore()).isEqualTo(30);
    }

    @Test
    @DisplayName("NormalFrame 프레임 종료")
    void isFinish() {
        Frame frame1 = NormalFrame.init();
        frame1.bowl(3).bowl(5);

        assertThat(frame1.isFinish()).isTrue();
    }

    @Test
    @DisplayName("NormalFrame 프레임 종료 아님")
    void isNotFinish() {
        Frame frame1 = NormalFrame.init();
        frame1.bowl(3);

        assertThat(frame1.isFinish()).isFalse();
    }

}